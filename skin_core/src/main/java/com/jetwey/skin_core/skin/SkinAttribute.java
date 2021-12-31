package com.jetwey.skin_core.skin;

import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public
        /**
         *Package com.jetwey.skin_core.skin
         *Author  Dimen
         *Create by Dimen on  2021/12/31
         *Version:1.0
         *Describe:
         */
class SkinAttribute {
    private static final List<String> mAttributes = new ArrayList<>();
    static {
        mAttributes.add("background");
        mAttributes.add("src");

        mAttributes.add("textColor");
        mAttributes.add("drawableLeft");
        mAttributes.add("drawableTop");
        mAttributes.add("drawableRight");
        mAttributes.add("drawableBottom");

        mAttributes.add("skinTypeface");
    }

    public void load(View view, AttributeSet attrs) {
    }
}
