package com.example.mr_do.guide.guide;

/**
 * Created by Mr_Do on 2017/7/27.
 */

public interface IGuideView {

    double getStartLatitude();

    double geStartLongitude();

    double getEndLatitude();

    double geEndLongitude();

    void showCalculating();

    void finishCalculating();

    void calculateError();

    void startGuide();

    void stopGuide();

}
