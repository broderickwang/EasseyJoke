package com.hannahxian.easseyjoke;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.hannahxian.baselibrary.CommonRecycleAdapter.MultiTypeSupport;
import com.hannahxian.baselibrary.CommonRecycleAdapter.OnItemClickListener;
import com.hannahxian.baselibrary.IOC.ViewById;
import com.hannahxian.baselibrary.View.WrapRecycleView;
import com.hannahxian.baselibrary.adapter.WrapRecyclerAdapter;
import com.hannahxian.baselibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class RecycleActivity extends BaseActivity {

	@ViewById(R.id.myRecycle)
	WrapRecycleView mRecycleView;


	@Override
	protected void initData() {

	}

	@Override
	protected void initView() {
		mRecycleView.setLayoutManager(new LinearLayoutManager(this));

		List<String> strs = new ArrayList<>();
		for(int i=0;i<5;i++){
			strs.add(i+"");
		}

		MyRecycleAdapter adapter = new MyRecycleAdapter(this,strs,R.layout.test_recycle);
		/*MyRecycleAdapter adapter = new MyRecycleAdapter(this, strs, new MultiTypeSupport<String>() {
			@Override
			public int getLayoutId(String item, int position) {
				return R.layout.test_recycle;
			}
		});*/
		WrapRecyclerAdapter adapter2 = new WrapRecyclerAdapter(adapter);
		adapter2.addHeaderView(LayoutInflater.from(this).inflate(R.layout.head_view,null,false));

		mRecycleView.setAdapter(adapter2);

	}

	@Override
	protected void initTile() {

	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_recycle);
	}
}
