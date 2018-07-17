package com.zyyoona7.easypopup.easypop;

import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.easypopup.base.BaseActivity;
import com.zyyoona7.easypopup.views.TitleBar;
import com.zyyoona7.easypopup.views.TriangleDrawable;
import com.zyyoona7.popup.BasePopup;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

public class EasyPopActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "EasyPopActivity";

    private TitleBar mTitleBar;

    private Button mCircleBtn;
    private Button mAboveBtn;
    private Button mRightBtn;
    private Button mBgDimBtn;
    private Button mAnyBgDimBtn;
    private Button mGiftBtn;
    private Button mCmmtBtn;
    private Button mComplexBtn;
    private AppCompatTextView mEverywhereTv;

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

    private EverywherePopup mEverywherePopup;
    private float mLastX;
    private float mLastY;

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
        mEverywhereTv = findViewById(R.id.tv_pop_everywhere);
        initQQPop();
        initWeiboPop();
        initCirclePop();
        initAbovePop();
        initBgDimPop();
        initAnyBgDimPop();
        initGiftPop();
        initCmmtPop();
        initComplexPop();

        mEverywherePopup=EverywherePopup.create(this)
                .apply();

        mEverywhereTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mLastX = event.getRawX();
                    mLastY = event.getRawY();
                    LogUtils.i("onTouch x=" + mLastX + ",y=" + mLastY);
                }
                return false;
            }
        });
        mEverywhereTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mEverywherePopup.showEverywhere(v,(int)mLastX,(int)mLastY);
                return true;
            }
        });
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
                .setAnimationStyle(R.style.RightTop2PopAnim)
                .setOnViewListener(new EasyPopup.OnViewListener() {
                    @Override
                    public void initViews(View view, BasePopup basePopup) {
                        View arrowView = view.findViewById(R.id.v_arrow);
                        arrowView.setBackground(new TriangleDrawable(TriangleDrawable.TOP, Color.parseColor("#88FF88")));
                    }
                })
                .setFocusAndOutsideEnable(true)
//                .setBackgroundDimEnable(true)
//                .setDimValue(0.5f)
//                .setDimColor(Color.RED)
//                .setDimView(mTitleBar)
                .apply();

    }

    private void showQQPop(View view) {
        int offsetX = SizeUtils.dp2px(20) - view.getWidth() / 2;
        int offsetY = (mTitleBar.getHeight() - view.getHeight()) / 2;
        mQQPop.showAtAnchorView(view, YGravity.BELOW, XGravity.ALIGN_RIGHT, offsetX, offsetY);
    }

    private void initWeiboPop() {
        mWeiboPop = EasyPopup.create()
                .setContentView(this, R.layout.layout_center_pop)
                .setAnimationStyle(R.style.TopPopAnim)
                .setOnViewListener(new EasyPopup.OnViewListener() {
                    @Override
                    public void initViews(View view, BasePopup basePopup) {
                        View arrowView = view.findViewById(R.id.v_arrow_weibo);
                        arrowView.setBackground(new TriangleDrawable(TriangleDrawable.TOP, Color.WHITE));
                    }
                })
                .setFocusAndOutsideEnable(true)
                .apply();
    }

    private void showWeiboPop(View view) {
        int offsetY = (mTitleBar.getHeight() - view.getHeight()) / 2;
        mWeiboPop.showAtAnchorView(view, YGravity.BELOW, XGravity.CENTER, 0, offsetY);
    }

    private void initCirclePop() {
        mCirclePop = EasyPopup.create()
                .setContentView(this, R.layout.layout_circle_comment)
                .setAnimationStyle(R.style.RightPopAnim)
                .setFocusAndOutsideEnable(true)
                .setOnViewListener(new EasyPopup.OnViewListener() {
                    @Override
                    public void initViews(View view, final BasePopup popup) {
                        view.findViewById(R.id.tv_zan).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShort("赞");
                                popup.dismiss();
                            }
                        });
                        view.findViewById(R.id.tv_comment).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShort("评论");
                                popup.dismiss();
                            }
                        });
                    }
                })
                .apply();

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
//        mComplexPopup.showAtAnchorView(view, YGravity.ABOVE, XGravity.LEFT);
        mComplexPopup.showAtLocation(view,Gravity.BOTTOM,0,0);
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
