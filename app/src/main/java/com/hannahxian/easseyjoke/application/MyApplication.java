package com.hannahxian.easseyjoke.application;

import android.app.Application;
import android.content.pm.PackageManager;

import com.alipay.euler.andfix.patch.PatchManager;

import org.xutils.x;

/**
 * Created by Broderick on 2017/3/16.
 */

public class MyApplication extends Application {
	public static PatchManager mPatchManager;
	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		x.Ext.setDebug(true);

		mPatchManager = new PatchManager(this);
		String pkName = this.getPackageName();
		String versionName = "";
		try {
			versionName = this.getPackageManager().getPackageInfo(pkName,0).versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		mPatchManager.init(versionName);
		mPatchManager.loadPatch();
	}
}
