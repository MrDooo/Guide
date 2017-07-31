package com.example.mr_do.guide.login;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public class LoginPresenter {
    private ILoginView mLoginView;
    private ILoginData mData;

    public LoginPresenter(ILoginView loginView ){
        mLoginView = loginView;
        mData = new LoginData();
    }


    public void login(){
        mData.sendUserMessage(new ILoginData.NetRequestCallBack() {
            @Override
            public void onSuccess() {
                mLoginView.clearEditText();
                mLoginView.startNextActivity();
            }

            @Override
            public void onFailed() {
                mLoginView.showFailError("未定义");
            }
        });

    }
}
