package com.example.mr_do.guide.bus_route;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.mr_do.guide.R;
import com.example.mr_do.guide.customize_view.TopBar;
import com.example.mr_do.guide.guide.WalkFragment;

/**
 * 公交车站点之间的步行导航
 * Created by Mr_Do on 2017/7/31.
 */

public class BusToWalkActivity extends AppCompatActivity {

    private double mStartLatitude;
    private double mStartLongitide;
    private double mEndLatitude;
    private double mEndLongitide;
    private TopBar mTopBar;
    public static final String START_LAT = "START_LAT";
    public static final String START_LONG = "START_LONG";
    public static final String END_LAT = "END_LAT";
    public static final String END_LONG = "END_LONG";
    private static final double DEFALT = 0xfff;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_to_walk);
        mTopBar = (TopBar) findViewById(R.id.top_bar);
        mTopBar.setBackEnable(true);
        mTopBar.setOnBackClick(new TopBar.OnBackClick() {
            @Override
            public void onClick() {
                mTopBar.setBackEnable(false);
                finish();
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.walk_guide);
        mStartLatitude = getIntent().getDoubleExtra(START_LAT ,DEFALT);
        mStartLongitide = getIntent().getDoubleExtra(START_LONG , DEFALT);
        mEndLatitude = getIntent().getDoubleExtra(END_LAT , DEFALT);
        mEndLongitide = getIntent().getDoubleExtra(END_LONG , DEFALT);
        if(fragment == null) {
            fragmentManager.
                    beginTransaction().
                    add(R.id.walk_guide, WalkFragment.newInstance(
                            mStartLatitude,
                            mStartLongitide,
                            mEndLatitude,
                            mEndLongitide
                    )).
                    commit();
        }
    }
}
