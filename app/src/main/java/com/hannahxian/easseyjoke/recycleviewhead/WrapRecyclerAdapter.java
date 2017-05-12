package com.hannahxian.easseyjoke.recycleviewhead;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/10
 * Time: 14:43
 * Version: 1.0
 * Description:可以添加头部的adapter
 * Email:wangchengda1990@gmail.com
 **/
public class WrapRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private SparseArray<View> mHeaderViews;

	private SparseArray<View> mFootViews;

	private static int BASE_ITEM_TYPE_HEAD = 100000;

	private static int BASE_ITEM_TYPE_FOOT = 200000;

	private RecyclerView.Adapter mAdapter;

	public WrapRecyclerAdapter(RecyclerView.Adapter mAdapter) {
		this.mAdapter = mAdapter;
		mHeaderViews = new SparseArray<>();
		mFootViews = new SparseArray<>();
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if(isHeadViewType(viewType)){
			View headerView = mHeaderViews.get(viewType);
			return createHeaderFooterViewHolder(headerView);
		}
		if(isFootViewType(viewType)){
			View footerView = mFootViews.get(viewType);
			return createHeaderFooterViewHolder(footerView);
		}
		return mAdapter.onCreateViewHolder(parent,viewType);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if(isHeadViewType(position) || isFootViewType(position)){
			return;
		}

		position = position - mHeaderViews.size();
		mAdapter.onBindViewHolder(holder,position);
	}

	@Override
	public int getItemViewType(int position) {
		if(isHeadViewType(position)){
			return mHeaderViews.keyAt(position);
		}
		if(isFootViewType(position)){
			position = position - mHeaderViews.size() - mAdapter.getItemCount();
			return mFootViews.keyAt(position);
		}
		position = position - mHeaderViews.size();
		return mAdapter.getItemViewType(position);
	}

	@Override
	public int getItemCount() {
		return mAdapter.getItemCount()+mHeaderViews.size()+mFootViews.size();
	}

	private boolean isFootViewType(int viewType){
		int position = mFootViews.indexOfKey(viewType);
		return  position >= 0;
	}

	private boolean isHeadViewType(int viewType){
		int position = mHeaderViews.indexOfKey(viewType);
		return position >= 0;
	}

	private RecyclerView.ViewHolder createHeaderFooterViewHolder(View view){
		return new RecyclerView.ViewHolder(view) {
		};
	}

	public RecyclerView.Adapter getAdapter() {
		return mAdapter;
	}

	public void addHeaderView(View view){
		int position = mHeaderViews.indexOfValue(view);
		if(position < 0){
			mHeaderViews.put(BASE_ITEM_TYPE_HEAD++,view);
		}
		notifyDataSetChanged();
	}

	public void addFooterView(View view){
		int position = mFootViews.indexOfValue(view);
		if(position < 0){
			mFootViews.put(BASE_ITEM_TYPE_FOOT++,view);
		}
		notifyDataSetChanged();
	}

	public void removeHeaderView(View view){
		int index = mHeaderViews.indexOfValue(view);
		if(index < 0) return;
		mHeaderViews.remove(index);
		notifyDataSetChanged();
	}

	public void removeFooterView(View view){
		int index = mFootViews.indexOfValue(view);
		if(index < 0)return;
		mFootViews.remove(index);
		notifyDataSetChanged();
	}
	/**
	 * 解决GridLayoutManager添加头部和底部不占用一行的问题
	 *
	 * @param recycler
	 * @version 1.0
	 */
	public void adjustSpanSize(RecyclerView recycler) {
		if (recycler.getLayoutManager() instanceof GridLayoutManager) {
			final GridLayoutManager layoutManager = (GridLayoutManager) recycler.getLayoutManager();
			layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
				@Override
				public int getSpanSize(int position) {
					boolean isHeaderOrFooter =
							isHeadViewType(position) || isFootViewType(position);
					return isHeaderOrFooter ? layoutManager.getSpanCount() : 1;
				}
			});
		}
	}
}
