package com.example.mr_do.guide.guide;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.autonavi.tbt.TrafficFacilityInfo;


/**
 * Created by Mr_Do on 2017/7/11.
 */

public class BaseFragment extends Fragment implements AMapNaviViewListener, RouteSearch.OnRouteSearchListener {

    public static final String START_LATITUDE = "startLatitude";
    public static final String END_LATITUDE = "endLatitude";
    public static final String START_LONGITUDE = "startLongitude";
    public static final String END_LONGITUDE = "endLongitude";

    public static final double LATITUDE_DEFAUL = 0xfff;
    public static final double LONGITUDE_DEFAUL = 0xfff;

    protected double mStartLatitude ;
    protected double mStartLongitude ;
    protected double mEndLatitude ;
    protected double mEndLongitude ;


    protected AMapNaviView mAMapNaviView;

    public BaseFragment(){

    }

    protected static Bundle getBundle(double startLat , double startLong , double endLat , double endLong){
        Bundle args = new Bundle();
        args.putDouble(START_LATITUDE , startLat);
        args.putDouble(START_LONGITUDE , startLong);
        args.putDouble(END_LATITUDE , endLat);
        args.putDouble(END_LONGITUDE , endLong);
        return args;
    }

    protected void initData(){
        if (getArguments() != null) {
            mStartLatitude = getArguments().getDouble(START_LATITUDE);
            mStartLongitude = getArguments().getDouble(START_LONGITUDE);
            mEndLatitude = getArguments().getDouble(END_LATITUDE);
            mEndLongitude = getArguments().getDouble(END_LONGITUDE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(getClass().getName(),"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(getClass().getName(),"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(getClass().getName(),"onActivityCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(getClass().getName(),"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(getClass().getName(),"onResume");
        if(mAMapNaviView != null)mAMapNaviView.onResume();
    }

    @Override
    public void onPause() {
        if(mAMapNaviView != null)mAMapNaviView.onPause();
        Log.e(getClass().getName(),"onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(getClass().getName(),"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(getClass().getName(),"onDestroyView");
    }

    @Override
    public void onDestroy() {
        Log.e(getClass().getName(),"onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(getClass().getName(),"onDetach");
    }

    @Override
    public void onNaviSetting() {

    }

    @Override
    public void onNaviCancel() {

    }

    @Override
    public boolean onNaviBackClick() {
        return false;
    }

    @Override
    public void onNaviMapMode(int i) {

    }

    @Override
    public void onNaviTurnClick() {

    }

    @Override
    public void onNextRoadClick() {

    }

    @Override
    public void onScanViewButtonClick() {

    }

    @Override
    public void onLockMap(boolean b) {

    }

    @Override
    public void onNaviViewLoaded() {

    }


    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }
}
