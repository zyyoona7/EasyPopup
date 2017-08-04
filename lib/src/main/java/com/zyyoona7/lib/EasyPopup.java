package com.zyyoona7.lib;

import android.content.Context;

/**
 * Created by zyyoona7 on 2017/8/4.
 * 简单的PopupWindow使用
 */

public class EasyPopup extends BaseEasyPopup {

    protected EasyPopup(Context context) {
        super(context);
    }

    public static class Builder extends BaseEasyPopup.Builder<Builder> {

        public Builder(Context context) {
            super(context);
        }

        @Override
        protected BaseEasyPopup createPopup(Context context) {
            return new EasyPopup(context);
        }
    }
}
