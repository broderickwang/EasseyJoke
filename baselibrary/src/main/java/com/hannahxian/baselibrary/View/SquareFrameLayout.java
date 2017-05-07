package com.hannahxian.baselibrary.View;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by hannahxian on 2017/5/7.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class SquareFrameLayout extends RelativeLayout {
    public SquareFrameLayout(@NonNull Context context) {
        super(context);
    }

    public SquareFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //自定义view
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width;

        //设置宽高一样
        setMeasuredDimension(width,height);
    }
}
