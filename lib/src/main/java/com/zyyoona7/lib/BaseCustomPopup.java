package com.zyyoona7.lib;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.transition.Transition;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zyyoona7 on 2017/8/4.
 * 自定义PopupWindow使用
 */

public abstract class BaseCustomPopup extends EasyPopup {

    protected BaseCustomPopup(Context context) {
        super(context);
    }

    @Override
    protected void onPopupWindowCreated() {
        super.onPopupWindowCreated();
        //执行设置PopupWindow属性也可以通过Builder中设置
        //setContentView(x,x,x);
        //...
        initAttributes();
    }

    @Override
    protected void onPopupWindowViewCreated(View contentView) {
        super.onPopupWindowViewCreated(contentView);
        initViews(contentView);
    }

    @Override
    protected void onPopupWindowDismiss() {
        super.onPopupWindowDismiss();
    }

    /****设置属性方法****/

    protected void setContentView(View contentView) {
        this.mContentView = contentView;
        this.mLayoutId = 0;
    }

    protected void setContentView(@LayoutRes int layoutId) {
        this.mContentView = null;
        this.mLayoutId = layoutId;
    }

    protected void setContentView(View contentView, int width, int height) {
        this.mContentView = contentView;
        this.mLayoutId = 0;
        this.mWidth = width;
        this.mHeight = height;
    }

    protected void setContentView(@LayoutRes int layoutId, int width, int height) {
        this.mContentView = null;
        this.mLayoutId = layoutId;
        this.mWidth = width;
        this.mHeight = height;
    }

    protected void setAnimationStyle(@StyleRes int animationStyle) {
        this.mAnimationStyle = animationStyle;
    }

    protected void setFocusable(boolean focusable) {
        this.mFocusable = focusable;
    }

    protected void setOutsideTouchable(boolean outsideTouchable) {
        this.mOutsideTouchable = outsideTouchable;
    }

    /**
     * 背景变暗支持api>=18
     *
     * @param isDim
     * @return
     */
    protected void setBackgroundDimEnable(boolean isDim) {
        this.isBackgroundDim = isDim;
    }

    protected void setDimValue(@FloatRange(from = 0.0f, to = 1.0f) float dimValue) {
        this.mDimValue = dimValue;
    }

    protected void setDimColor(@ColorInt int color) {
        this.mDimColor = color;
    }

    protected void setDimView(@NonNull ViewGroup dimView) {
        this.mDimView = dimView;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void setEnterTransition(Transition enterTransition) {
        this.mEnterTransition = enterTransition;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void setExitTransition(Transition exitTransition) {
        this.mExitTransition = exitTransition;
    }

    /**
     * 初始化view {@see getView()}
     *
     * @param view
     */
    protected abstract void initViews(View view);

    /**
     * 可以在此方法中设置PopupWindow需要的属性
     */
    protected abstract void initAttributes();
}
