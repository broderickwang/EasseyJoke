package com.hannahxian.baselibrary.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hannahxian.baselibrary.R;
import com.hannahxian.baselibrary.navigationbar.AbsNavigationBar;

/**
 * Created by hannahxian on 2017/3/22.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:Default
 */

public class DefaultNavigationBar extends AbsNavigationBar<DefaultNavigationBar.Builder.DefaultNavigationParams> {
    public DefaultNavigationBar(Builder.DefaultNavigationParams mParams) {
        super(mParams);
    }

    @Override
    public int bindLayoutId() {
        return R.layout.title_bar;
    }

    @Override
    public void applyView() {
        //bind effects
        setText(R.id.title,getParams().mTitle);
        setText(R.id.right_text,getParams().mRightText);
//        setTitlebarHeight(R.id.title_bar,getParams().mTitlebarHeight);
        setTitleBarColor(R.id.title_bar,getParams().mTitleBarColor);
        setOnClickListner(R.id.right_text,getParams().mRightClickListner);
        //左边写一个默认的
        setOnClickListner(R.id.back,getParams().mLeftClickListner);

    }

    public static class Builder extends AbsNavigationBar.Builder{

        DefaultNavigationParams P;

        public Builder(Context context, ViewGroup parent) {
            super(context, parent);
            P = new DefaultNavigationParams(context,parent);
        }

        public Builder(Context context){
            super(context, null);
            P = new DefaultNavigationParams(context,null);
        }

        @Override
        public DefaultNavigationBar builder() {
            DefaultNavigationBar navigationBar = new DefaultNavigationBar(P);
            return navigationBar;
        }

        //set effects
        public Builder setTitle(String title){
            P.mTitle = title;
            return this;
        }

        public Builder setTitleHeight(int height){
            P.mTitlebarHeight = height;
            return this;
        }

        public Builder setTitleColor(int color){
            P.mTitleBarColor = color;
            return this;
        }

        public Builder setRightText(String text){
            P.mRightText = text;
            return this;
        }

        public Builder setLeftText(String text){
            P.mLeftText = text;
            return this;
        }

        public Builder setRightIcon(int resourceId){
            P.mRightImage.setImageResource(resourceId);
            return this;
        }

        public Builder setLeftIcon(int resourceId){
            P.mLeftImage.setImageResource(resourceId);
            return this;
        }

        public Builder setRightClickListner(View.OnClickListener listner){
            P.mRightClickListner = listner;
            return this;
        }

        public Builder setLeftClickListner(View.OnClickListener listner){
            P.mLeftClickListner = listner;
            return this;
        }

        public static class DefaultNavigationParams extends AbsNavigationParams{

            //write effects
            public String mTitle;
            public String mRightText;
            public String mLeftText;
            public ImageView mRightImage;
            public ImageView mLeftImage;
            public int mTitleBarColor = mContext.getResources().getColor(R.color.title_bar_bg_day);
            public int mTitlebarHeight = ViewGroup.LayoutParams.WRAP_CONTENT;

            public View.OnClickListener mRightClickListner;
            public View.OnClickListener mLeftClickListner = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity)mContext).finish();
                }
            };

            DefaultNavigationParams(Context context, ViewGroup parent) {
                super(context, parent);
            }
        }
    }
}
