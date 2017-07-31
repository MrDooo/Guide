package com.example.mr_do.guide.customize_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.mr_do.guide.R;
import com.example.mr_do.guide.utils.DensityUtil;

/**
 * 头部标题栏
 * Created by Mr_Do on 2017/7/26.
 */

public class TopBar extends RelativeLayout {

    public interface OnBackClick{
        void onClick();
    }

    public void setBackEnable(boolean b){
        if(mBackButton != null){
            mBackButton.setEnabled(b);
        }
    }

    private Context mContext;
    private ImageView mIcon;
    private Button mBackButton;
    private TextView mTitle;
    private OnBackClick mBackClick;

    public void setOnBackClick(OnBackClick onBackClick){
        mBackClick = onBackClick;
    }

    public TopBar(Context context) {
        super(context);
        mContext = context;
        initView(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context);
    }

    private void initView(Context context){
        mIcon = new ImageView(context);
        mBackButton = new Button(context);
        mTitle = new TextView(context);

        //add Icon
        mIcon.setImageResource(R.drawable.green_heart);
        mIcon.setBackground(context.getDrawable(R.color.colorRed));
        LayoutParams iconParams = new LayoutParams(px(40) , px(40));
        iconParams.addRule(RelativeLayout.CENTER_VERTICAL , TRUE);
        iconParams.setMargins(px(20) , 0 , 0 ,0);
        addView(mIcon , iconParams);

        //add title
        mTitle.setText("智能定位用具配套APP");
        mTitle.setGravity(Gravity.CENTER);
        mTitle.setBackground(context.getDrawable(R.color.colorWhite));
        mTitle.setTextSize(px(10));
        mTitle.setTextColor(context.getResources().getColor(R.color.colorDark));
        LayoutParams textParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , px(30));
        textParams.addRule(RelativeLayout.CENTER_VERTICAL , TRUE);
        textParams.setMargins(px(70) , 0 , 0 ,0);
        addView(mTitle , textParams);

        //add button
        mBackButton.setText("返回");
        mBackButton.setBackground(null);
        mBackButton.setTextSize(px(8));
        LayoutParams buttonParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , px(30));
        buttonParams.addRule(RelativeLayout.CENTER_VERTICAL , TRUE);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT , TRUE);
        addView(mBackButton , buttonParams);
        mBackButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBackClick != null){
                    mBackClick.onClick();
                }
            }
        });
    }

    private int px(int dp){
        return DensityUtil.dip2px(mContext , dp);
    }
}
