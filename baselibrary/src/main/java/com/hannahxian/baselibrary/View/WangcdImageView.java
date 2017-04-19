package com.hannahxian.baselibrary.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hannahxian.baselibrary.R;

/**
 * Created by Broderick on 2017/4/19.
 */

public class WangcdImageView extends RelativeLayout {

	private ImageView mImageView;
	private TextView mTextView;

	private Bitmap mBitmap;
	private CharSequence mName;

	public WangcdImageView(Context context) {
		this(context,null);
	}

	public WangcdImageView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public WangcdImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		LayoutInflater.from(context).inflate(R.layout.wangcd_imageview,this,true);

		mImageView = (ImageView)findViewById(R.id.wangcd_img);

		mTextView = (TextView)findViewById(R.id.wangcd_name);

		TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.WangcdImageView);

		mBitmap = BitmapFactory.decodeResource(getResources(),array.getResourceId(R.styleable.WangcdImageView_imgsrc,0));

		mName = array.getString(R.styleable.WangcdImageView_imgname);

		array.recycle();

		mImageView.setImageBitmap(mBitmap);

		mTextView.setText(mName);
	}

	public void setImageBitmap(Bitmap mBitmap) {
		this.mBitmap = mBitmap;
	}

	public void setText(CharSequence mName) {
		this.mName = mName;
	}

	public void setImageClickListner(OnClickListener listner){
		this.mImageView.setOnClickListener(listner);
	}
}
