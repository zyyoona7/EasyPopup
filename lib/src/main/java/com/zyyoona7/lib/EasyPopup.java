package com.zyyoona7.lib;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v4.widget.PopupWindowCompat;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.PopupWindow;

/**
 * Created by zyyoona7 on 2017/8/3.
 * <p>
 * PopupWindow封装
 */

public class EasyPopup implements PopupWindow.OnDismissListener {

    private static final float DEFAULT_DIM = 0.7f;

    //PopupWindow对象
    private PopupWindow mPopupWindow;

    //context
    private Context mContext;
    //contentView
    private View mContentView;
    //布局id
    private int mLayoutId;
    //获取焦点
    private boolean mFocusable;
    //是否触摸之外dismiss
    private boolean mOutsideTouchable;

    //宽高
    private int mWidth;
    private int mHeight;

    private int mAnimationStyle;

    private PopupWindow.OnDismissListener mOnDismissListener;

    //弹出pop时，背景是否变暗
    private boolean isBackgroundDim;

    //背景变暗时透明度
    private float mDimValue = DEFAULT_DIM;
    //背景变暗颜色
    @ColorInt
    private int mDimColor = Color.BLACK;

    //背景变暗的view
    @NonNull
    private ViewGroup mDimView;

    /**
     * Views indexed with their IDs
     */
    private final SparseArray<View> mViews;

    private EasyPopup(Context context) {
        this.mContext = context;
        mViews = new SparseArray<>();
    }

    private void createPopup() {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow();
        }

        if (mContentView == null) {
            if (mLayoutId != 0) {
                mContentView = LayoutInflater.from(mContext).inflate(mLayoutId, null);
            } else {
                throw new IllegalArgumentException("The content view is null");
            }
        }
        mPopupWindow.setContentView(mContentView);

        if (mWidth != 0) {
            mPopupWindow.setWidth(mWidth);
        } else {
            mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        if (mHeight != 0) {
            mPopupWindow.setHeight(mHeight);
        } else {
            mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        if (mAnimationStyle != 0) {
            mPopupWindow.setAnimationStyle(mAnimationStyle);
        }

        mPopupWindow.setFocusable(mFocusable);
        mPopupWindow.setOutsideTouchable(mOutsideTouchable);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mPopupWindow.setOnDismissListener(this);
    }

    /**
     * PopupWindow自带的显示方法
     *
     * @param anchor
     * @param offsetX
     * @param offsetY
     */
    public void showAsDropDown(View anchor, int offsetX, int offsetY) {
        if (mPopupWindow != null) {
            handleBackgroundDim();
            mPopupWindow.showAsDropDown(anchor, offsetX, offsetY);
        }
    }

    public void showAsDropDown(View anchor) {
        if (mPopupWindow != null) {
            handleBackgroundDim();
            mPopupWindow.showAsDropDown(anchor);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showAsDropDown(View anchor, int offsetX, int offsetY, int gravity) {
        if (mPopupWindow != null) {
            handleBackgroundDim();
            PopupWindowCompat.showAsDropDown(mPopupWindow, anchor, offsetX, offsetY, gravity);
        }
    }

    public void showAtLocation(View parent, int gravity, int offsetX, int offsetY) {
        if (mPopupWindow != null) {
            handleBackgroundDim();
            mPopupWindow.showAtLocation(parent, gravity, offsetX, offsetY);
        }
    }

    /**
     * 相对anchor view显示，适用 宽高不为match_parent
     *
     * @param anchor
     * @param vertPos
     * @param horizPos
     */
    public void showAtAnchorView(@NonNull View anchor, @VerticalGravity int vertPos, @HorizontalGravity int horizPos) {
        showAtAnchorView(anchor, vertPos, horizPos, 0, 0);
    }

    /**
     * 相对anchor view显示，适用 宽高不为match_parent
     *
     * @param anchor
     * @param vertPos
     * @param horizPos
     * @param x
     * @param y
     */
    public void showAtAnchorView(@NonNull View anchor, @VerticalGravity int vertPos, @HorizontalGravity int horizPos, int x, int y) {
        if (mPopupWindow == null) {
            return;
        }
        //处理背景变暗
        handleBackgroundDim();
        View contentView = getContentView();
        contentView.measure(0, View.MeasureSpec.UNSPECIFIED);
        final int measuredW = contentView.getMeasuredWidth();
        final int measuredH = contentView.getMeasuredHeight();
        switch (vertPos) {
            case VerticalGravity.ABOVE:
                //anchor view之上
                y -= measuredH + anchor.getHeight();
                break;
            case VerticalGravity.ALIGN_BOTTOM:
                //anchor view底部对齐
                y -= measuredH;
                break;
            case VerticalGravity.CENTER:
                //anchor view垂直居中
                y -= anchor.getHeight() / 2 + measuredH / 2;
                break;
            case VerticalGravity.ALIGN_TOP:
                //anchor view顶部对齐
                y -= anchor.getHeight();
                break;
            case VerticalGravity.BELOW:
                //anchor view之下
                // Default position.
                break;
        }
        switch (horizPos) {
            case HorizontalGravity.LEFT:
                //anchor view左侧
                x -= measuredW;
                break;
            case HorizontalGravity.ALIGN_RIGHT:
                //与anchor view右边对齐
                x -= measuredW - anchor.getWidth();
                break;
            case HorizontalGravity.CENTER:
                //anchor view水平居中
                x += anchor.getWidth() / 2 - measuredW / 2;
                break;
            case HorizontalGravity.ALIGN_LEFT:
                //与anchor view左边对齐
                // Default position.
                break;
            case HorizontalGravity.RIGHT:
                //anchor view右侧
                x += anchor.getWidth();
                break;
        }
        PopupWindowCompat.showAsDropDown(mPopupWindow, anchor, x, y, Gravity.NO_GRAVITY);
    }

    /**
     * 设置监听器
     *
     * @param listener
     */
    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        this.mOnDismissListener = listener;
    }

    /**
     * 处理背景变暗
     */
    private void handleBackgroundDim() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (isBackgroundDim) {
                if (mDimView != null) {
                    applyDim(mDimView);
                } else {
                    if (getContentView() != null) {
                        Activity activity = (Activity) getContentView().getContext();
                        if (activity != null) {
                            applyDim(activity);
                        }
                    }
                }
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void applyDim(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.getWindow().getDecorView().getRootView();
        //activity跟布局
//        ViewGroup parent = (ViewGroup) parent1.getChildAt(0);
        Drawable dim = new ColorDrawable(mDimColor);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * mDimValue));
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void applyDim(ViewGroup dimView) {
        ViewGroup parent = dimView;
        Drawable dim = new ColorDrawable(mDimColor);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * mDimValue));
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }

    /**
     * 清除背景变暗
     */
    private void clearBackgroundDim() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (isBackgroundDim) {
                if (mDimView != null) {
                    clearDim(mDimView);
                } else {
                    if (getContentView() != null) {
                        Activity activity = (Activity) getContentView().getContext();
                        if (activity != null) {
                            clearDim(activity);
                        }
                    }
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void clearDim(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.getWindow().getDecorView().getRootView();
        //activity跟布局
//        ViewGroup parent = (ViewGroup) parent1.getChildAt(0);
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void clearDim(ViewGroup dimView) {
        ViewGroup parent = dimView;
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }

    /**
     * 获取PopupWindow中加载的view
     *
     * @return
     */
    public View getContentView() {
        if (mPopupWindow != null) {
            return mPopupWindow.getContentView();
        } else {
            return null;
        }
    }

    /**
     * 获取PopupWindow对象
     *
     * @return
     */
    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    /**
     * 获取view
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null && getContentView() != null) {
            view = getContentView().findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    @Override
    public void onDismiss() {
        handleDismiss();
    }

    /**
     * PopupWindow消失后处理一些逻辑
     */
    private void handleDismiss() {
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss();
        }

        //清除背景变暗
        clearBackgroundDim();
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * Builder class
     */
    public static class Builder {

        private EasyPopup mEasyPopup;

        public Builder(Context context) {
            mEasyPopup = new EasyPopup(context);
        }

        public Builder setContentView(@NonNull View contentView) {
            mEasyPopup.mContentView = contentView;
            mEasyPopup.mLayoutId = 0;
            return this;
        }

        public Builder setContentView(@LayoutRes int layoutId) {
            mEasyPopup.mContentView = null;
            mEasyPopup.mLayoutId = layoutId;
            return this;
        }

        public Builder setContentView(@NonNull View contentView, int width, int height) {
            mEasyPopup.mContentView = contentView;
            mEasyPopup.mLayoutId = 0;
            mEasyPopup.mWidth = width;
            mEasyPopup.mHeight = height;
            return this;
        }

        public Builder setContentView(@LayoutRes int layoutId, int width, int height) {
            mEasyPopup.mContentView = null;
            mEasyPopup.mLayoutId = layoutId;
            mEasyPopup.mWidth = width;
            mEasyPopup.mHeight = height;
            return this;
        }

        public Builder setWidth(int width) {
            mEasyPopup.mWidth = width;
            return this;
        }

        public Builder setHeight(int height) {
            mEasyPopup.mHeight = height;
            return this;
        }

        public Builder setAnimationStyle(@StyleRes int animationStyle) {
            mEasyPopup.mAnimationStyle = animationStyle;
            return this;
        }

        public Builder setFocusable(boolean focusable) {
            mEasyPopup.mFocusable = focusable;
            return this;
        }

        public Builder setOutsideTouchable(boolean outsideTouchable) {
            mEasyPopup.mOutsideTouchable = outsideTouchable;
            return this;
        }

        public Builder setOnDismissListener(PopupWindow.OnDismissListener listener) {
            mEasyPopup.mOnDismissListener = listener;
            return this;
        }

        /**
         * 背景变暗支持api>=18
         *
         * @param isDim
         * @return
         */
        public Builder setBackgroundDimEnable(boolean isDim) {
            mEasyPopup.isBackgroundDim = isDim;
            return this;
        }

        public Builder setDimValue(@FloatRange(from = 0.0f, to = 1.0f) float dimValue) {
            mEasyPopup.mDimValue = dimValue;
            return this;
        }

        public Builder setDimColor(@ColorInt int color) {
            mEasyPopup.mDimColor = color;
            return this;
        }

        public Builder setDimView(@NonNull ViewGroup dimView) {
            mEasyPopup.mDimView = dimView;
            return this;
        }

        public EasyPopup build() {
            mEasyPopup.createPopup();
            return mEasyPopup;
        }
    }
}
