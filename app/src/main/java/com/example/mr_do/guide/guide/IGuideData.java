package com.example.mr_do.guide.guide;

import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.model.NaviLatLng;

import java.util.List;

/**
 * Created by Mr_Do on 2017/7/28.
 */

public interface IGuideData {

    interface OnCalculateRouteCallBack{
        void onInitSuccess();
        void onCalculateSuccess();
        void onCalculateFailed();
    }

    /**
     * 为导航的计算做好准备工作
     * @param calculateRouteCallBack
     */
    void calculateForNavi(OnCalculateRouteCallBack calculateRouteCallBack);

    /**
     * 计算出步行导航路线
     * @param startLatLng
     * @param endLatLng
     */
    void calculateWalk(NaviLatLng startLatLng , NaviLatLng endLatLng);

    /**
     * 计算出驾车导航路线
     * @param startLatLngs
     * @param endLatLngs
     */
    void calculateDrive(List<NaviLatLng> startLatLngs , List<NaviLatLng> endLatLngs);
}
