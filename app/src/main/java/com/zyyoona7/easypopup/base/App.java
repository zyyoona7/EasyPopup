package com.zyyoona7.easypopup.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by zyyoona7 on 2017/8/3.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // init it in the function of onCreate in ur Application
        Utils.init(this);

    }
}
