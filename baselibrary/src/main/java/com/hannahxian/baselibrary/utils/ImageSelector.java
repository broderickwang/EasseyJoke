package com.hannahxian.baselibrary.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import com.hannahxian.baselibrary.base.SelectImageActivity;
import java.util.ArrayList;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/10
 * Time: 16:09
 * Version: 1.0
 * Description:图片选择的链式调用
 * Email:wangchengda1990@gmail.com
 **/
public class ImageSelector {
	private int mMaxCount;

	private int mIsMulti;

	private boolean mIsShowCamera;

	private ArrayList<String> mSelectedImgs;

	public ImageSelector() {
	}

	public static ImageSelector create(){
		return new ImageSelector();
	}

	public ImageSelector count(int count){
		this.mMaxCount = count;
		return this;
	}

	public ImageSelector camera(boolean isShow){
		this.mIsShowCamera = isShow;
		return this;
	}

	public ImageSelector multi(){
		this.mIsMulti = SelectImageActivity.MODE_MULTI;
		return this;
	}

	public ImageSelector single(){
		this.mIsMulti = SelectImageActivity.MODE_SINGLE;
		return this;
	}

	public ImageSelector origenlist(ArrayList<String> list){
		this.mSelectedImgs = list;
		return this;
	}

	public void start(Fragment fragment, int requestCode){
		Intent i = new Intent(fragment.getContext(), SelectImageActivity.class);
		addParamsByIntent(i);
		fragment.startActivityForResult(i,requestCode);
	}

	public void start(Activity activity, int requestCode){
		Intent i = new Intent(activity, SelectImageActivity.class);
		addParamsByIntent(i);
		activity.startActivityForResult(i,requestCode);
	}

	private void addParamsByIntent(Intent intent){
		intent.putExtra(SelectImageActivity.EXTRA_DEFAULT_SELECTED_LIST,mSelectedImgs);
		intent.putExtra(SelectImageActivity.EXTRA_SHOW_CAMERA,mIsShowCamera);
		intent.putExtra(SelectImageActivity.EXTRA_SELECT_MODE,mIsMulti);
		intent.putExtra(SelectImageActivity.EXTRA_SELECT_COUNT,mMaxCount);
	}
}
