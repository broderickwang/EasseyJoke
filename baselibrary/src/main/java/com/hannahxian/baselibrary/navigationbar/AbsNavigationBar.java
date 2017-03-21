package com.hannahxian.baselibrary.navigationbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 头部的基类
 * Created by Broderick on 2017/3/21.
 */

public abstract class AbsNavigationBar implements INavigationBar {

	private Builder.AbaNavigationParams mParams;

	public AbsNavigationBar(Builder.AbaNavigationParams mParams) {
		this.mParams = mParams;
		createAndBindView();
	}

	/**
	 * 绑定和创建view
	 */
	private void createAndBindView() {
		//创建view
		View navigationView = LayoutInflater.from(mParams.mContext)
				.inflate(bindLayoutId(),mParams.mParent,false);
		//添加
		mParams.mParent.addView(navigationView,0);

		//绑定参数
		applyView();
	}

	public abstract static class Builder{
		AbaNavigationParams P;

		public Builder(Context context, ViewGroup parent){
			//创建 P = NEW
			P = new AbaNavigationParams(context,parent);

		}

		public abstract AbsNavigationBar build();


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
