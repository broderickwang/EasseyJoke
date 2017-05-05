package com.hannahxian.baselibrary.base;

import android.view.ViewGroup;

import com.hannahxian.baselibrary.R;

import java.util.ArrayList;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/5
 * Time: 16:12
 * Version: 1.0
 * Description:图片选择的activity
 * Email:wangchengda1990@gmail.com
 **/
public class SelectImageActivity extends BaseActivity {

	public static final int MODE_SINGLE = 0x0011;

	public static final int MODE_MULTI = 0x0022;

	//带过来的key
	public static final String EXTRA_SHOW_CAMERA = "EXTRA_SHOW_CAMERA";

	public static final String EXTRA_SELECT_COUNT = "EXTRA_SELECT_CONT";

	public static final String EXTRA_DEFAULT_SELECTED_LIST = "EXTRA_DEFAULT_SELECTED_LIST";

	public static final String EXTRA_SELECT_MODE = "EXTRA_SELECT_MODE";

	private static final String EXTRA_RESULT = "EXTRA_RESULT";

	//int 单选或者多选
	private int mMode = MODE_MULTI;

	//boolean 是否显示拍照按钮
	private boolean mShowCamera = true;

	//int 图片张数
	private int mMaxCount = 9;

	//ArrayList 已经选择好的图片列表
	private ArrayList<String> mResultList;

	@Override
	protected void setContentView() {
		setContentView(R.layout.image_select_activity);
	}
	@Override
	protected void initData() {
		//上一个页面传过来的参数
	}

	@Override
	protected void initView() {

	}

	@Override
	protected void initTile() {
		DefaultNavigationBar navigationBar = new
				DefaultNavigationBar.Builder(SelectImageActivity.this, (ViewGroup) findViewById(R.id.content))
				.setTitle("所有图片")
				.builder();
	}
}
