package com.yoona.popwindow;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;


public class PopupWindowUtil {
    protected final View anchor;
    private final PopupWindow window;
    private View root;
    private Drawable background = null;
    private final WindowManager windowManager;
    //是否设置了透明
    private boolean isAlpha = false;
    private ValueAnimator valueAnimator = null;
    private boolean mOutsideTouchable=true;

    public PopupWindowUtil(View anchor) {
        this.anchor = anchor;
        this.window = new MPopupWindow(anchor.getContext());

        // 点击window以外，自动关闭
        this.window.setTouchInterceptor(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int x = (int) event.getX();
                final int y = (int) event.getY();
                if ((event.getAction() == MotionEvent.ACTION_DOWN)
                        && ((x < 0) || (x >= window.getWidth()) || (y < 0) || (y >= window.getHeight()))) {
                    dismiss();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });

        this.windowManager = (WindowManager) this.anchor.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        onCreate();
    }


    /**
     * Anything you want to have happen when created. Probably should create a
     * view and setup the event listeners on child views.
     */
    protected void onCreate() {
    }

    /**
     * In case there is stuff to do right before displaying.
     */
    protected void onShow() {
    }

    private void preShow() {
        if (this.root == null) {
            throw new IllegalStateException(
                    "setContentView was not called with a view to display.");
        }
        onShow();

        if (this.background == null) {
            this.window.setBackgroundDrawable(new BitmapDrawable());
        } else {
            this.window.setBackgroundDrawable(this.background);
        }

        this.window.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        this.window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.window.setTouchable(true);
        this.window.setFocusable(true);
        this.window.setOutsideTouchable(mOutsideTouchable);

        this.window.setContentView(this.root);
        // Common.setGray((Activity) anchor.getContext());
    }

    /**
     * 宽度填满屏幕，准备show
     */
    private void preShowWeithScreen() {
        if (this.root == null) {
            throw new IllegalStateException(
                    "setContentView was not called with a view to display.");
        }
        onShow();

        if (this.background == null) {
            this.window.setBackgroundDrawable(new BitmapDrawable());
        } else {
            this.window.setBackgroundDrawable(this.background);
        }

        this.window.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.window.setTouchable(true);
        this.window.setFocusable(true);
        this.window.setOutsideTouchable(mOutsideTouchable);

        this.window.setContentView(this.root);
        // Common.setGray((Activity) anchor.getContext());
    }

    /**
     * 高度填满屏幕，准备show
     */
    private void preShowHeightScreen() {
        if (this.root == null) {
            throw new IllegalStateException(
                    "setContentView was not called with a view to display.");
        }
        onShow();

        if (this.background == null) {
            this.window.setBackgroundDrawable(new BitmapDrawable());
        } else {
            this.window.setBackgroundDrawable(this.background);
        }

        this.window.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        this.window.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        this.window.setTouchable(true);
        this.window.setFocusable(true);
        this.window.setOutsideTouchable(mOutsideTouchable);

        this.window.setContentView(this.root);
        // Common.setGray((Activity) anchor.getContext());
    }

    /**
     * 横向铺满全屏，带有半透明效果，准备show
     */
    private void preShowAllScreen() {
        if (this.root == null) {
            throw new IllegalStateException(
                    "setContentView was not called with a view to display.");
        }
        onShow();

        if (this.background == null) {
            this.window.setBackgroundDrawable(new BitmapDrawable());
        } else {
            this.window.setBackgroundDrawable(this.background);
        }

        dimBackgroundWithAnimation(1.0f, 0.5f);
        this.window.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.window.setTouchable(true);
        this.window.setFocusable(true);
        this.window.setOutsideTouchable(mOutsideTouchable);

        this.window.setContentView(this.root);
        // Common.setGray((Activity) anchor.getContext());
    }

    /**
     * 自适应view大小，带有半透明效果，准备show
     */
    private void preShowAlpha() {

        if (this.root == null) {
            throw new IllegalStateException(
                    "setContentView was not called with a view to display.");
        }
        onShow();

        if (this.background == null) {
            this.window.setBackgroundDrawable(new BitmapDrawable());
        } else {
            this.window.setBackgroundDrawable(this.background);
        }

        dimBackgroundWithAnimation(1.0f, 0.5f);
        this.window.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        this.window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.window.setTouchable(true);
        this.window.setFocusable(true);
        this.window.setOutsideTouchable(mOutsideTouchable);

        this.window.setContentView(this.root);
        // Common.setGray((Activity) anchor.getContext());
    }

    /**
     * findViewById方法
     *
     * @param id
     * @return
     */
    public View findId(int id) {
        if (this.root == null) {
            throw new IllegalStateException(
                    "setContentView was not called with a view to display.");
        }
        return this.root.findViewById(id);
    }


    /**
     * 设置popupWindow背景颜色
     *
     * @param background
     */
    public void setBackgroundDrawable(Drawable background) {
        this.background = background;
    }

    public void setOutsideTouchable(boolean touchable){
        this.mOutsideTouchable=touchable;
    }

    /**
     * 调整窗口的透明度(无动画)
     *
     * @param bgDim
     * @param bgAlpha
     */
    private void dimBackground(float bgDim, float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) this.anchor.getContext()).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        lp.dimAmount = bgDim;
        ((Activity) this.anchor.getContext()).getWindow().setAttributes(lp);
        ((Activity) this.anchor.getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        isAlpha = true;
    }

    /**
     * 调整窗口的透明度(有动画)
     *
     * @param from>=0&&from<=1.0f
     * @param to>=0&&to<=1.0f
     */
    private void dimBackgroundWithAnimation(final float from, final float to) {
        final Window window = ((Activity) this.anchor.getContext()).getWindow();
        ((Activity) this.anchor.getContext()).getWindow().setDimAmount(from);
        ((Activity) this.anchor.getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.alpha = (Float) animation.getAnimatedValue();
                window.setAttributes(params);
            }
        });

        valueAnimator.start();
        isAlpha = true;
    }


    /**
     * Sets the content view. Probably should be called from onCreate
     *
     * @param root the view the popup will display
     */
    public void setContentView(View root) {
        this.root = root;
        this.window.setContentView(root);
    }

    /**
     * Will inflate and set the view from a resource id
     *
     * @param layoutResID
     */
    public void setContentView(int layoutResID) {
        LayoutInflater inflator = (LayoutInflater) this.anchor.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.setContentView(inflator.inflate(layoutResID, null));
    }

    /**
     * If you want to do anything when dismiss is called
     *
     * @param listener
     */
    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        this.window.setOnDismissListener(listener);
    }

    /**
     * Displays like a popdown menu from the anchor view
     * 在anchor view的左下方弹出
     */
    public void showLikePopDownLeftMenu() {
        this.showLikePopDownLeftMenu(0, 0);
    }

    /**
     * Displays like a popdown menu from the anchor view
     * anchor view右下方弹出
     */
    public void showLikePopDownRightMenu() {
        this.root.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        this.showLikePopDownRightMenu(this.anchor.getWidth() - this.root.getMeasuredWidth(), 0);
    }

    /**
     * Displays like a popdown menu from the anchor view.
     *
     * @param xOffset offset in X direction
     * @param yOffset offset in Y direction
     */
    public void showLikePopDownLeftMenu(int xOffset, int yOffset) {
        this.preShow();

        this.window.setAnimationStyle(R.style.PopDownLeftMenu);
        this.window.showAsDropDown(this.anchor, xOffset, yOffset);
    }

    /**
     * Displays like a popdown menu from the anchor view.
     *
     * @param xOffset offset in X direction
     * @param yOffset offset in Y direction
     */
    public void showLikePopDownRightMenu(int xOffset, int yOffset) {
        this.preShow();
        this.window.setAnimationStyle(R.style.PopDownRightMenu);
        this.window.showAsDropDown(this.anchor, xOffset, yOffset);
    }

    /**
     * Displays like a QuickAction from the anchor view.
     * 自己判断相对控件的位置，默认在相对控件的上面弹出
     * 当相对控件的高度小于pop的高度时，在下面弹出
     */
    public void showLikeQuickAction() {
        this.showLikeQuickAction(0, 0);
    }

    /**
     * Displays like a QuickAction from the anchor view.
     *
     * @param xOffset offset in the X direction
     * @param yOffset offset in the Y direction
     */
    public void showLikeQuickAction(int xOffset, int yOffset) {
        this.preShow();

        this.window.setAnimationStyle(R.style.GrowFromBottom);

        int[] location = new int[2];
        this.anchor.getLocationOnScreen(location);

        Rect anchorRect = new Rect(location[0], location[1], location[0]
                + this.anchor.getWidth(), location[1] + this.anchor.getHeight());

        this.root.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        int rootWidth = this.root.getMeasuredWidth();
        int rootHeight = this.root.getMeasuredHeight();

        int screenWidth = this.windowManager.getDefaultDisplay().getWidth();

        int xPos = ((screenWidth - rootWidth) / 2) + xOffset;
        int yPos = anchorRect.top - rootHeight + yOffset;

        // display on bottom
        if (rootHeight > anchorRect.top) {
            yPos = anchorRect.bottom + yOffset;
            this.window.setAnimationStyle(R.style.GrowFromTop);
        }

        this.window.showAtLocation(this.anchor, Gravity.NO_GRAVITY, xPos, yPos);
    }

    /**
     * 在中间显示
     */
    public void showCenter() {
        this.preShow();
        this.window.setAnimationStyle(R.style.GrowFromCenter);
        this.window.showAtLocation(this.anchor, Gravity.CENTER, 0, 0);

    }

    /**
     * 带有半透明效果的显示
     * 显示在底部
     */
    public void showBottomWithAlpha() {
        this.preShowAllScreen();
        Rect rect = new Rect();
        ((Activity) this.anchor.getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int winHeight = ((Activity) this.anchor.getContext()).getWindow().getDecorView().getHeight();
        this.window.setAnimationStyle(R.style.PushInBottom);
        this.window.showAtLocation(this.anchor, Gravity.BOTTOM | Gravity.LEFT, 0, winHeight - rect.bottom);
    }

    /**
     * 带有半透明效果的显示
     * 显示在中间
     */
    public void showCenterWithAlpha() {
        this.preShowAlpha();
        this.window.setAnimationStyle(R.style.GrowFromCenter);
        this.window.showAtLocation(this.anchor, Gravity.CENTER, 0, 0);
    }

    /**
     * 显示底部
     * 横向铺满全屏
     */
    public void showBottom() {
        this.preShowWeithScreen();
        Rect rect = new Rect();
        ((Activity) this.anchor.getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int winHeight = ((Activity) this.anchor.getContext()).getWindow().getDecorView().getHeight();
        this.window.setAnimationStyle(R.style.PushInBottom);
        this.window.showAtLocation(this.anchor, Gravity.BOTTOM | Gravity.LEFT, 0, winHeight - rect.bottom);
    }

    /**
     * 显示顶部
     * 横向铺满全屏
     */
    public void showTop() {
        this.preShowWeithScreen();
        this.window.setAnimationStyle(R.style.PushInTop);
        int result = 0;
        int resourceId = this.anchor.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = this.anchor.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        this.window.showAtLocation(this.anchor, Gravity.TOP | Gravity.LEFT, 0, result);
    }

    /**
     * 显示顶部(带透明效果)
     * 横向铺满全屏
     */
    public void showTopWithAlpha() {
        this.preShowAllScreen();
        this.window.setAnimationStyle(R.style.PushInTop);
        int result = 0;
        int resourceId = this.anchor.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = this.anchor.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        this.window.showAtLocation(this.anchor, Gravity.TOP | Gravity.LEFT, 0, result);
    }


    /**
     * 关闭popupWindow
     */
    public void dismiss() {
        this.window.dismiss();
    }


    class MPopupWindow extends PopupWindow {
        public MPopupWindow(Context context) {
            super(context, null);
        }

        public MPopupWindow(View contentView) {
            super(contentView, 0, 0);
        }

        @Override
        public void dismiss() {
            if (isAlpha) {
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    valueAnimator.cancel();
                }
                dimBackground(0.0f, 1.0f);
                isAlpha = false;
            }
            super.dismiss();
        }
    }
}