package com.example.mr_do.guide.utils;

import android.content.Context;
import android.util.Log;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

/**
 * Created by Mr_Do on 2017/7/11.
 */

public class GPSUtil {
    public interface onSearchedListener {
        void onSearched(RegeocodeResult regeocodeResult , int error_code);//对反编码结果的处理
    }
    public static void getAddress(Context content ,
                                  double latitude ,
                                  double longitude,
                                  final onSearchedListener searchedListener){
        Log.e("POSITION",latitude+" "+longitude);
        GeocodeSearch search = new GeocodeSearch(content);
        GeocodeSearch.OnGeocodeSearchListener  listener = new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                searchedListener.onSearched(regeocodeResult , i);
            }
            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

            }
        };
        search.setOnGeocodeSearchListener(listener);
        LatLonPoint latLonPoint = new LatLonPoint(latitude ,longitude);
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,GeocodeSearch.AMAP);
        search.getFromLocationAsyn(query);
    }
}
