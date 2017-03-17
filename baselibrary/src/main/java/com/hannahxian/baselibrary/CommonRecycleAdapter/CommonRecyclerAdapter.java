package com.hannahxian.baselibrary.CommonRecycleAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Broderick on 2017/3/17.
 */

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder>
										implements MultiTypeSupport{

	protected Context mContext;
	protected LayoutInflater mInflater;

	protected List<T> mDatas;
	private int mLayoutId;

	// 多布局支持
	private MultiTypeSupport mMultiTypeSupport;

	public CommonRecyclerAdapter(Context mContext, List<T> mDatas, int mLayoutId) {
		this.mContext = mContext;
		this.mDatas = mDatas;
		this.mLayoutId = mLayoutId;
		mInflater = LayoutInflater.from(mContext);
	}

	/**
	 * 多布局支持
	 */
	public CommonRecyclerAdapter(Context context, List<T> data, MultiTypeSupport<T> multiTypeSupport) {
		this(context, data, -1);
		this.mMultiTypeSupport = multiTypeSupport;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = mInflater.inflate(mLayoutId,parent,false);
		ViewHolder holder = new ViewHolder(itemView);
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		// 设置点击和长按事件
		if (mItemClickListener != null) {
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mItemClickListener.onClick(position);
				}
			});
		}
		if (mLongClickListener != null) {
			holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					return mLongClickListener.onLongClick(position);
				}
			});
		}

		convert(holder,mDatas.get(position));
	}

	public abstract void convert(ViewHolder holder,T item);

	@Override
	public int getItemCount() {
		return mDatas.size();
	}

	@Override
	public int getItemViewType(int position) {
		// 多布局支持
		if (mMultiTypeSupport != null) {
			return mMultiTypeSupport.getLayoutId(mDatas.get(position), position);
		}
		return super.getItemViewType(position);
	}

	/***************
	 * 设置条目点击和长按事件
	 *********************/
	public OnItemClickListener mItemClickListener;
	public OnLongClickListener mLongClickListener;

	public void setOnItemClickListener(OnItemClickListener itemClickListener) {
		this.mItemClickListener = itemClickListener;
	}

	public void setOnLongClickListener(OnLongClickListener longClickListener) {
		this.mLongClickListener = longClickListener;
	}
}
