package com.hannahxian.baselibrary.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hannahxian.baselibrary.R;
import com.hannahxian.baselibrary.navigationbar.AbsNavigationBar2;

/**
 * Created by hannahxian on 2017/3/22.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:Default
 */

public class DefaultNavigationBar extends AbsNavigationBar2<DefaultNavigationBar.Builder.DefaultNavigationParams> {
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
        setText(R.id.title_tv,getParams().mTitle);
        setText(R.id.right_tv,getParams().mRightText);
        setOnClickListner(R.id.iv_right,getParams().mRightClickListner);
        //左边写一个默认的

    }

    public static class Builder extends AbsNavigationBar2.Builder{

        DefaultNavigationParams P;

        public Builder(Context context, ViewGroup parent) {
            super(context, parent);
            P = new DefaultNavigationParams(context,parent);
        }

        @Override
        public DefaultNavigationBar builder() {
            DefaultNavigationBar navigationBar2 = new DefaultNavigationBar(P);
            return navigationBar2;
        }

        //set effects
        public Builder setTitle(String title){
            P.mTitle = title;
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

            public View.OnClickListener mRightClickListner;
            public View.OnClickListener mLeftClickListner;

            DefaultNavigationParams(Context context, ViewGroup parent) {
                super(context, parent);
            }
        }
    }
}
