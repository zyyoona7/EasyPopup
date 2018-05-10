package com.zyyoona7.easypopup.basic;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.blankj.utilcode.util.SizeUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.easypopup.base.BaseActivity;
import com.zyyoona7.easypopup.views.TitleBar;

public class BasicActivity extends BaseActivity {
    private static final String TAG = "BasicActivity";

    private TitleBar mTitleBar;

    private PopupWindow mCiclePop;
    private PopupWindow mQQPop;
    private PopupWindow mWeiboPop;

    private Button mCommentBtn;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_basic;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mTitleBar = (TitleBar) findViewById(R.id.tb_basic);
        mTitleBar.setTile("传统使用");
        mCommentBtn = (Button) findViewById(R.id.btn_comment);
        initCirclePop();
        initQQPop();
        initWeiboPop();
    }

    @Override
    protected void initEvents() {
        mTitleBar.setOnTitleListener(new TitleBar.OnTitleListener() {
            @Override
            public void onLeftClick(View view) {

            }

            @Override
            public void onRightClick(View view) {
                if (mQQPop != null) {
                    mQQPop.showAsDropDown(view, -mQQPop.getContentView().getWidth() + SizeUtils.dp2px(30), 0);
                }
            }

            @Override
            public void onTitleClick(View view) {
                if (mWeiboPop != null) {
                    Log.e(TAG, "onTitleClick: " + view.getWidth());
                    mWeiboPop.showAsDropDown(view, view.getWidth() / 2 - mWeiboPop.getContentView().getWidth() / 2, 0);
                }
            }
        });

        mCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCiclePop != null) {
                    mCiclePop.showAsDropDown(v,-mCiclePop.getContentView().getWidth(),-(mCommentBtn.getHeight()/2+mCiclePop.getContentView().getHeight()/2));
                }
            }
        });
    }

    private void initCirclePop() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_circle_comment, null);
        mCiclePop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mCiclePop.setAnimationStyle(R.style.RightPopAnim);
        mCiclePop.setFocusable(true);
        mCiclePop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mCiclePop.setOutsideTouchable(true);
    }

    private void initQQPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_right_pop, null);
        mQQPop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mQQPop.setAnimationStyle(R.style.RightTopPopAnim);
        mQQPop.setFocusable(true);
        mQQPop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mQQPop.setOutsideTouchable(true);
    }

    private void initWeiboPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_center_pop, null);
        mWeiboPop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mWeiboPop.setAnimationStyle(R.style.TopPopAnim);
        mWeiboPop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mWeiboPop.setFocusable(true);
        mWeiboPop.setOutsideTouchable(false);
    }
}
