package com.example.mr_do.guide.bus_route;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.BusStep;
import com.example.mr_do.guide.R;
import com.example.mr_do.guide.customize_view.LoadingDialog;
import com.example.mr_do.guide.customize_view.TopBar;
import com.example.mr_do.guide.guide.BaseFragment;
import com.example.mr_do.guide.utils.AMapUtil;
import com.example.mr_do.guide.utils.ConstantUtil;
import com.example.mr_do.guide.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr_Do on 2017/7/30.
 */

public class BusRouteFragment extends BaseFragment implements IBusRouteView {

    private LoadingDialog mLoadingDialog;
    private View mView;
    private RecyclerView mRecyclerView;
    private RecyclerView mDetailRecyclerView;
    private BusRoutePresenter mPresenter;
    private List<BusPath> mBusList = new ArrayList<>();
    private List<SchemeBusStep> mSchemeBusStepList = new ArrayList<>();
    private BusRouteAdapter mAdapter;
    private PopupWindow mPopupWindow;
    private TopBar mPopTop;
    private View mPopupLayout;
    private List<BusStep> mStepList = new ArrayList<>();

    public static BusRouteFragment newInstance(double startLat ,
                                           double startLong ,
                                           double endLat,
                                           double endLong) {
       BusRouteFragment fragment = new BusRouteFragment();

        Bundle args = getBundle(
                startLat ,
                startLong ,
                endLat ,
                endLong);

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initData();
        mView = inflater.inflate(R.layout.fragment_bus , container , false);

        mPopupLayout = inflater.inflate(R.layout.path_detail_layout , container , false);
        mPopupWindow = new PopupWindow(mPopupLayout, ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setBackgroundDrawable(getActivity().getDrawable(R.color.colorWhite));
        mDetailRecyclerView = mPopupLayout.findViewById(R.id.detail_list);
        mDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDetailRecyclerView.setAdapter(mDetailAdapter);
        mDetailRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mPopTop = mPopupLayout.findViewById(R.id.pop_top);
        mPopTop.setBackEnable(true);
        mPopTop.setOnBackClick(new TopBar.OnBackClick() {
            @Override
            public void onClick() {
                mPopupWindow.dismiss();
            }
        });

        mLoadingDialog = new LoadingDialog(getActivity() , R.style.Dialog);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mRecyclerView = mView.findViewById(R.id.bus_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BusRouteAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mPresenter = new BusRoutePresenter(getActivity() , this);
        mPresenter.calculateBusRoute();
        return mView;
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
    public void calculateError(int error_code) {
        if(isAdded()){
            ToastUtil.show(getActivity() , ConstantUtil.parseBusErrorCode(error_code));
        }
    }

    @Override
    public void showBusRoute(BusRouteResult routeResult) {
        mBusList = routeResult.getPaths();
        mAdapter.notifyDataSetChanged();
    }

    //用于记录有多少种公交车乘坐方案可以到达目的地的RecyclerView
    private class BusRouteHolder extends RecyclerView.ViewHolder{
        private TextView mPathTextView;
        private TextView mDetailTextView;
        public BusRouteHolder(View itemView) {
            super(itemView);
            mPathTextView = itemView.findViewById(R.id.item_path_text_title);
            mDetailTextView = itemView.findViewById(R.id.item_path_text_detail);
        }

        public void bindView(final BusPath busPath){
            mPathTextView.setText(AMapUtil.getBusPathTitle(busPath));
            mDetailTextView.setText(AMapUtil.getBusPathDes(busPath));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mStepList = busPath.getSteps();
                    mSchemeBusStepList.clear();
                    initDetailData();
                    mPopupWindow.showAtLocation(mView , Gravity.CENTER , 0 , 0);
                }
            });
        }
    }

    private class BusRouteAdapter extends RecyclerView.Adapter<BusRouteHolder>{

        @Override
        public BusRouteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(isAdded()) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_path , parent , false);
                return new BusRouteHolder(view);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(BusRouteHolder holder, int position) {
            holder.bindView(mBusList.get(position));
        }

        @Override
        public int getItemCount() {
            return mBusList.size();
        }
    }

    private RecyclerView.Adapter<StepViewHolder> mDetailAdapter = new RecyclerView.Adapter<StepViewHolder>() {
        @Override
        public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_step ,parent, false);
            return new StepViewHolder(view);

        }

        @Override
        public void onBindViewHolder(StepViewHolder holder, int position) {
            holder.bindView(mSchemeBusStepList.get(position));
        }

        @Override
        public int getItemCount() {
            return mSchemeBusStepList.size();
        }
    };


    //用于记录每种公交车方案的详细做法的RecyclerView
    private class StepViewHolder extends RecyclerView.ViewHolder{
        private TextView mStepTextView;
        public StepViewHolder(View itemView) {
            super(itemView);
            mStepTextView = itemView.findViewById(R.id.step_text);
        }
        public void bindView(final SchemeBusStep step){
            if(mSchemeBusStepList.get(0).equals(step)){
                mStepTextView.setText("出发");
            }else if(mSchemeBusStepList.get(mSchemeBusStepList.size()-1).equals(step)){
                mStepTextView.setText("到达终点");
            }else {
                if (step.isWalk() && step.getWalk() != null && step.getWalk().getDistance() > 0) {
                    mStepTextView.setText("步行 " + step.getWalk().getDistance() + " 米"+"     "+"跟我走");
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity() , BusToWalkActivity.class);
                            intent.putExtra(BusToWalkActivity.START_LAT , step.getWalk().getOrigin().getLatitude());
                            intent.putExtra(BusToWalkActivity.START_LONG , step.getWalk().getOrigin().getLongitude());
                            intent.putExtra(BusToWalkActivity.END_LAT , step.getWalk().getDestination().getLatitude());
                            intent.putExtra(BusToWalkActivity.END_LONG , step.getWalk().getDestination().getLongitude());
                            startActivity(intent);
                        }
                    });
                } else if(step.isBus() && step.getBusLines().size()>0){
                    mStepTextView.setText(step.getBusLines().get(0).getBusLineName()+"   "
                            +(step.getBusLines().get(0).getPassStationNum() + 1) + "站");
                } else if (step.isRailway() && step.getRailway() != null){
                    mStepTextView.setText(step.getRailway().getName()
                            +(step.getRailway().getViastops().size() + 1) + "站");
                }else if(step.isTaxi() && step.getTaxi() != null){
                    mStepTextView.setText("打车到终点");
                }
            }
        }
    }

    private void initDetailData(){
        SchemeBusStep start = new SchemeBusStep(null);
        start.setStart(true);
        mSchemeBusStepList.add(start);
        if(mStepList != null) {
            for (BusStep step : mStepList) {
                if (step.getWalk() != null && step.getWalk().getDistance() > 0) {
                    SchemeBusStep walk = new SchemeBusStep(step);
                    walk.setWalk(true);
                    mSchemeBusStepList.add(walk);
                }
                if (step.getBusLine() != null) {
                    SchemeBusStep bus = new SchemeBusStep(step);
                    bus.setBus(true);
                    mSchemeBusStepList.add(bus);
                }
                if (step.getRailway() != null) {
                    SchemeBusStep railway = new SchemeBusStep(step);
                    railway.setRailway(true);
                    mSchemeBusStepList.add(railway);
                }

                if (step.getTaxi() != null) {
                    SchemeBusStep taxi = new SchemeBusStep(step);
                    taxi.setTaxi(true);
                    mSchemeBusStepList.add(taxi);
                }
            }
            SchemeBusStep end = new SchemeBusStep(null);
            end.setEnd(true);
            mSchemeBusStepList.add(end);
        }
    }
}
