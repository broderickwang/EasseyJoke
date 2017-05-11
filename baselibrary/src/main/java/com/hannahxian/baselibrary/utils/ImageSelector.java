package com.hannahxian.baselibrary.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.hannahxian.baselibrary.base.SelectImageActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	public static void galleryAddPic(Context context, String photoPath) {
		Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(photoPath);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		context.sendBroadcast(mediaScanIntent);
	}
	public static File createImageFile() throws IOException {
		// 定义图片名称
		String imageFileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileSuffix = ".jpg";
		File storageDir = Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_PICTURES + "/GIS");
		if (!storageDir.exists()) {
			storageDir.mkdirs();
		}
		File image = new File(storageDir.getPath() + "/" + imageFileName + imageFileSuffix);
		return image;
	}
}
