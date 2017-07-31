package com.example.mr_do.guide.Locate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.example.mr_do.guide.R;
import com.example.mr_do.guide.customize_view.TopBar;
import com.example.mr_do.guide.utils.ConstantUtil;
import com.example.mr_do.guide.utils.ToastUtil;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public class LocateFragment extends LocateBaseFragment implements ILocateView {

    private View mView;
    private TopBar mTopBar;
    private MapView mMapView;
    private LocationPresenter mPresenter;
    private Button mRefreshButton;
    private Button mGuideButton;

    //地图显示配置
    private static final int ZOOM_LEVEL = 17; //缩放比例
    private static final int TILT = 0; //目标可视区域的倾斜度
    private static final int BEARING = 0; //可视区域指向的方向
    private static final int MOVE_DURATION = 2000; //定位动画播放时间

    public static LocateFragment newInstance() {
        Bundle args = new Bundle();
        LocateFragment fragment = new LocateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_locate, container , false);

        mMapView = mView.findViewById(R.id.map_view);
        mMapView.onCreate(savedInstanceState);

        mPresenter = new LocationPresenter(this);
        mPresenter.locateMachine(ZOOM_LEVEL , TILT , BEARING , MOVE_DURATION);
        mPresenter.locatePhone(getActivity());

        mTopBar = mView.findViewById(R.id.top_bar);
        mTopBar.setBackEnable(true);
        mTopBar.setOnBackClick(new TopBar.OnBackClick() {
            @Override
            public void onClick() {
                mPresenter.back();
            }
        });

        mRefreshButton = mView.findViewById(R.id.refresh_button);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.locateMachine(ZOOM_LEVEL,TILT,BEARING , MOVE_DURATION);
            }
        });

        mGuideButton = mView.findViewById(R.id.guide_button);
        mGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.startGuide(getActivity());
            }
        });

        super.onCreateView(inflater , container , savedInstanceState);
        return mView;
    }

    @Override
    public void showLocating() {

    }

    @Override
    public void finishLocating() {

    }

    @Override
    public void back() {
        if(isAdded() && mTopBar != null){
            mTopBar.setBackEnable(false);
           getActivity().finish();
        }
    }

    @Override
    public void locate(CameraUpdate cameraUpdate, long moveDuration) {
        if(mMapView != null) {
            mMapView.getMap().clear();
            mMapView.getMap().animateCamera(cameraUpdate, moveDuration, null);
        }
    }

    @Override
    public void addMark(double latitude, double longitude) {
        if(mMapView != null) {
            mMapView.getMap().addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
        }
    }

    @Override
    public void showFailed(int error_code) {
        ToastUtil.show(getContext() , ConstantUtil.parseGuideErrorCode(error_code));
    }

    @Override
    public void startGuide(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void setGuideButtonEnable(boolean isEnable) {
        mGuideButton.setEnabled(isEnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        mGuideButton.setEnabled(true);
    }
}
