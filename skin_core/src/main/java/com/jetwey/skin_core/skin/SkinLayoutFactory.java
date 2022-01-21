package com.jetwey.skin_core.skin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jetwey.skin_core.utils.SkinThemeUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;


public
        /**
         *Package com.jetwey.skin_core.skin
         *Author  Dimen
         *Create by Dimen on  2021/12/31
         *Version:1.0
         *Describe:
         */
class SkinLayoutFactory implements LayoutInflater.Factory2, Observer {
    private static final String[] mClassPrefixList = {
            "android.widget.",
            "android.view.",
            "android.webkit."
    };
    private static final Class<?>[] mConstructorSignature = new Class[]{Context.class, AttributeSet.class};
    private static final HashMap<String, Constructor<? extends View>> mConstructorMap =
            new HashMap<String, Constructor<? extends View>>();
   // private static final Map<String, Constructor<? extends View>> mConstructorMap = new ArrayMap<>();
    //属性处理类
    private SkinAttribute mSkinAttribute;


    private Activity activity;
    public SkinLayoutFactory(Activity activity, Typeface skinTypeface) {
        this.activity = activity;
        mSkinAttribute = new SkinAttribute(skinTypeface);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

        //反射classloader
        View view = createViewFromTag(name, context, attrs);
        //自定义的控件
        if (null == view) {
            view = createView(name, context, attrs);
        }
        //筛选符合属性的View
        mSkinAttribute.load(view,attrs);
        return view;
    }

    private View createViewFromTag(String name, Context context, AttributeSet attrs) {
        //包含自定义控件处理
        if (-1 != name.indexOf(".")) {
            return null;
        }
        View view = null;
        for (int i = 0; i < mClassPrefixList.length; i++) {
            view = createView(mClassPrefixList[i] + name, context, attrs);
            if (null != view) {
                break;

            }
        }
        return view;
    }

    private View createView(String name, Context context, AttributeSet attrs) {
        Constructor<? extends View> constructor = mConstructorMap.get(name);
        if (constructor == null) {
            try {
                //通过全类名获取class
                Class<? extends View> clazz = context.getClassLoader().loadClass(name).asSubclass(View.class);

                //获取构造方法
                constructor = clazz.getConstructor(mConstructorSignature);
                mConstructorMap.put(name, constructor);
               // constructor.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (null != constructor) {
            try {
                return constructor.newInstance(context, attrs);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return null;
    }


    @Override
    public void update(Observable o, Object arg) {
        //更新状态栏
        SkinThemeUtils.updataStatusBarColor(activity);
        //更新字体
        Typeface skinTypeface = SkinThemeUtils.getSkinTypeface(activity);
        mSkinAttribute.setTypeface(skinTypeface);
        //更换皮肤
        mSkinAttribute.applySkin();
    }
}
