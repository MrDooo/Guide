package com.example.mr_do.guide.Locate;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.mr_do.guide.utils.ConstantUtil;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public class LocateData implements ILocateData{

    private GetLocationCallBack mGetMachineLocationCallBack;
    private GetLocationCallBack mGetPhoneLocationCallBack;

    private class LocationImplements implements AMapLocationListener{
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if(aMapLocation != null){
                if(aMapLocation.getErrorCode() ==
                        ConstantUtil.SUCCESSFUL_LOCATE){
                    mGetPhoneLocationCallBack.onLocatedPhoneSuccess(aMapLocation);
                    return;
                }
            }
            mGetPhoneLocationCallBack.onLocatePhoneFailed(aMapLocation.getErrorCode());
        }
    }

    @Override
    public void getMachineLocation(GetLocationCallBack callBack) {
        // TODO: 2017/7/27 Set success for test
        // TODO: 2017/7/29 这里是拿来获取后台数据的
        mGetMachineLocationCallBack = callBack;
        mGetMachineLocationCallBack.onLocatedMachineSuccess(22.95 , 113.36);
    }

    @Override
    public void getPhoneLocation(Context context , GetLocationCallBack callBack) {
        mGetPhoneLocationCallBack = callBack;
        LocationImplements locationImplements = new LocationImplements();
        final AMapLocationClient locationClient = new AMapLocationClient(context);
        //初始化定位参数
        AMapLocationClientOption locationOption = new AMapLocationClientOption();
        //设置定位监听
        locationClient.setLocationListener(locationImplements);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        locationOption.setInterval(2000);
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        locationClient.startLocation();
    }
}
