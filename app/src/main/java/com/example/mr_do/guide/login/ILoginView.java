package com.example.mr_do.guide.login;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public interface ILoginView {

    /**
     * Get the phone number of user from editText
     * @return
     */
    String getUserPhoneNumber();

    /**
     * Get the machine number from editText
     * @return
     */
    String getMachineNumber();

    /**
     * Get the machine number from editText
     * @return
     */
    String getPassWord();

    /**
     * Clear content of editText
     */
    void clearEditText();

    /**
     * Show loading dialog
     */
    void showLoading();

    /**
     * Hide loading dialog
     */
    void hideLoading();

    /**
     * Turn to next activity
     */
    void startNextActivity();


    /**
     * Notify that upload failed
     * @param reason
     */
    void showFailError(String reason);
}
