package com.example.mr_do.guide.guide;

import android.content.Context;

import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.model.NaviLatLng;

import java.util.ArrayList;

/**
 * Created by Mr_Do on 2017/7/28.
 */

public class GuidePresenter {

    private IGuideView mGuideView;
    private IGuideData mGuideData;


    public GuidePresenter(IGuideView guideView , Context context){
        mGuideView = guideView;
        mGuideData = new GuideData(context);
    }

    public void startWalkGuide(){
        mGuideData.calculateForNavi(new IGuideData.OnCalculateRouteCallBack() {
            @Override
            public void onInitSuccess() {
                mGuideView.showCalculating();
                NaviLatLng startLatLng = new NaviLatLng(mGuideView.getStartLatitude() , mGuideView.geStartLongitude());
                NaviLatLng endLatLng = new NaviLatLng(mGuideView.getEndLatitude() , mGuideView.geEndLongitude());
                mGuideData.calculateWalk(startLatLng , endLatLng);
            }

            @Override
            public void onCalculateSuccess() {
                mGuideView.finishCalculating();
                mGuideView.startGuide();
            }

            @Override
            public void onCalculateFailed() {
                mGuideView.finishCalculating();
                mGuideView.calculateError();
            }
        });
    }

    public void startDriveGuide(){
        mGuideData.calculateForNavi(new IGuideData.OnCalculateRouteCallBack() {
            @Override
            public void onInitSuccess() {
                mGuideView.showCalculating();
                ArrayList<NaviLatLng> startLatLngs = new ArrayList<NaviLatLng>();
                ArrayList<NaviLatLng> endLatLngs = new ArrayList<NaviLatLng>();
                NaviLatLng startLatLng = new NaviLatLng(mGuideView.getStartLatitude() , mGuideView.geStartLongitude());
                NaviLatLng endLatLng = new NaviLatLng(mGuideView.getEndLatitude() , mGuideView.geEndLongitude());
                startLatLngs.add(startLatLng);
                endLatLngs.add(endLatLng);
                mGuideData.calculateDrive(startLatLngs , endLatLngs);
                mGuideData.calculateDrive(startLatLngs ,endLatLngs);
            }

            @Override
            public void onCalculateSuccess() {
                mGuideView.finishCalculating();
                mGuideView.startGuide();
            }

            @Override
            public void onCalculateFailed() {
                mGuideView.finishCalculating();
                mGuideView.calculateError();
            }
        });
    }

    public void stopGuide(){
        mGuideView.stopGuide();
    }

}
