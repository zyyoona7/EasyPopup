package com.zyyoona7.easypopup.easypop;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.blankj.utilcode.util.ScreenUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.popup.BasePopup;

public class EverywherePopup extends BasePopup<EverywherePopup> {

    public static EverywherePopup create(Context context) {
        return new EverywherePopup(context);
    }

    private EverywherePopup(Context context) {
        setContext(context);
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.layout_everywhere_pop)
                .setAnimationStyle(R.style.LeftTopPopAnim);
    }

    @Override
    protected void initViews(View view, EverywherePopup basePopup) {

//        setOnRealWHAlreadyListener(new OnRealWHAlreadyListener() {
//            @Override
//            public void onRealWHAlready(BasePopup basePopup, int popWidth, int popHeight, int anchorW, int anchorH) {
//
//            }
//        });
    }

    /**
     * 自适应触摸点 弹出
     * @param parent
     * @param touchX
     * @param touchY
     * @return
     */
    public EverywherePopup showEverywhere(View parent,int touchX, int touchY) {
//        if (isRealWHAlready()) {
            int screenHeight = ScreenUtils.getScreenHeight();
            int screenWidth = ScreenUtils.getScreenWidth();
            int offsetX=touchX;
            int offsetY=touchY;
            if (touchX<getWidth() && screenHeight-touchY<getHeight()){
                //左下弹出动画
                getPopupWindow().setAnimationStyle(R.style.LeftBottomPopAnim);
                offsetY=touchY-getHeight();
            }else if (touchX+getWidth()>screenWidth && touchY+getHeight()>screenHeight){
                //右下弹出动画
                getPopupWindow().setAnimationStyle(R.style.RightBottomPopAnim);
                offsetX=(touchX-getWidth());
                offsetY=touchY-getHeight();
            }else if (touchX+getWidth()>screenWidth){
                getPopupWindow().setAnimationStyle(R.style.RightTopPopAnim);
                offsetX=(touchX-getWidth());
            }else {
                getPopupWindow().setAnimationStyle(R.style.LeftTopPopAnim);
            }

            showAtLocation(parent, Gravity.NO_GRAVITY,offsetX,offsetY);
//        }
        return this;
    }
}
