package com.example.mr_do.guide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.mr_do.guide.login.LoginFragment;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public class SingleFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.single_fragment_layout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment == null) {
            fragmentManager.
                    beginTransaction().
                    add(R.id.fragment_container, newFragment()).
                    commit();
        }
    }

    protected Fragment newFragment(){
        return null;
    }
}
