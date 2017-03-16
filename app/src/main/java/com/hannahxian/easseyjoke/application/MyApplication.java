package com.hannahxian.easseyjoke.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Broderick on 2017/3/16.
 */

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		x.Ext.setDebug(true);
	}
}
