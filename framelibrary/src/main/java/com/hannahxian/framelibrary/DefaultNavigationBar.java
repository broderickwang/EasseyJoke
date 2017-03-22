package com.hannahxian.framelibrary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.hannahxian.baselibrary.navigationbar.AbsNavigationBar;

/**
 * Created by Broderick on 2017/3/21.
 */

public class DefaultNavigationBar<D extends AbsNavigationBar.Builder.NavigationParams>
		extends AbsNavigationBar<DefaultNavigationBar.Builder.DefaultNavigationBarParams> {


	public DefaultNavigationBar(Builder.DefaultNavigationBarParams mParams) {
		super(mParams);
	}

	@Override
	public int bindLayoutId() {
		return R.layout.title_bar;
	}

	@Override
	public void applyView() {
		//绑定效果
		setImageSource(R.id.iv_left, getmParams().leftIconRes);
		setImageSource(R.id.iv_right, getmParams().rightIconRes);
//		setImageSource(R.id.iv_right_icon, getmParams().textRightIconRes);
		setText(R.id.title_tv, getmParams().title);
		setText(R.id.left_tv, getmParams().leftTv);
		setText(R.id.right_tv, getmParams().rightTv);
		setBackground(R.id.title_bar, getmParams().bgColor);
		setOnClickListner(R.id.iv_left, getmParams().leftOnClickListener);
		setOnClickListner(R.id.iv_right, getmParams().rightOnClickListener);
	}

	public static class Builder extends AbsNavigationBar.Builder{
		private DefaultNavigationBarParams params;

		public Builder(Context context,ViewGroup parent){
			params = new DefaultNavigationBarParams(context,parent);
		}

		public Builder setTitle(String title){
			params.title = title;
			return this;
		}

		public Builder setRight(String right) {
			params.rightTv = right;
			return this;
		}

		public Builder setLeft(String left) {
			params.leftTv = left;
			return this;
		}

		public Builder setLeftIcon(int iconRes) {
			params.leftIconRes = iconRes;
			return this;
		}

		public Builder setRightIcon(int iconRes) {
			params.rightIconRes = iconRes;
			return this;
		}

		public Builder setTitleBackgroundColor(int bgColor) {
			params.bgColor = bgColor;
			return this;
		}

		public Builder setLeftOnClickListener(View.OnClickListener onClickListener) {
			params.leftOnClickListener = onClickListener;
			return this;
		}

		public Builder setRightOnClickListener(View.OnClickListener onClickListener) {
			params.rightOnClickListener = onClickListener;
			return this;
		}

		@Override
		public DefaultNavigationBar create() {
			DefaultNavigationBar<NavigationParams> navigationBar  = new DefaultNavigationBar<>(params);
			return navigationBar;
		}

		public static class DefaultNavigationBarParams extends NavigationParams {
			//标题
			public String title;
			//左边图片资源
			public int leftIconRes;
			//右边图片资源
			public int rightIconRes;
			//左边的点击事件
			public View.OnClickListener leftOnClickListener;
			//右边的点击事件
			public View.OnClickListener rightOnClickListener;
			public String leftTv;
			public String rightTv;
			public int bgColor;

			public DefaultNavigationBarParams(Context context, ViewGroup parent) {
				super(context, parent);
			}
		}
	}
}
