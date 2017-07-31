package com.example.mr_do.guide.Locate;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.example.mr_do.guide.guide.BaseFragment;
import com.example.mr_do.guide.guide.GuideActivity;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public class LocationPresenter {

    private ILocateView mLocateView;
    private ILocateData mLocateData;
    private double mEndLatitude = 0xfff;
    private double mEndLongitude = 0xfff;
    private double mStartLatitude = 0xfff;
    private double mStartLongitude = 0xfff;

    private int mZoom;
    private int mTilt;
    private int mBearing;
    private long mMoveDuration;

    private ILocateData.GetLocationCallBack mCallBack = new ILocateData.GetLocationCallBack() {
        @Override
        public void onLocatedMachineSuccess(double latitude, double longitude) {
            mLocateView.finishLocating();
            mEndLatitude = latitude;
            mEndLongitude = longitude;
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(
                    new CameraPosition(new LatLng(latitude, longitude),
                            mZoom,
                            mTilt,
                            mBearing));
            mLocateView.locate(cameraUpdate , mMoveDuration);
            mLocateView.addMark(latitude , longitude);
        }

        @Override
        public void onLocatedPhoneSuccess(AMapLocation location) {
            mStartLatitude = location.getLatitude();
            mStartLongitude = location.getLongitude();
        }

        @Override
        public void onLocateMachineFailed(int error_code) {
            mLocateView.finishLocating();
            mLocateView.showFailed(error_code);
        }

        @Override
        public void onLocatePhoneFailed(int error_code) {
            mLocateView.finishLocating();
            mLocateView.showFailed(error_code);
        }

    };



    public LocationPresenter(ILocateView locateView){
        mLocateView = locateView;
        mLocateData = new LocateData();
    }

    public void back(){
        mLocateView.back();
    }

    /**
     * 将视角移动到传入的经纬度所在的位置
     * @param zoom - 缩放级别0-18
     * @param tilt - 目标可视区域的倾斜度，以角度为单位
     * @param bearing - 可视区域指向的方向，以角度为单位，从正北向逆时针方向计算，从0 度到360 度
     * @param moveDuration - 移动到该位置的动画播放时间
     */
    public void locateMachine(final int zoom , final int tilt , final int bearing , final long moveDuration){
        mZoom = zoom;
        mTilt = tilt;
        mBearing = bearing;
        mMoveDuration = moveDuration;
        mLocateView.showLocating();
        mLocateData.getMachineLocation(mCallBack);
    }

    public void locatePhone(Context context){
        mLocateData.getPhoneLocation(context , mCallBack);
    }

    public void startGuide(Context context){
        mLocateView.setGuideButtonEnable(false);
        if(Math.abs(mEndLatitude - 0xfff) > 1e-6 &&
                Math.abs(mEndLongitude - 0xfff) > 1e-6 &&
                Math.abs(mStartLatitude - 0xfff) > 1e-6 &&
                Math.abs(mStartLongitude - 0xfff) > 1e-6
                ){
            Intent intent = new Intent(context , GuideActivity.class);
            intent.putExtra(BaseFragment.START_LATITUDE , mStartLatitude);
            intent.putExtra(BaseFragment.START_LONGITUDE , mStartLongitude);
            intent.putExtra(BaseFragment.END_LATITUDE , mEndLatitude);
            intent.putExtra(BaseFragment.END_LONGITUDE , mEndLongitude);
            mLocateView.startGuide(intent);
        }else{
            Log.e("LocationMessage",
                    "SLat:"+mStartLatitude+" "+
                            "SLong:"+mStartLongitude+" "+
                            "ELat:"+mEndLatitude+" "+
                            "ELong:"+mEndLongitude);
            mLocateView.setGuideButtonEnable(true);
        }
    }
}
