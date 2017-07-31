package com.example.mr_do.guide.utils;

import android.view.KeyEvent;

import java.io.IOException;

/**
 * Created by Mr_Do on 2017/7/27.
 */

public class KeyPressUtil {
    public static void pressKeyBack() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
    }
}
