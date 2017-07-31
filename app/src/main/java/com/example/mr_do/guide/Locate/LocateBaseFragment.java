package com.example.mr_do.guide.Locate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.MapView;

/**
 * Created by Mr_Do on 2017/7/27.
 */

public class LocateBaseFragment extends Fragment {
    protected MapView mMapView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mMapView != null){
            mMapView.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onPause() {
        if(mMapView != null){
            mMapView.onPause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    public void onDestroy() {
        if(mMapView != null){
            mMapView.onDestroy();
        }
        super.onDestroy();
    }

}
