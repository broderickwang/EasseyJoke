package com.hannahxian.baselibrary.CommonRecycleAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannahxian.baselibrary.View.WangcdImageView;

/**
 * Created by Broderick on 2017/3/17.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
	private SparseArray<View> mViews;

	public ViewHolder(View itemView) {
		super(itemView);
		mViews = new SparseArray<>();
	}

	public <T extends View> T getView(int viewId){
		View view = mViews.get(viewId);
		if(view == null){
			view = itemView.findViewById(viewId);
			mViews.put(viewId,view);
		}
		return (T)view;
	}

	public ViewHolder setText(int viewId,CharSequence text){
		TextView textView = getView(viewId);
		textView.setText(text);
		return this;
	}

	public ViewHolder setText_wangcd(int viewId,CharSequence text){
		WangcdImageView view = getView(viewId);
		view.setText(text);
		return this;
	}

	public ViewHolder setTag(int viewId,CharSequence text){
		TextView textView = getView(viewId);
		textView.setTag(text);
		return this;
	}

	public ViewHolder setVisibility(int viewId,int visibility){
		getView(viewId).setVisibility(visibility);
		return this;
	}

	public ViewHolder setImageResource(int viewId,int resourceId){
		ImageView imageView = getView(viewId);
		imageView.setImageResource(resourceId);
		return this;
	}

	public ViewHolder setImageByUrl(int viewId,HolderImageLoader imageLoader){
		ImageView imageView = getView(viewId);
		if (imageLoader == null) {
			throw new NullPointerException("imageLoader is null!");
		}
		imageLoader.displayImage(imageView.getContext(), imageView, imageLoader.getImagePath());
		return this;
	}
	/**
	 * 设置条目点击事件
	 */
	public void setOnIntemClickListener(View.OnClickListener listener) {
		itemView.setOnClickListener(listener);
	}

	/**
	 * 设置条目长按事件
	 */
	public void setOnIntemLongClickListener(View.OnLongClickListener listener) {
		itemView.setOnLongClickListener(listener);
	}
	/**
	 * 图片加载，这里稍微处理得复杂一些，因为考虑加载图片的第三方可能不太一样
	 * 也可以不写这个类
	 */
	public static abstract class HolderImageLoader {
		private String mImagePath;

		public HolderImageLoader(String imagePath) {
			this.mImagePath = imagePath;
		}

		public String getImagePath() {
			return mImagePath;
		}

		public abstract void displayImage(Context context, ImageView imageView, String imagePath);
	}
}
