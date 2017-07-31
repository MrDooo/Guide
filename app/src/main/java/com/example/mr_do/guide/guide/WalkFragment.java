package com.example.mr_do.guide.guide;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.enums.NaviType;
import com.example.mr_do.guide.R;
import com.example.mr_do.guide.customize_view.LoadingDialog;
import com.example.mr_do.guide.utils.ToastUtil;


public class WalkFragment extends BaseFragment implements IGuideView{

    private View mView;
    private AMapNavi mAMapNavi;
    private GuidePresenter mPresenter;
    private LoadingDialog mLoadingDialog;

    public static WalkFragment newInstance(double startLat ,
                                           double startLong ,
                                           double endLat,
                                           double endLong) {
        WalkFragment fragment = new WalkFragment();

        Bundle args = getBundle(
                startLat ,
                startLong ,
                endLat ,
                endLong);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        initData();
        mView = inflater.inflate(R.layout.fragment_guide,container ,false);
        mLoadingDialog = new LoadingDialog(getActivity() , R.style.Dialog);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mPresenter = new GuidePresenter(this ,getActivity());
        mAMapNaviView = mView.findViewById(R.id.guide_view);
        mAMapNaviView.onCreate(savedInstanceState);
        mAMapNaviView.setAMapNaviViewListener(this);
        mAMapNavi = AMapNavi.getInstance(getActivity());
        mPresenter.startWalkGuide();
        Log.e(getClass().getName(),"onCreateView");
        return mView;
    }


    @Override
    public void onNaviCancel() {
        if(isAdded()){
            mPresenter.stopGuide();
            getActivity().finish();
        }
    }

    @Override
    public void onDestroy() {
        mPresenter.stopGuide();
        super.onDestroy();
    }


    @Override
    public double getStartLatitude() {
        return mStartLatitude;
    }

    @Override
    public double geStartLongitude() {
        return mStartLongitude;
    }

    @Override
    public double getEndLatitude() {
        return mEndLatitude;
    }

    @Override
    public double geEndLongitude() {
        return mEndLongitude;
    }

    @Override
    public void showCalculating() {
        mLoadingDialog.show();
    }

    @Override
    public void finishCalculating() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void calculateError() {
        if(isAdded()) {
            ToastUtil.show(getActivity(), "路径规划出错");
        }
    }

    @Override
    public void startGuide() {
        mAMapNavi.startNavi(NaviType.GPS);
    }

    @Override
    public void stopGuide() {
        if(mAMapNavi != null){
            mAMapNavi.stopNavi();
            mAMapNavi.destroy();
        }
        if(mAMapNaviView != null){
            mAMapNaviView.onDestroy();
        }
    }
}
