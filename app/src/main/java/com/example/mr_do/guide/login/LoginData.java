package com.example.mr_do.guide.login;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public class LoginData implements ILoginData {

    @Override
    public void getSiteMessage(NetRequestCallBack callBack) {

    }

    @Override
    public void sendUserMessage(NetRequestCallBack callBack) {
        callBack.onSuccess();
    }
}
