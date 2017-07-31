package com.example.mr_do.guide.search_location;

import android.support.v4.app.Fragment;

import com.example.mr_do.guide.SingleFragmentActivity;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public class SearchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment newFragment() {
        return SearchFragment.newInstance();
    }

}
