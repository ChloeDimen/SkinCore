package com.jetwey.skin_core.skin;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public
        /**
         *Package com.jetwey.skin_core.skin
         *Author  Dimen
         *Create by Dimen on  2021/12/31
         *Version:1.0
         *Describe:
         */
class SkinActivityLifecycle  implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);

        //添加自定义创建view 工厂
        SkinLayoutFactory skinLayoutFactory = new SkinLayoutFactory();
        layoutInflater.setFactory2(skinLayoutFactory);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
