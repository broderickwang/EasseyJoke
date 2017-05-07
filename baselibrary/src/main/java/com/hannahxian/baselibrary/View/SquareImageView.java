package com.hannahxian.baselibrary.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by hannahxian on 2017/5/7.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class SquareImageView extends ImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
