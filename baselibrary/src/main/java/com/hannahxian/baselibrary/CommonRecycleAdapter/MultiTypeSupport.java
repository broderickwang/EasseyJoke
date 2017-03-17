package com.hannahxian.baselibrary.CommonRecycleAdapter;

/**
 * Created by Broderick on 2017/3/17.
 */

public interface MultiTypeSupport<T> {
	// 根据当前位置或者条目数据返回布局
	public int getLayoutId(T item, int position);
}
