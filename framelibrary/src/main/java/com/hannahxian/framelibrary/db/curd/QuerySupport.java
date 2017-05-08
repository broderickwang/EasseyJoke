package com.hannahxian.framelibrary.db.curd;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/8
 * Time: 14:58
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class QuerySupport<T> {
	SQLiteDatabase mSQLiteDatabase;
	Class mClazz;

	public QuerySupport(SQLiteDatabase mSQLiteDatabase, Class mClazz) {
		this.mSQLiteDatabase = mSQLiteDatabase;
		this.mClazz = mClazz;
	}
}
