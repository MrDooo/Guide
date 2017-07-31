package com.example.mr_do.guide.Locate;


import android.support.v4.app.Fragment;

import com.example.mr_do.guide.SingleFragmentActivity;
import com.example.mr_do.guide.login.LoginFragment;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public class LocateActivity extends SingleFragmentActivity {

    @Override
    protected Fragment newFragment() {
        return LocateFragment.newInstance();
    }
}
