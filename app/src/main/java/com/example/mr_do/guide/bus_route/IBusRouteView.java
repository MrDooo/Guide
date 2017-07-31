package com.example.mr_do.guide.bus_route;

import com.amap.api.services.route.BusRouteResult;

/**
 * Created by Mr_Do on 2017/7/30.
 */

public interface IBusRouteView {

    double getStartLatitude();

    double geStartLongitude();

    double getEndLatitude();

    double geEndLongitude();

    void showCalculating();

    void finishCalculating();

    void calculateError(int error_code);

    void showBusRoute(BusRouteResult routeResult);
}
