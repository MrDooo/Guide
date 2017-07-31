package com.example.mr_do.guide.guide;


import android.content.Context;

import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.model.NaviLatLng;

import java.util.List;

import static com.amap.api.maps.AMapUtils.DRIVING_DEFAULT;

/**
 * Created by Mr_Do on 2017/7/28.
 */

public class GuideData implements IGuideData {

    private Context mContext;
    private AMapNavi mAMapNavi;
    private OnCalculateRouteCallBack mOnCalculateRouteCallBack;

    public GuideData(Context context){
        mContext = context;
        mAMapNavi = AMapNavi.getInstance(mContext);
    }

    @Override
    public void calculateForNavi(OnCalculateRouteCallBack calculateRouteCallBack) {
        mOnCalculateRouteCallBack = calculateRouteCallBack;
        mAMapNavi.addAMapNaviListener(mInterfaceImplement);
    }

    @Override
    public void calculateWalk(NaviLatLng startLatLng , NaviLatLng endLatLng) {
        mAMapNavi.calculateWalkRoute(startLatLng , endLatLng);
    }

    @Override
    public void calculateDrive(List<NaviLatLng> startLatLngs , List<NaviLatLng> endLatLngs) {
        mAMapNavi.calculateDriveRoute(startLatLngs , endLatLngs , null , DRIVING_DEFAULT);
    }


    private InterfaceImplement mInterfaceImplement = new InterfaceImplement(){
        @Override
        public void onInitNaviFailure() {
            super.onInitNaviFailure();
            mOnCalculateRouteCallBack.onCalculateFailed();
        }

        @Override
        public void onInitNaviSuccess() {
            super.onInitNaviSuccess();
            if(mOnCalculateRouteCallBack != null) {
                mOnCalculateRouteCallBack.onInitSuccess();
            }
        }

        @Override
        public void onCalculateRouteFailure(int i) {
            super.onCalculateRouteFailure(i);
            if(mOnCalculateRouteCallBack != null){
                mOnCalculateRouteCallBack.onCalculateFailed();
            }
        }

        @Override
        public void onCalculateRouteSuccess(int[] ints) {
            super.onCalculateRouteSuccess(ints);
            if(mOnCalculateRouteCallBack != null){
                mOnCalculateRouteCallBack.onCalculateSuccess();
            }
        }
    };

}
