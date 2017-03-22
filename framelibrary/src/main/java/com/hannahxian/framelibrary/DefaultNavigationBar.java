package com.hannahxian.framelibrary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.hannahxian.baselibrary.navigationbar.AbsNavigationBar;

/**
 * Created by Broderick on 2017/3/21.
 */

public class DefaultNavigationBar extends AbsNavigationBar {

	public DefaultNavigationBar(Builder.AbaNavigationParams mParams) {
		super(mParams);
	}

	@Override
	public int bindLayoutId() {
		return R.layout.title_bar;
	}

	@Override
	public void applyView() {
		//绑定效果
	}

	/*public static class Builder extends AbsNavigationBar.Builder{

		DefaultNavigationParams P;

		public Builder(Context context, ViewGroup parent) {
			super(context, parent);
			P = new DefaultNavigationParams(context,parent);
		}



		@Override
		public DefaultNavigationBar build() {
			DefaultNavigationBar navigationBar = new DefaultNavigationBar(P);
			return navigationBar;
		}
		//设置所有效果

		//

		public static class DefaultNavigationParams extends AbsNavigationBar.Builder.AbaNavigationParams{

			//所有的效果

			public DefaultNavigationParams(Context context, ViewGroup parent) {
				super(context, parent);
			}
		}
	}*/
}
