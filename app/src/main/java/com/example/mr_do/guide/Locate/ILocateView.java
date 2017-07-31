package com.example.mr_do.guide.Locate;

import android.content.Intent;

import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.model.LatLng;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public interface ILocateView {

    /**
     * 显示正在获取后台定位信息对话框
     */
    void showLocating();

    /**
     * 关闭正在获取后台定位信息对话框
     */
    void finishLocating();

    /**
     * 返回
     */
    void back();

    /**
     * 地图移动到传入的位置
     * @param cameraUpdate
     * @param moveDuration
     */
    void locate(CameraUpdate cameraUpdate, long moveDuration);

    /**
     * 在指定位置添加锚点
     * @param latitude
     * @param longitude
     */
    void addMark(double latitude , double longitude);

    /**
     * 通知用户获取后台信息失败或定位本机位置失败
     */
    void showFailed(int error_code);

    /**
     * 开启导航页面
     */
    void startGuide(Intent intent);

    /**
     * 禁用导航按钮
     */
    void setGuideButtonEnable(boolean isEnable);
}
