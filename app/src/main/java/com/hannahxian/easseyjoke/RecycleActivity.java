package com.hannahxian.easseyjoke;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.hannahxian.baselibrary.CommonRecycleAdapter.MultiTypeSupport;
import com.hannahxian.baselibrary.CommonRecycleAdapter.OnItemClickListener;
import com.hannahxian.baselibrary.IOC.ViewById;
import com.hannahxian.baselibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends BaseActivity {

	@ViewById(R.id.myRecycle)
	RecyclerView mRecycleView;


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

//		MyRecycleAdapter adapter = new MyRecycleAdapter(this,strs,R.layout.test_recycle);
		MyRecycleAdapter adapter = new MyRecycleAdapter(this, strs, new MultiTypeSupport<String>() {
			@Override
			public int getLayoutId(String item, int position) {
				return R.layout.test_recycle;
			}
		});

		mRecycleView.setAdapter(adapter);

		adapter.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public boolean onClick(int v) {
				Toast.makeText(RecycleActivity.this, v+"", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}

	@Override
	protected void initTile() {

	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_recycle);
	}
}
