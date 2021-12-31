package com.jetwey.skin_core.skin;

import android.app.Application;

public
        /**
         *Package com.jetwey.skin_core.skin
         *Author  Dimen
         *Create by Dimen on  2021/12/31
         *Version:1.0
         *Describe:
         */
class SkinManager {
    private static SkinManager mSkinManger;
    private Application mApplication;

    private SkinManager(Application application) {
        mApplication = application;
        /**
         * 提供了一个应用生命周期回调的注册方法，
         *          * 用来对应用的生命周期进行集中管理，
         *  这个接口叫registerActivityLifecycleCallbacks，可以通过它注册
         *          * 自己的ActivityLifeCycleCallback，每一个Activity的生命周期都会回调到这里的对应方法。
         */
        mApplication.registerActivityLifecycleCallbacks(new SkinActivityLifecycle());
    }

    public static void init(Application application) {
        synchronized (SkinManager.class) {
            if (null == mSkinManger) {
                mSkinManger = new SkinManager(application);
            }
        }
    }
}
