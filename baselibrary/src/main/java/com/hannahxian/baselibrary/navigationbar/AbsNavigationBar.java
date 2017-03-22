package com.hannahxian.baselibrary.navigationbar;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 头部的基类
 * Created by Broderick on 2017/3/21.
 */

public abstract class AbsNavigationBar<P extends AbsNavigationBar.Builder.AbaNavigationParams> implements INavigationBar {

	private P mParams;

	private View mView;

	public AbsNavigationBar(P mParams) {
		this.mParams = mParams;
		createAndBindView();
	}

	protected String getString(int id){
		return this.mParams.mContext.getResources().getString(id);
	}

	protected int getColor(int id){
		return ContextCompat.getColor(this.mParams.mContext,id);
	}

	protected P getmParams(){
		return  mParams;
	}

	protected void setText(int viewId,CharSequence text){
		TextView tv = findViewById(viewId);
		tv.setText(text);
	}

	protected void setImageSource(int viewId,int resourceId){
		ImageView iv = findViewById(viewId);
		iv.setImageResource(resourceId);
	}

	protected void setOnClickListner(int viewId, View.OnClickListener listener){
		View view = findViewById(viewId);
		view.setOnClickListener(listener);
	}

	protected <T extends View>T findViewById(int id){
		return (T)mView.findViewById(id);
	}

	/**
	 * 绑定和创建view
	 */
	private void createAndBindView() {
		if(mParams == null)
			return;
		//创建view
		mView = LayoutInflater.from(mParams.mContext)
				.inflate(bindLayoutId(),mParams.mParent,false);
		//添加
		mParams.mParent.addView(mView,0);

		//绑定参数
		applyView();
	}

	public abstract static class Builder{
		/*AbaNavigationParams P;

		public Builder(Context context, ViewGroup parent){
			//创建 P = NEW
			P = new AbaNavigationParams(context,parent);

		}*/

		public abstract AbsNavigationBar create();


		public static class AbaNavigationParams{
			public Context mContext;
			public ViewGroup mParent;

			public AbaNavigationParams(Context context,ViewGroup parent) {
				this.mContext = context;
				this.mParent = parent;
			}
		}
	}
}
