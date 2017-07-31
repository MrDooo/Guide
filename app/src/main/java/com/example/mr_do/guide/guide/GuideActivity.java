package com.example.mr_do.guide.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.example.mr_do.guide.R;
import com.example.mr_do.guide.bus_route.BusRouteFragment;
import com.example.mr_do.guide.customize_view.TopBar;

import java.util.ArrayList;

import static com.example.mr_do.guide.guide.BaseFragment.END_LATITUDE;
import static com.example.mr_do.guide.guide.BaseFragment.END_LONGITUDE;
import static com.example.mr_do.guide.guide.BaseFragment.START_LATITUDE;
import static com.example.mr_do.guide.guide.BaseFragment.START_LONGITUDE;

/**
 * Created by Mr_Do on 2017/7/27.
 */

public class GuideActivity extends AppCompatActivity{

    private static final String[] mTitle = {"Walk" ,"Drive" , "Bus" };
    private double mStartLatitude ;
    private double mStartLongitude ;
    private double mEndLatitude ;
    private double mEndLongitude ;
    private TabLayout mTabLayout;
    private TopBar mTopBar;
    private FragmentManager mFragmentManager;
    private ArrayList<Fragment> mFragments;
    private Fragment mFragment;
    private int mFragmentPosition = 0;
    private static  final String FRAGMENT_POSITION = "FRAGMENT_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
            mFragmentPosition = savedInstanceState.getInt(FRAGMENT_POSITION , 0);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_guide);
        mFragmentManager = getSupportFragmentManager();
        initData(getIntent());
        initView();
        Log.e(getClass().getName() , "onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(FRAGMENT_POSITION, mFragmentPosition);
    }

    private void initData(Intent intent){
        mStartLatitude = getDoubleExtra(intent , START_LATITUDE);
        mEndLatitude = getDoubleExtra(intent , END_LATITUDE);
        mStartLongitude = getDoubleExtra(intent , START_LONGITUDE);
        mEndLongitude = getDoubleExtra(intent , END_LONGITUDE);
        mFragments = new ArrayList<>();
        mFragments.add(WalkFragment.newInstance(mStartLatitude , mStartLongitude , mEndLatitude , mEndLongitude));
        mFragments.add(DriveFragment.newInstance(mStartLatitude , mStartLongitude , mEndLatitude , mEndLongitude));
        mFragments.add(BusRouteFragment.newInstance(mStartLatitude , mStartLongitude , mEndLatitude , mEndLongitude));
    }

    private double getDoubleExtra(Intent intent ,String tag){
        return intent.getDoubleExtra(tag , 0);
    }

    private void initView(){
        mFragment = mFragmentManager.findFragmentById(R.id.guide_fragment_container );
        if(mFragment == null){
            mFragment = mFragments.get(0);
            mFragmentManager.beginTransaction().add(R.id.guide_fragment_container , mFragment).commit();
        }

        mTopBar = (TopBar) findViewById(R.id.top_bar);
        mTopBar.setBackEnable(true);
        mTopBar.setOnBackClick(new TopBar.OnBackClick() {
            @Override
            public void onClick() {
                mTopBar.setBackEnable(false);
                finish();
            }
        });

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitle[2]));
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(mFragment != null){
                    mFragmentManager.beginTransaction().
                            replace(R.id.guide_fragment_container  , mFragments.get(tab.getPosition())).commit();
                    mFragmentPosition = tab.getPosition();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.getTabAt(mFragmentPosition).select();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(getClass().getName() , "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(getClass().getName() , "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(getClass().getName() , "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(getClass().getName() , "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(getClass().getName() , "onDestroy");
    }
}
