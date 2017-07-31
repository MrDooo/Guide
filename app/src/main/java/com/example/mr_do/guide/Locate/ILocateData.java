package com.example.mr_do.guide.Locate;

import android.content.Context;
import android.location.Location;

import com.amap.api.location.AMapLocation;
import com.autonavi.ae.gmap.maploader.ERROR_CODE;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public interface ILocateData {

    interface GetLocationCallBack{

        /**
         * 从后台获取机器定位信息成功则回调
         * @param latitude
         * @param longitude
         */
        void onLocatedMachineSuccess(double latitude , double longitude);

        /**
         * 获取手机定位成功回调
         * @param location
         */
        void onLocatedPhoneSuccess(AMapLocation location);

        /**
         * 获取机器定位信息失败时回调
         * @param error_code
         */
        void onLocateMachineFailed(int error_code);

        /**
         * 获取手机定位信息失败时回调
         */
        void onLocatePhoneFailed(int error_code);
    }

    void getMachineLocation(GetLocationCallBack callBack);

    void getPhoneLocation(Context context , GetLocationCallBack callBack);
}
