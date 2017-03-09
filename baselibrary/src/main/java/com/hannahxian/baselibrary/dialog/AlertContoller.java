package com.hannahxian.baselibrary.dialog;

import android.content.Context;

/**
 * Created by Broderick on 2017/3/9.
 */

class AlertContoller {
	public static class AlertParams{
		private  Context mContext;
		private int mThemeResId;

		public AlertParams(Context context, int thm) {
			this.mContext = context;
			this.mThemeResId = thm;
		}
	}
}
