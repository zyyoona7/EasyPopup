package com.zyyoona7.popup;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

public class EasyPopup extends BasePopup<EasyPopup> {

    private OnViewListener mOnViewListener;

    public static EasyPopup create() {
        return new EasyPopup();
    }

    public static EasyPopup create(Context context) {
        return new EasyPopup(context);
    }

    public EasyPopup() {

    }

    public EasyPopup(Context context) {
        setContext(context);
    }

    @Override
    protected void initAttributes() {

    }

    @Override
    protected void initViews(View view, EasyPopup popup) {
        if (mOnViewListener != null) {
            mOnViewListener.initViews(view, popup);
        }
    }

    public EasyPopup setOnViewListener(OnViewListener listener) {
        this.mOnViewListener = listener;
        return this;
    }

    public interface OnViewListener {

        void initViews(View view, EasyPopup popup);
    }
}
