package com.jetwey.skin_core.skin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jetwey.skin_core.utils.SkinThemeUtils;

import java.lang.reflect.Field;
import java.util.HashMap;

public
        /**
         *Package com.jetwey.skin_core.skin
         *Author  Dimen
         *Create by Dimen on  2021/12/31
         *Version:1.0
         *Describe:
         */
class SkinActivityLifecycle  implements Application.ActivityLifecycleCallbacks {
    HashMap<Activity , SkinLayoutFactory> factoryHashMap = new HashMap<>();
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        /**
         *  更新状态栏
         */
        SkinThemeUtils.updataStatusBarColor(activity);

        /**
         * 更新字体
         */
        Typeface skinTypeface = SkinThemeUtils.getSkinTypeface(activity);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        try {
            Field mFactorySet = LayoutInflater.class.getDeclaredField("mFactorySet");
            mFactorySet.setAccessible(true);
            mFactorySet.setBoolean(layoutInflater, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //添加自定义创建view 工厂
        SkinLayoutFactory skinLayoutFactory = new SkinLayoutFactory(activity,skinTypeface);
        layoutInflater.setFactory2(skinLayoutFactory);

        //注册观察者
        SkinManager.getInstance().addObserver(skinLayoutFactory);
        factoryHashMap.put(activity, skinLayoutFactory);
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
        //删除观察者
        SkinLayoutFactory remove = factoryHashMap.remove(activity);
        SkinManager.getInstance().deleteObserver(remove);
    }
}
