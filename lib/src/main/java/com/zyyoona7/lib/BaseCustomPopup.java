package com.zyyoona7.lib;

import android.content.Context;
import android.view.View;

/**
 * Created by zyyoona7 on 2017/8/4.
 * 自定义PopupWindow使用
 */

public abstract class BaseCustomPopup extends EasyPopup {
    private static final String TAG = "BaseCustomPopup";

    protected BaseCustomPopup(Context context) {
        super(context);
    }

    @Override
    public void onPopupWindowCreated() {
        super.onPopupWindowCreated();
        //执行设置PopupWindow属性也可以通过Builder中设置
        //setContentView(x,x,x);
        //...
        initAttributes();
    }

    @Override
    public void onPopupWindowViewCreated(View contentView) {
        initViews(contentView);
    }

    @Override
    public void onPopupWindowDismiss() {

    }

    /**
     * 可以在此方法中设置PopupWindow需要的属性
     */
    protected abstract void initAttributes();

    /**
     * 初始化view {@see getView()}
     *
     * @param view
     */
    protected abstract void initViews(View view);


}
