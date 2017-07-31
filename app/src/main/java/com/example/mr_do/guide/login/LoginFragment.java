package com.example.mr_do.guide.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mr_do.guide.R;
import com.example.mr_do.guide.search_location.SearchActivity;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public class LoginFragment extends Fragment implements ILoginView{

    private RelativeLayout mLayout;
    private ImageView mLogo;
    private TextView mTips;
    private TextInputEditText mPhoneNumberEdit;
    private TextInputEditText mMachineNumberEdit;
    private TextInputEditText mPasswordEdit;
    private float mTouchY;
    private float mLastY;
    private View mView;
    private LoginPresenter mPresenter;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login , container , false);
        mLayout = mView.findViewById(R.id.message_layout);
        mLogo = mView.findViewById(R.id.head_image);
        mTips = mView.findViewById(R.id.title_text);
        mPhoneNumberEdit = mView.findViewById(R.id.phone_number);
        mMachineNumberEdit = mView.findViewById(R.id.machine_number);
        mPasswordEdit = mView.findViewById(R.id.password);
        mPresenter = new LoginPresenter(this);
        mLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mLastY = motionEvent.getRawY();
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mTouchY = motionEvent.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if(mLastY - mTouchY < -100){
                            mPresenter.login();
                        }
                        break;
                }
                return true;
            }
        });
        return mView;
    }

    @Override
    public String getUserPhoneNumber() {
        return mPhoneNumberEdit.getText().toString();
    }

    @Override
    public String getMachineNumber() {
        return mMachineNumberEdit.getText().toString();
    }

    @Override
    public String getPassWord() {
        return mPasswordEdit.getText().toString();
    }

    @Override
    public void clearEditText() {
        mPhoneNumberEdit.setText("");
        mMachineNumberEdit.setText("");
        mPasswordEdit.setText("");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void startNextActivity() {
        if(isAdded()) {
            Pair layout = new Pair<>(mLayout, ViewCompat.getTransitionName(mLayout));
            Pair logo = new Pair<>(mLogo, ViewCompat.getTransitionName(mLogo));
            Pair tips = new Pair<>(mTips, ViewCompat.getTransitionName(mTips));
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), logo, tips, layout);
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent, compat.toBundle());
        }
    }


    @Override
    public void showFailError(String reason) {
        Toast.makeText(getActivity() , reason , Toast.LENGTH_SHORT).show();
    }
}
