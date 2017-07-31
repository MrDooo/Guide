package com.example.mr_do.guide.bus_route;

import com.amap.api.services.route.BusRouteResult;

/**
 * Created by Mr_Do on 2017/7/30.
 */

public interface IBusRouteData {

    interface RouteCallBack{
        /**
         * 获取城市编码错误时回调
         * @param error_code
         */
        void onGetCityCodeFailed(int error_code);

        /**
         * 获取规划方案成功时回调
         * @param routeSearch
         */
        void onGetRouteSuccess(BusRouteResult routeSearch);

        /**
         * 获取规划方案失败时回调
         * @param error_code
         */
        void onGetRouteFailed(int error_code);
    }

    /**
     * 计算出规划方案
     * @param startLat
     * @param startLong
     * @param endLat
     * @param endLong
     */
    void calculateBusRoute(double startLat , double startLong , double endLat , double endLong);
}
