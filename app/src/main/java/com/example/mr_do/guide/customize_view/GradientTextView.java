package com.example.mr_do.guide.customize_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;

/**
 * 变色的TextView
 * Created by Mr_Do on 2017/7/19.
 */

public class GradientTextView extends android.support.v7.widget.AppCompatTextView {

    private int mViewWidth = 0;
    private TextPaint mPaint;
    private LinearGradient mLinerTGradient;
    private Matrix mGradientMatrix;
    private int mTranslate = 0;

    public GradientTextView(Context context) {
        super(context);
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth == 0){
            mViewWidth = getMeasuredWidth();
            if(mViewWidth > 0){
                mPaint = getPaint();
                mLinerTGradient = new LinearGradient(
                        -mViewWidth,
                        0,
                        0,
                        0,
                        new int[]{Color.BLUE , Color.WHITE , Color.RED},//这里改变颜色
                        null , Shader.TileMode.CLAMP);
                mPaint.setShader(mLinerTGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mGradientMatrix != null){
            mTranslate += mViewWidth/15;
            if(mTranslate > mViewWidth){
                mTranslate -= mViewWidth;
            }
        }
        mGradientMatrix.setTranslate(mTranslate , 0);
        mLinerTGradient.setLocalMatrix(mGradientMatrix);
        postInvalidateDelayed(100);
    }
}
