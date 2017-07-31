package com.example.mr_do.guide.login;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public interface ILoginData {

    interface NetRequestCallBack{
        void onSuccess();
        void onFailed();
    }

    /**
     * To request the location of the machine
     * @param callBack
     */
    void getSiteMessage(NetRequestCallBack callBack);

    /**
     * Send user's message to the service
     */
    void sendUserMessage(NetRequestCallBack callBack);
}
