package com.example.mr_do.guide.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.example.mr_do.guide.R;
import com.example.mr_do.guide.SingleFragmentActivity;
import com.example.mr_do.guide.utils.Sha1Util;

public class LoginActivity extends SingleFragmentActivity {
    @Override
    protected Fragment newFragment() {

        //通过Log输出Sha1码和PackageName,方便测试
        Log.e("SHA_1", Sha1Util.getSHA1(getApplicationContext()));
        Log.e("PackageName",getPackageName());

        return LoginFragment.newInstance();
    }
}
