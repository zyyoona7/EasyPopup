package com.zyyoona7.easypopup.easypop;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.easypopup.base.BaseActivity;
import com.zyyoona7.easypopup.views.TitleBar;
import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.XGravity;
import com.zyyoona7.lib.YGravity;

public class EasyPopActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "EasyPopActivity";

    // TODO: 2018/5/9 重做Demo 带箭头的背景，微信长按跟随手指位置的弹窗
    private TitleBar mTitleBar;

    private Button mCircleBtn;
    private Button mAboveBtn;
    private Button mRightBtn;
    private Button mBgDimBtn;
    private Button mAnyBgDimBtn;
    private Button mGiftBtn;
    private Button mCmmtBtn;
    private Button mComplexBtn;

    private LinearLayout mComplexBgDimView;

    private EasyPopup mWeiboPop;
    private EasyPopup mQQPop;
    private EasyPopup mCirclePop;
    private EasyPopup mAbovePop;
    private EasyPopup mBgDimPop;
    private EasyPopup mAnyBgDimPop;
    private GiftPopup mGiftPopup;
    private CmmtPopup mCmmtPopup;
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
        mTitleBar = findViewById(R.id.tb_easy);
        mTitleBar.setTile("Easy Pop");
        mCircleBtn = findViewById(R.id.btn_circle_comment);
        mAboveBtn = findViewById(R.id.btn_above);
        mRightBtn = findViewById(R.id.btn_right);
        mBgDimBtn = findViewById(R.id.btn_bg_dim);
        mAnyBgDimBtn = findViewById(R.id.btn_bg_dim_any);
        mGiftBtn = findViewById(R.id.btn_gift);
        mCmmtBtn = findViewById(R.id.btn_pop_cmmt);
        mComplexBtn = findViewById(R.id.btn_complex);
        mComplexBgDimView = findViewById(R.id.ll_complex_bg_dim);
        initQQPop();
        initWeiboPop();
        initCirclePop();
        initAbovePop();
        initBgDimPop();
        initAnyBgDimPop();
        initGiftPop();
        initCmmtPop();
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
        mCmmtBtn.setOnClickListener(this);
        mComplexBtn.setOnClickListener(this);

    }

    private void initQQPop() {
        mQQPop = EasyPopup.create()
                .setContext(this)
                .setContentView(R.layout.layout_right_pop)
                .setAnimationStyle(R.style.RightTopPopAnim)
                .setFocusAndOutsideEnable(true)
//                .setBackgroundDimEnable(true)
//                .setDimValue(0.5f)
//                .setDimColor(Color.RED)
//                .setDimView(mTitleBar)
                .apply();

    }

    private void showQQPop(View view) {
        mQQPop.showAtAnchorView(view, YGravity.BELOW, XGravity.LEFT, SizeUtils.dp2px(30), 0);
    }

    private void initWeiboPop() {
        mWeiboPop = EasyPopup.create()
                .setContentView(this, R.layout.layout_center_pop)
                .setAnimationStyle(R.style.TopPopAnim)
                .setFocusAndOutsideEnable(true)
                .apply();
    }

    private void showWeiboPop(View view) {
        mWeiboPop.showAtAnchorView(view, YGravity.BELOW, XGravity.CENTER, 0, 0);
    }

    private void initCirclePop() {
        mCirclePop = EasyPopup.create()
                .setContentView(this, R.layout.layout_circle_comment)
                .setAnimationStyle(R.style.RightPopAnim)
                .setFocusAndOutsideEnable(true)
                .apply();
        TextView tvZan = mCirclePop.findViewById(R.id.tv_zan);
        TextView tvComment = mCirclePop.findViewById(R.id.tv_comment);
        tvZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("赞");
                mCirclePop.dismiss();
            }
        });

        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("评论");
                mCirclePop.dismiss();
            }
        });
        mCirclePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.e(TAG, "onDismiss: mCirclePop");
            }
        });
    }

    private void showCirclePop(View view) {
        mCirclePop.showAtAnchorView(view, YGravity.CENTER, XGravity.LEFT, 0, 0);
//        mCirclePop.getPopupWindow().setAnimationStyle(R.style.QQPopAnim);
    }

    private void initAbovePop() {
        mAbovePop = EasyPopup.create()
                .setContentView(this, R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.e(TAG, "onDismiss: mAbovePop");
                    }
                })
                .apply();
    }

    private void showAbovePop(View view) {
        mAbovePop.showAtAnchorView(view, YGravity.ABOVE, XGravity.CENTER);
    }

    private void showRightPop(View view) {
        mAbovePop.showAtAnchorView(view, YGravity.CENTER, XGravity.RIGHT);
    }

    private void initBgDimPop() {
        mBgDimPop = EasyPopup.create()
                .setContentView(this, R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.4f)
                .apply();
    }

    private void showBgDimPop(View view) {
        mBgDimPop.showAtAnchorView(view, YGravity.ALIGN_TOP, XGravity.ALIGN_LEFT);
    }

    private void initAnyBgDimPop() {
        mAnyBgDimPop = EasyPopup.create()
                .setContentView(this, R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.4f)
                .setDimView(mTitleBar)
                .setDimColor(Color.YELLOW)
                .apply();
    }

    private void showAnyBgDimPop(View view) {
        mAnyBgDimPop.showAtAnchorView(view, YGravity.ALIGN_BOTTOM, XGravity.ALIGN_RIGHT);
    }

    private void initGiftPop() {
        mGiftPopup = GiftPopup.create()
                .setContext(this)
                .apply();
    }

    private void showGiftPop(View view) {
        mGiftPopup.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void initComplexPop() {
        mComplexPopup = ComplexPopup.create(this)
                .setDimView(mComplexBgDimView)
                .apply();
    }

    private void showComplexPop(View view) {
        mComplexPopup.showAtAnchorView(view, YGravity.ABOVE, XGravity.LEFT);
    }

    private void initCmmtPop() {
        mCmmtPopup = CmmtPopup.create(this)
                .setOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCmmtPopup.isShowing()) {
                            //无法隐藏输入法。只有toggle方法起作用...
                            KeyboardUtils.hideSoftInput(EasyPopActivity.this);
                            mCmmtPopup.hideSoftInput()
                                    .dismiss();
                        }
                    }
                })
                .setOnOkClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCmmtPopup.isShowing()) {
                            //无法隐藏输入法。只有toggle方法起作用...
                            KeyboardUtils.hideSoftInput(EasyPopActivity.this);
                            mCmmtPopup
                                    .dismiss();
                        }
                    }
                })
                .apply();

    }

    private void showCmmtPop(View view) {
        mCmmtPopup.showSoftInput()
                .showAtLocation(view, Gravity.BOTTOM, 0, 0);
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
                showGiftPop(v);
                break;
            case R.id.btn_pop_cmmt:
                showCmmtPop(v);
                break;
            case R.id.btn_complex:
                showComplexPop(v);
                break;
        }
    }
}
