package com.hannahxian.baselibrary.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Dialog View辅助处理类
 * Created by Broderick on 2017/3/9.
 */

class DialogViewHelper {

    private View mContentView = null;

    private SparseArray<WeakReference<View>> mViews;

    public DialogViewHelper(Context context, int layoutResId) {
        this();
        mContentView = LayoutInflater.from(context).inflate(layoutResId,null);
    }


    public DialogViewHelper() {
        mViews = new SparseArray<>();
    }

    public void setContentView(View mContentView) {
        this.mContentView = mContentView;
    }

    /**
     *设置文本
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text ) {
        //每次都findViewById 减少查找次数
        TextView tv = getView(viewId);
        if(null != tv) {
            tv.setText(text);
        }
    }

    /**
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public  <T extends View>T getView(int viewId) {
        View view = null;
        if(mViews != null){
            WeakReference<View> viewWeakReference = mViews.get(viewId);
            if(viewWeakReference != null){
                view = viewWeakReference.get();
            }
        }

        if(view == null){
            view = mContentView.findViewById(viewId);
            if(view != null) {
                mViews.put(viewId, new WeakReference<View>(view));
            }
        }
        return (T)view;
    }

    /**
     *设置点击事件
     * @param viewId
     * @param listener
     */
    public void setOnClickListner(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if(null != view){
            view.setOnClickListener(listener);
        }
    }

    /**
     * 获取dialog的contentView
     * @return
     */
    public View getContentView() {
        return mContentView;
    }
}
