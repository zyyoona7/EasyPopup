package com.zyyoona7.easypopup.easypop;

import android.graphics.Color;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.easypopup.base.BaseActivity;
import com.zyyoona7.easypopup.views.TitleBar;
import com.zyyoona7.lib.BaseEasyPopup;
import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

public class EasyPopActivity extends BaseActivity {

    private TitleBar mTitleBar;

    private BaseEasyPopup mWeiboPop;
    private BaseEasyPopup mQQPop;
    private BaseEasyPopup mCirclePop;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_easy_pop;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mTitleBar= (TitleBar) findViewById(R.id.tb_easy);
        mTitleBar.setTile("Easy Pop");
        initQQPop();
    }

    @Override
    protected void initEvents() {
        mTitleBar.setOnTitleListener(new TitleBar.OnTitleListener() {
            @Override
            public void onLeftClick(View view) {

            }

            @Override
            public void onRightClick(View view) {
                mQQPop.showAtAnchorView(view, VerticalGravity.BELOW, HorizontalGravity.LEFT, SizeUtils.dp2px(30),0);
            }

            @Override
            public void onTitleClick(View view) {

            }
        });
    }

    private void initQQPop(){
        mQQPop=new EasyPopup.Builder(this)
                .setContentView(R.layout.layout_right_pop)
                .setAnimationStyle(R.style.QQPopAnim)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.5f)
                .setDimColor(Color.RED)
                .setDimView(mTitleBar)
                .build();

    }
}
