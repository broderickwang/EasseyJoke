package com.hannahxian.baselibrary.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Broderick on 2017/3/9.
 */

class AlertContoller {

	private AlertDialog mDialog;
	private Window mWindow;
	private DialogViewHelper mViewHelper;

	public AlertContoller(AlertDialog dialog, Window window) {
		this.mDialog = dialog;
		this.mWindow = window;
	}

	public void setViewHelper(DialogViewHelper viewHelper) {
		this.mViewHelper = viewHelper;
	}

	/**
	 * 获取Dialog
	 * @return
     */
	public AlertDialog getDialog() {
		return mDialog;
	}

	/**
	 * 获取dialog的window
	 *
	 * @return
     */
	public Window getWindow() {
		return mWindow;
	}

	public void setText(int viewId, CharSequence text) {
		mViewHelper.setText(viewId,text);
	}

	public <T extends View> T getView(int viewId) {
		return mViewHelper.getView(viewId);
	}

	public void setOnClickListner(int viewId, View.OnClickListener listener) {
		mViewHelper.setOnClickListner(viewId,listener);
	}

	public static class AlertParams{
		public   Context mContext;
		public int mThemeResId;
		//点击空白是否可以取消
		public boolean mCancelable = true;
		//dialog取消监听
		public DialogInterface.OnCancelListener mOnCancelListener;
		//dialog消失监听
		public DialogInterface.OnDismissListener mOnDismissListener;
		//dialog KEY监听
		public DialogInterface.OnKeyListener mOnKeyListener;
		//显示的布局view
		public View mView;
		//布局view的id
		public int mViewLayoutResId;
		//字体的修改
		public SparseArray<CharSequence> mTextArray = new SparseArray<>();
		//存放点击事件
		public SparseArray<View.OnClickListener> mListnerArray = new SparseArray<>();
		//宽度
		public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
		//位置
		public int mGravity = Gravity.CENTER;
		// 动画
		public int mAnimation = 0;
		//高度
		public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;

		public AlertParams(Context context, int thm) {
			this.mContext = context;
			this.mThemeResId = thm;
		}

		/**
		 * 绑定和设置参数
		 * @param mAlert
         */
		public void apply(AlertContoller mAlert) {
			//1.设置dialog布局 DialogViewHelper
			DialogViewHelper viewHelper = null;
			if(mViewLayoutResId != 0){
				viewHelper = new DialogViewHelper(mContext,mViewLayoutResId);
			}
			if(mView != null){
				viewHelper = new DialogViewHelper();
				viewHelper.setContentView(mView);
			}
			if(viewHelper == null){
				throw new IllegalArgumentException("请设置布局setContentView()");
			}
			//给dialog设置布局
			mAlert.getDialog().setContentView(viewHelper.getContentView());

			//设置Controller的辅助类Viewhelper
			mAlert.setViewHelper(viewHelper);

			//2.设置文本
			for(int i=0;i<mTextArray.size();i++){
				mAlert.setText(mTextArray.keyAt(i),mTextArray.valueAt(i));
			}

			//3.设置点击
			for(int i=0;i<mListnerArray.size();i++){
				mAlert.setOnClickListner(mListnerArray.keyAt(i),mListnerArray.valueAt(i));
			}


			//4.配置自定义效果，全屏，从底部弹出  默认动画
			Window window = mAlert.getWindow();
			//设置位置
			window.setGravity(mGravity);
			//设置动画
			if(mAnimation != 0) {
				window.setWindowAnimations(mAnimation);
			}
			//设置宽高
			WindowManager.LayoutParams params = window.getAttributes();
			params.width = mWidth;
			params.height = mHeight;
			window.setAttributes(params);
		}
	}
}
