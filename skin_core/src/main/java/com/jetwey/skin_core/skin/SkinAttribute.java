package com.jetwey.skin_core.skin;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.jetwey.skin_core.utils.SkinResources;
import com.jetwey.skin_core.utils.SkinThemeUtils;

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
    private List<SkinView> skinViews = new ArrayList<>();
    private Typeface mTypeface;
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
    public  SkinAttribute(Typeface skinTypeface) {
        mTypeface = skinTypeface;
    }
    public void load(View view, AttributeSet attrs) {
        List<SkinPain> skinPains = new ArrayList<>();
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            //获取属性名字
            String attributeName = attrs.getAttributeName(i);
            if (mAttributes.contains(attributeName)) {
                //获取属性对应的值
                String attributeValue = attrs.getAttributeValue(i);
                if (attributeValue.startsWith("#")) {
                    continue;
                }
                int resId;
                //判断前缀字符串 是否是"?"
                //attributeValue  = "?2130903043"
                if (attributeValue.startsWith("?")) {//系统属性值
                    //字符串的子字符串  从下标 1 位置开始
                    int attrId = Integer.parseInt(attributeValue.substring(1));
                    resId = SkinThemeUtils.getResId(view.getContext(), new int[]{attrId})[0];
                } else {
                    //@1234564
                    resId = Integer.parseInt(attributeValue.substring(1));
                }
                if (resId != 0) {
                    SkinPain skinPain = new SkinPain(attributeName, resId);
                    skinPains.add(skinPain);
                }
            }

        }
        if (!skinPains.isEmpty() || view instanceof TextView || view instanceof SkinViewSupport) {
            SkinView skinView = new SkinView(view, skinPains);
            skinView.applySkin(mTypeface);
            skinViews.add(skinView);
        }
    }
    public void setTypeface(Typeface skinTypeface) {
        this.mTypeface = skinTypeface;
    }
    static class SkinView {
        View view;
        List<SkinPain> skinPains;

        public SkinView(View view, List<SkinPain> skinPains) {
            this.view = view;
            this.skinPains = skinPains;
        }

        public void applySkin(Typeface typeface) {
            applyTypeface(typeface);
            applySkinSupport();
            for (SkinPain skinPain : skinPains) {
                Drawable left = null, top = null, right = null, bottom = null;
                switch (skinPain.attributeName) {
                    case "background":
                        Object background = SkinResources.getInstance().getBackground(
                                skinPain.resId);
                        //Color
                        if (background instanceof Integer) {
                            view.setBackgroundColor((Integer) background);
                        } else {
                            ViewCompat.setBackground(view, (Drawable) background);
                        }
                        //摸摸唱
                        break;
                    case "src":
                        background = SkinResources.getInstance().getBackground(skinPain
                                .resId);
                        if (background instanceof Integer) {
                            ((ImageView) view).setImageDrawable(new ColorDrawable((Integer)
                                    background));
                        } else {
                            ((ImageView) view).setImageDrawable((Drawable) background);
                        }
                        break;
                    case "textColor":
                        ((TextView) view).setTextColor(SkinResources.getInstance().getColorStateList
                                (skinPain.resId));
                        break;
                    case "drawableLeft":
                        left = SkinResources.getInstance().getDrawable(skinPain.resId);
                        break;
                    case "drawableTop":
                        top = SkinResources.getInstance().getDrawable(skinPain.resId);
                        break;
                    case "drawableRight":
                       right = SkinResources.getInstance().getDrawable(skinPain.resId);
                        break;
                    case "drawableBottom":
                        bottom = SkinResources.getInstance().getDrawable(skinPain.resId);
                        break;
                    case "skinTypeface" :
                        applyTypeface(SkinResources.getInstance().getTypeface(skinPain.resId));
                        break;
                    default:
                        break;

                }
                if (null != left || null != right || null != top || null != bottom) {
                    ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(left, top, right,
                            bottom);
                }
            }
        }


    //自定义View
    private void applySkinSupport() {
        if(view instanceof SkinViewSupport){
            ((SkinViewSupport)view).applySkin();
        }
    }

    //字体换肤
    private void applyTypeface(Typeface typeface) {
        if(view instanceof TextView){
            ((TextView) view).setTypeface(typeface);
        }
    }
}
    static class SkinPain {
        String attributeName;
        int resId;

        public SkinPain(String attributeName, int resId) {
            this.attributeName = attributeName;
            this.resId = resId;
        }
    }
    /**
     * 换皮肤
     */
    public void applySkin() {
        for (SkinView mSkinView : skinViews) {
            mSkinView.applySkin(mTypeface);
        }
    }
}
