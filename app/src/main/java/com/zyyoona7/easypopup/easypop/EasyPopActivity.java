package com.zyyoona7.easypopup.easypop;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SizeUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.easypopup.base.BaseActivity;
import com.zyyoona7.easypopup.views.TitleBar;
import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

public class EasyPopActivity extends BaseActivity implements View.OnClickListener {

    private TitleBar mTitleBar;

    private Button mCircleBtn;
    private Button mAboveBtn;
    private Button mRightBtn;
    private Button mBgDimBtn;
    private Button mAnyBgDimBtn;
    private Button mGiftBtn;
    private Button mComplexBtn;

    private LinearLayout mComplexBgDimView;

    private EasyPopup mWeiboPop;
    private EasyPopup mQQPop;
    private EasyPopup mCirclePop;
    private EasyPopup mAbovePop;
    private EasyPopup mBgDimPop;
    private EasyPopup mAnyBgDimPop;
    private ComplexPopup mComplexPopup;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_easy_pop;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mTitleBar = (TitleBar) findViewById(R.id.tb_easy);
        mTitleBar.setTile("Easy Pop");
        mCircleBtn = (Button) findViewById(R.id.btn_circle_comment);
        mAboveBtn = (Button) findViewById(R.id.btn_above);
        mRightBtn = (Button) findViewById(R.id.btn_right);
        mBgDimBtn = (Button) findViewById(R.id.btn_bg_dim);
        mAnyBgDimBtn = (Button) findViewById(R.id.btn_bg_dim_any);
        mGiftBtn = (Button) findViewById(R.id.btn_gift);
        mComplexBtn = (Button) findViewById(R.id.btn_complex);
        mComplexBgDimView = (LinearLayout) findViewById(R.id.ll_complex_bg_dim);
        initQQPop();
        initWeiboPop();
        initCirclePop();
        initAbovePop();
        initBgDimPop();
        initAnyBgDimPop();
        initComplexPop();
    }

    @Override
    protected void initEvents() {
        mTitleBar.setOnTitleListener(new TitleBar.OnTitleListener() {
            @Override
            public void onLeftClick(View view) {
                finish();
            }

            @Override
            public void onRightClick(View view) {
                showQQPop(view);
            }

            @Override
            public void onTitleClick(View view) {
                showWeiboPop(view);
            }
        });
        mCircleBtn.setOnClickListener(this);
        mAboveBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);
        mBgDimBtn.setOnClickListener(this);
        mAnyBgDimBtn.setOnClickListener(this);
        mGiftBtn.setOnClickListener(this);
        mComplexBtn.setOnClickListener(this);

    }

    private void initQQPop() {
        mQQPop = new EasyPopup(this)
                .setContentView(R.layout.layout_right_pop)
                .setAnimationStyle(R.style.QQPopAnim)
                .setFocusAndOutsideEnable(true)
//                .setBackgroundDimEnable(true)
//                .setDimValue(0.5f)
//                .setDimColor(Color.RED)
//                .setDimView(mTitleBar)
                .createPopup();

    }

    private void showQQPop(View view) {
        mQQPop.showAtAnchorView(view, VerticalGravity.BELOW, HorizontalGravity.LEFT, SizeUtils.dp2px(30), 0);
    }

    private void initWeiboPop() {
        mWeiboPop = new EasyPopup(this)
                .setContentView(R.layout.layout_center_pop)
                .setAnimationStyle(R.style.WeiboPopAnim)
                .setFocusAndOutsideEnable(true)
                .createPopup();
    }

    private void showWeiboPop(View view) {
        mWeiboPop.showAtAnchorView(view, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
    }

    private void initCirclePop() {
        mCirclePop = new EasyPopup(this)
                .setContentView(R.layout.layout_circle_comment)
                .setAnimationStyle(R.style.CirclePopAnim)
                .setFocusAndOutsideEnable(true)
                .createPopup();
    }

    private void showCirclePop(View view) {
        mCirclePop.showAtAnchorView(view, VerticalGravity.CENTER, HorizontalGravity.LEFT, 0, 0);
    }

    private void initAbovePop() {
        mAbovePop = new EasyPopup(this)
                .setContentView(R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .createPopup();
    }

    private void showAbovePop(View view) {
        mAbovePop.showAtAnchorView(view, VerticalGravity.ABOVE, HorizontalGravity.CENTER);
    }

    private void showRightPop(View view) {
        mAbovePop.showAtAnchorView(view, VerticalGravity.CENTER, HorizontalGravity.RIGHT);
    }

    private void initBgDimPop() {
        mBgDimPop = new EasyPopup(this)
                .setContentView(R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.4f)
                .createPopup();
    }

    private void showBgDimPop(View view) {
        mBgDimPop.showAtAnchorView(view, VerticalGravity.ALIGN_TOP, HorizontalGravity.ALIGN_LEFT);
    }

    private void initAnyBgDimPop() {
        mAnyBgDimPop = new EasyPopup(this)
                .setContentView(R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.4f)
                .setDimView(mTitleBar)
                .setDimColor(Color.YELLOW)
                .createPopup();
    }

    private void showAnyBgDimPop(View view) {
        mAnyBgDimPop.showAtAnchorView(view, VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.ALIGN_RIGHT);
    }

    private void initComplexPop() {
        mComplexPopup = new ComplexPopup(this);
        mComplexPopup.setBackgroundDimEnable(true)
                .setDimValue(0.5f)
                .setDimView(mComplexBgDimView)
                .createPopup();
    }

    private void showComplexPop(View view) {
        mComplexPopup.showAtAnchorView(view, VerticalGravity.ABOVE, HorizontalGravity.LEFT);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_circle_comment:
                showCirclePop(v);
                break;
            case R.id.btn_above:
                showAbovePop(v);
                break;
            case R.id.btn_right:
                showRightPop(v);
                break;
            case R.id.btn_bg_dim:
                showBgDimPop(v);
                break;
            case R.id.btn_bg_dim_any:
                showAnyBgDimPop(v);
                break;
            case R.id.btn_gift:
                break;
            case R.id.btn_complex:
                showComplexPop(v);
                break;
        }
    }
}
