package com.zyyoona7.easypopup.easypop;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.popup.BasePopup;

/**
 * Created by zyyoona7 on 2018/3/12.
 *
 * PopupWindow 中存在 EditText 隐藏键盘方法不起作用，只有 toggle 键盘方法才起作用
 * 注：建议由 EditText 需求的弹窗使用 DialogFragment
 */
public class CmmtPopup extends BasePopup<CmmtPopup> {

    private View.OnClickListener mCancelListener;
    private View.OnClickListener mOkListener;
    AppCompatTextView mCancelTv;
    AppCompatTextView mOkTv;
    AppCompatEditText mEditText;

    public static CmmtPopup create(Context context) {
        return new CmmtPopup(context);
    }

    public CmmtPopup(Context context) {
        setContext(context);
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.layout_cmmt, ViewGroup.LayoutParams.MATCH_PARENT, SizeUtils.dp2px(150));
        setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setAnimationStyle(R.style.BottomPopAnim)
                .setDimValue(0.5f)
                .setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED)
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    protected void initViews(View view, CmmtPopup basePopup) {

        mCancelTv = findViewById(R.id.tv_cancel);
        mOkTv = findViewById(R.id.tv_ok);
        mEditText = findViewById(R.id.et_cmmt);
        mCancelTv.setOnClickListener(mCancelListener);
        mOkTv.setOnClickListener(mOkListener);
    }

    public CmmtPopup setOnCancelClickListener(View.OnClickListener listener) {
        mCancelListener = listener;
        return this;
    }

    public CmmtPopup setOnOkClickListener(View.OnClickListener listener) {
        mOkListener = listener;
        return this;
    }

    public CmmtPopup showSoftInput() {
        if (mEditText != null) {
            mEditText.post(new Runnable() {
                @Override
                public void run() {
                    KeyboardUtils.showSoftInput(mEditText);
                }
            });
        }
        return this;
    }

    public CmmtPopup hideSoftInput() {
        if (mEditText != null) {
            mEditText.post(new Runnable() {
                @Override
                public void run() {
                        KeyboardUtils.hideSoftInput(mEditText);
                }
            });
        }
        return this;
    }
}


