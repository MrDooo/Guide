package com.example.mr_do.guide.customize_view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.mr_do.guide.R;
import com.example.mr_do.guide.utils.DensityUtil;

/**
 * 加载时显示的对话框
 * Created by Mr_Do on 2017/7/29.
 */

public class LoadingDialog extends Dialog {

    private Context mContext;

    public LoadingDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_load , null);
        setContentView(view);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = DensityUtil.dip2px(mContext,160);
        lp.width = DensityUtil.dip2px(mContext,200);
        win.setAttributes(lp);
        setCancelable(false);
    }
}
