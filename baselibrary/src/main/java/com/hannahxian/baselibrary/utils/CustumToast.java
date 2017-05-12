package com.hannahxian.baselibrary.utils;

import android.content.Context;
import android.support.annotation.IntDef;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.hannahxian.baselibrary.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UnknownFormatFlagsException;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/12
 * Time: 11:12
 * Version: 1.0
 * Description:自定义toast
 * Email:wangchengda1990@gmail.com
 **/
public class CustumToast {
	private Toast mToast;

	private  static  final  int LAYOUTID = R.layout.toast_view;

	public static final int DEFAULT = 0x0011;

	public static final int CENTER = 0x0022;

	private View mView;

	private TextView mTextView;

	private int mStyle;

	private int mDuration;

	private CustumToast(Context context){
		mToast = new Toast(context);
		mView = LayoutInflater.from(context).inflate(LAYOUTID,null);
		mTextView = (TextView) mView.findViewById(R.id.toast_txt);
	}

	public static CustumToast with(Context context){
		return new CustumToast(context);
	}

	public CustumToast text(CharSequence text){
		mTextView.setText(text);
		return this;
	}

	public CustumToast textColor(int color){
		mTextView.setTextColor(color);
		return this;
	}

	public CustumToast style(@CStyle int style){
		this.mStyle = style;
		return this;
	}

	public CustumToast duration(@CDuration int duration){
		if(duration!= Toast.LENGTH_LONG && duration!= Toast.LENGTH_SHORT)
			throw new UnknownFormatFlagsException("只支持Toast.LENGTH_SHORT或Toast.LENGTH_LONG");
		this.mDuration = duration;
		return this;
	}

	public void show(){
		if(mStyle == CENTER)
			mToast.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);
		mToast.setView(mView);
		mToast.setDuration(mDuration);
		mToast.show();
	}


	public static CustumToast makeToast(Context context,CharSequence text,@CDuration int duration,@CStyle int style){
		if(duration!= Toast.LENGTH_LONG && duration!= Toast.LENGTH_SHORT)
			throw new UnknownFormatFlagsException("只支持Toast.LENGTH_SHORT或Toast.LENGTH_LONG");
		CustumToast custumToast = CustumToast.with(context)
				.duration(duration)
				.style(style)
				.text(text);
		return custumToast;
	}

	@IntDef({DEFAULT,CENTER})
	@Retention(RetentionPolicy.SOURCE)
	public @interface CStyle{}

	@IntDef({Toast.LENGTH_LONG,Toast.LENGTH_SHORT})
	@Retention(RetentionPolicy.SOURCE)
	public @interface CDuration{}
}
