package com.hannahxian.baselibrary.dialog;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.hannahxian.baselibrary.R;

/**
 * 万能dialog
 * Created by Broderick on 2017/3/9.
 */

public class AlertDialog extends Dialog {

    private AlertContoller mAlert;



    public AlertDialog(Context context, int themeResId) {
        super(context, themeResId);

        mAlert = new AlertContoller(this, getWindow());
    }
    public void setText(int viewId,CharSequence text){
        mAlert.setText(viewId,text);
    }
    public <T extends View> T getView(int viewId){
        return mAlert.getView(viewId);
    }
    public void setOnClickListner(int viewId, View.OnClickListener listener){
        mAlert.setOnClickListner(viewId,listener);
    }

    public static class Builder {
        private final AlertContoller.AlertParams P;

        public Builder(Context context) {
            this(context, R.style.dialog);
        }

        public Builder(Context context, int thm) {
            P = new AlertContoller.AlertParams(context, thm);
        }

        private AlertDialog create() {
            final AlertDialog dialog = new AlertDialog(P.mContext, P.mThemeResId);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        public AlertDialog show() {
            AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }

        /**
         * 设置布局layout的view
         * @param view
         * @return
         */
        public Builder setContentView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }

        public Builder setContentView(int layoutId) {
            P.mView = null;
            P.mViewLayoutResId = layoutId;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }

        /**
         * 字体的修改
         * @param viewId
         * @param text
         * @return
         */
        public Builder setText(int viewId,CharSequence text){
            P.mTextArray.put(viewId,text);
            return this;
        }

        public Builder setTitle(String title){
            P.mTitle = title;
            return this;
        }

        /**
         * 设置点击事件
         * @param viewId
         * @param listener
         * @return
         */
        public Builder setOnClickListner(int viewId, View.OnClickListener listener){
            P.mListnerArray.put(viewId,listener);
            return this;
        }
        /**
         * 设置一些万能的参数
         * **/
        /**
         * 全屏
         * @return
         */
        public Builder fullWindow(){
            P.mWidth = ActionBar.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 从底部弹出
         * @param isAnimation 是否有动画
         * @return
         */
        public Builder fromBottom(boolean isAnimation){
            if(isAnimation){
                P.mAnimation = R.style.dialog_from_bottom_anim;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }

        /**
         * 设置dialog的宽高
         * @param width
         * @param height
         * @return
         */
        public Builder setWidthAndHeight(int width,int height){
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }

        /**
         * 添加默认动画
         * @return
         */
        public Builder addDefaultAnimation(){
            P.mAnimation = R.anim.dialog_default_anim;
            return this;
        }

        /**
         * 设置动画
         * @param styleAnimation
         * @return
         */
        public Builder setAnimation(int styleAnimation){
            P.mAnimation = styleAnimation;
            return this;
        }
    }

}
