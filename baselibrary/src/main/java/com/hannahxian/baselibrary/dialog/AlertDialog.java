package com.hannahxian.baselibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.style.BulletSpan;

import com.hannahxian.baselibrary.R;

/**
 * Created by Broderick on 2017/3/9.
 */

public class AlertDialog extends Dialog {
	public AlertDialog(Context context, int themeResId) {
		super(context, themeResId);
	}

	public static class Builder{
		private final AlertContoller.AlertParams P;
		public Builder(Context context){
			this(context, R.style.dialog);
		}
		public Builder(Context context,int thm){
			P = new AlertContoller.AlertParams(context,thm);
		}
	}

}
