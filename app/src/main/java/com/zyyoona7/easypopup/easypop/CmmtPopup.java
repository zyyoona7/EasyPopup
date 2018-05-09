package com.zyyoona7.easypopup.easypop;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.blankj.utilcode.util.SizeUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.lib.BasePopup;

/**
 * Created by zyyoona7 on 2018/3/12.
 */

public class CmmtPopup extends BasePopup<CmmtPopup> {

    private View.OnClickListener mCancelListener;
    private View.OnClickListener mOkListener;
    AppCompatTextView mCancelTv;
    AppCompatTextView mOkTv;

    public static CmmtPopup create(Context context) {
        return new CmmtPopup(context);
    }

    public CmmtPopup(Context context) {
        setContext(context);
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.layout_cmmt, ViewGroup.LayoutParams.MATCH_PARENT, SizeUtils.dp2px(150));
        setFocusAndOutsideEnable(false)
                .setBackgroundDimEnable(true)
                .setDimValue(0.5f);
        getPopupWindow().setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        getPopupWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    protected void initViews(View view) {

        mCancelTv = findViewById(R.id.tv_cancel);
        mOkTv = findViewById(R.id.tv_ok);

    }

    public CmmtPopup setOnCancelClickListener(View.OnClickListener listener) {
        if (mCancelTv == null) {
            return this;
        }
        mCancelTv.setOnClickListener(listener);
        return this;
    }

    public CmmtPopup setOnOkClickListener(View.OnClickListener listener) {
        if (mOkTv == null) {
            return this;
        }
        mOkTv.setOnClickListener(listener);
        return this;
    }
}


