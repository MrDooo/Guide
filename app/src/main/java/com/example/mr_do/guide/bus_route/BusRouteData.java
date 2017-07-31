package com.example.mr_do.guide.bus_route;

import android.content.Context;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.example.mr_do.guide.utils.GPSUtil;

/**
 * Created by Mr_Do on 2017/7/30.
 */

public class BusRouteData implements IBusRouteData{

    private Context mContext;
    private RouteSearch mRouteSearch;
    private String mCityCode = null;
    private RouteCallBack mCallBack;

    public BusRouteData(Context context , RouteCallBack callBack){
        mContext = context;
        mCallBack = callBack;
        mRouteSearch = new RouteSearch(context);
        mRouteSearch.setRouteSearchListener(mRouteListener);
    }

    private RouteSearch.OnRouteSearchListener mRouteListener = new RouteSearch.OnRouteSearchListener() {
        @Override
        public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
            if(i == 1000){
                mCallBack.onGetRouteSuccess(busRouteResult);
            }else {
                mCallBack.onGetRouteFailed(i);
            }
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
    };



    @Override
    public void calculateBusRoute(final double startLat,
                                  final double startLong,
                                  final double endLat,
                                  final double endLong) {
        GPSUtil.getAddress(mContext,startLat, startLong, new GPSUtil.onSearchedListener() {
            @Override
            public void onSearched(RegeocodeResult regeocodeResult, int error_code) {
                if(error_code == 1000){
                    mCityCode = regeocodeResult.getRegeocodeAddress().getCityCode();
                    RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                            new LatLonPoint(startLat , startLong) ,
                            new LatLonPoint(endLat , endLong));
                    RouteSearch.BusRouteQuery query
                            = new RouteSearch.BusRouteQuery(
                                    fromAndTo ,
                            RouteSearch.BUS_DEFAULT ,
                            mCityCode ,
                            0);
                    mRouteSearch.calculateBusRouteAsyn(query);// 异步路径规划BUS模式查询
                }else {
                    mCallBack.onGetCityCodeFailed(error_code);
                }
            }
        });
    }
}
