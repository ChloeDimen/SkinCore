package com.jetwey.skincore;

import android.app.Application;

import com.jetwey.skin_core.skin.SkinManager;

public
        /**
         *Package com.jetwey.skincore
         *Author  Dimen
         *Create by Dimen on  2021/12/31
         *Version:1.0
         *Describe:
         */
class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.init(this);
    }
}
