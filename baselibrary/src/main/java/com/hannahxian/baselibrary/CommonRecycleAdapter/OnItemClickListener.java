package com.hannahxian.baselibrary.CommonRecycleAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Broderick on 2017/3/17.
 */

public interface OnItemClickListener  {
	boolean onClick(RecyclerView parent, int position);
}
