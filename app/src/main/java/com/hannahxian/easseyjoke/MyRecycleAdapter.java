package com.hannahxian.easseyjoke;

import android.content.Context;

import com.hannahxian.baselibrary.CommonRecycleAdapter.CommonRecyclerAdapter;
import com.hannahxian.baselibrary.CommonRecycleAdapter.MultiTypeSupport;
import com.hannahxian.baselibrary.CommonRecycleAdapter.ViewHolder;

import java.util.List;

/**
 * Created by Broderick on 2017/3/17.
 */

public class MyRecycleAdapter extends CommonRecyclerAdapter<String> {
	public MyRecycleAdapter(Context context, List<String> data, MultiTypeSupport<String> multiTypeSupport) {
		super(context, data, multiTypeSupport);
	}


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
