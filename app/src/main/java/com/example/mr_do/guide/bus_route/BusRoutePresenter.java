package com.example.mr_do.guide.bus_route;

import android.content.Context;

import com.amap.api.services.route.BusRouteResult;

/**
 * Created by Mr_Do on 2017/7/30.
 */

public class BusRoutePresenter {
    private IBusRouteView mBusRouteView;
    private IBusRouteData mBusRouteData;
    private IBusRouteData.RouteCallBack mRouteCallBack = new IBusRouteData.RouteCallBack() {
        @Override
        public void onGetCityCodeFailed(int error_code) {
            mBusRouteView.finishCalculating();
            mBusRouteView.calculateError(error_code);
        }

        @Override
        public void onGetRouteSuccess(BusRouteResult routeSearch) {
            mBusRouteView.finishCalculating();
            mBusRouteView.showBusRoute(routeSearch);
        }

        @Override
        public void onGetRouteFailed(int error_code) {
            mBusRouteView.finishCalculating();
            mBusRouteView.calculateError(error_code);
        }
    };
    public BusRoutePresenter(Context context ,IBusRouteView busRouteView){
        mBusRouteView = busRouteView;
        mBusRouteData = new BusRouteData(context ,mRouteCallBack);
    }

    public void calculateBusRoute(){
        mBusRouteView.showCalculating();
        mBusRouteData.calculateBusRoute(
                mBusRouteView.getStartLatitude(),
                mBusRouteView.geStartLongitude(),
                mBusRouteView.getEndLatitude(),
                mBusRouteView.geEndLongitude()
        );
    }
}
