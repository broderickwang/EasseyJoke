package com.hannahxian.easseyjoke;

import android.content.Context;

import com.hannahxian.baselibrary.commonadapter.CommonRecyclerAdapter;
import com.hannahxian.baselibrary.commonadapter.ViewHolder;

import java.util.List;

/**
 * Created by Broderick on 2017/3/17.
 */

public class MyRecycleAdapter extends CommonRecyclerAdapter<String> {


	public MyRecycleAdapter(Context mContext, List<String> mDatas, int mLayoutId) {
		super(mContext, mDatas, mLayoutId);
	}

	@Override
	public void convert(final ViewHolder holder, final String item) {
		holder.setText(R.id.txt_rec,item);
		/*holder.setOnIntemClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, item, Toast.LENGTH_SHORT).show();
			}
		});*/
	}

	@Override
	public int getLayoutId(Object item, int position) {
		return 0;
	}
}
