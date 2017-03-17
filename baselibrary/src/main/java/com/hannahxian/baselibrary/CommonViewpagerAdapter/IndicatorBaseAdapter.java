package com.hannahxian.baselibrary.CommonViewpagerAdapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Broderick on 2017/3/17.
 */

public abstract class IndicatorBaseAdapter {

	public abstract int getCount();

	public abstract View getView(int position, ViewGroup parent);

}
