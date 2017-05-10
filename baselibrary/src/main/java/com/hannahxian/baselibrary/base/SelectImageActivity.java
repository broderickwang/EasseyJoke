package com.hannahxian.baselibrary.base;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hannahxian.baselibrary.CommonRecycleAdapter.MultiTypeSupport;
import com.hannahxian.baselibrary.R;
import com.hannahxian.baselibrary.adapter.SelectImageListAdapter;
import com.hannahxian.baselibrary.bean.ImageEntity;
import com.hannahxian.baselibrary.decoration.ImageSelectGridDecoration;
import com.hannahxian.baselibrary.listner.SelectImageListner;

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

	public static final int SELECT_RESULT_OK = 0x0099;

	private static final int LOADER_TYPE = 0x0021;

	//带过来的key
	public static final String EXTRA_SHOW_CAMERA = "EXTRA_SHOW_CAMERA";

	public static final String EXTRA_SELECT_COUNT = "EXTRA_SELECT_CONT";

	public static final String EXTRA_SELECT_MODE = "EXTRA_SELECT_MODE";

	public static final String EXTRA_RESULT = "EXTRA_RESULT";

	public static final String EXTRA_DEFAULT_SELECTED_LIST = "EXTRA_DEFAULT_SELECTED_LIST";

	//int 单选或者多选
	private int mMode = MODE_MULTI;

	//boolean 是否显示拍照按钮
	private boolean mShowCamera = false;

	//int 图片张数
	private int mMaxCount = 9;

	//ArrayList 已经选择好的图片列表(保存图片路径)
	private ArrayList<String> mResultList;

	private RecyclerView mRecyclerView;

	private TextView mComplete,mCountTv,mPreview;

	@Override
	protected void setContentView() {
		setContentView(R.layout.image_select_activity);
	}
	@Override
	protected void initData() {
		//上一个页面传过来的参数
		mShowCamera = getIntent().getBooleanExtra(EXTRA_SHOW_CAMERA,true);

		mMaxCount = getIntent().getIntExtra(EXTRA_SELECT_COUNT,9);

		mMode = getIntent().getIntExtra(EXTRA_SELECT_MODE,MODE_MULTI);

		mResultList = getIntent().getStringArrayListExtra(EXTRA_DEFAULT_SELECTED_LIST);

		if(mResultList == null){
			mResultList = new ArrayList<>();
		}

		exChangeView();

		initImageList();

		setListner();
	}

	@Override
	protected void initView() {
		mRecyclerView = (RecyclerView)findViewById(R.id.select_images);

		mComplete = (TextView)findViewById(R.id.completed);

		mCountTv = (TextView)findViewById(R.id.count);

		mPreview = (TextView)findViewById(R.id.preview);

	}

	/**
	 * 根据选择的张数，进行视图变化
	 */
	private void exChangeView(){
		//如果选择大于1张图片，预览可用
		if(mResultList.size() > 0) {
			mPreview.setEnabled(true);
			mComplete.setEnabled(true);
			mPreview.setTextColor(getResources().getColor(R.color.hs_s22));
			mPreview.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});
		} else {
			mPreview.setTextColor(getResources().getColor(R.color.account_account_bind_night));
			mPreview.setEnabled(false);
			mComplete.setEnabled(false);
		}

		mCountTv.setText(mResultList.size()+"/"+mMaxCount);
	}

	@Override
	protected void initTile() {
		new DefaultNavigationBar.Builder(SelectImageActivity.this)
				.setTitle("所有图片")
				.builder();
	}

	/**
	 * 获取图片
	 */
	protected void initImageList(){
		//耗时操作
		getLoaderManager().initLoader(LOADER_TYPE,null,mLoaderCallback);
	}

	private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {

		private final String[] IMAGE_PROJECTION = {
				MediaStore.Images.Media.DATA,
				MediaStore.Images.Media.DISPLAY_NAME,
				MediaStore.Images.Media.DATE_ADDED,
				MediaStore.Images.Media.MIME_TYPE,
				MediaStore.Images.Media.SIZE,
				MediaStore.Images.Media._ID
		};

		@Override
		public Loader<Cursor> onCreateLoader(int id, Bundle args) {
			//查询
			CursorLoader cursorLoader = new CursorLoader(SelectImageActivity.this,
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI,IMAGE_PROJECTION,
					IMAGE_PROJECTION[4] + ">0 AND "+IMAGE_PROJECTION[3]+"=? OR "
					+IMAGE_PROJECTION[3] +"=? ",
					new String[]{"image/jpeg","image/png"},IMAGE_PROJECTION[2]+" DESC");
			return cursorLoader;
		}

		@Override
		public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
			//解析，封装到集合  只保存String路径
			if (data!=null && data.getCount()>0){
				ArrayList<ImageEntity> images = new ArrayList<>();

				//如果需要显示拍照,加一个空结构体
				if(mShowCamera){
					images.add(null);
				}

				while (data.moveToNext()){
					String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
					String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
					long time = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[3]));

					ImageEntity entity = new ImageEntity(path,name,time);
					images.add(entity);
				}

				//显示列表
				showImageList(images);
			}
		}

		@Override
		public void onLoaderReset(Loader<Cursor> loader) {

		}
	};

	/**
	 * 显示图片列表
	 * @param images
	 */
	private void showImageList(final ArrayList<ImageEntity> images) {
		final SelectImageListAdapter adapter = new SelectImageListAdapter(SelectImageActivity.this,
				images, new MultiTypeSupport<ImageEntity>() {
			@Override
			public int getLayoutId(ImageEntity item, int position) {
				int layoutId = 0;
				if(item == null){
					layoutId = R.layout.select_image_adapter_layout_camera;
				}else{
					layoutId = R.layout.select_image_adapter_layout;
				}
				return layoutId;
			}
		},mResultList,mMaxCount);
		adapter.setListner(new SelectImageListner() {
			@Override
			public void select() {
				exChangeView();
			}
		});

		mRecyclerView.addItemDecoration(new ImageSelectGridDecoration(4,5,false));
		((DefaultItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
		mRecyclerView.setLayoutManager(new GridLayoutManager(SelectImageActivity.this,4));
		mRecyclerView.setAdapter(adapter);
	}

	private void setListner(){
		mComplete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra(EXTRA_RESULT,mResultList);
				setResult(SELECT_RESULT_OK,intent);
				finish();
			}
		});
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
		super.startActivityForResult(intent, requestCode, options);
		// TODO: 2017/5/10 拍照
	}
}
