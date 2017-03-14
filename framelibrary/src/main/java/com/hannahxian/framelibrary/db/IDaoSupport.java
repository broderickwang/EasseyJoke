package com.hannahxian.framelibrary.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by hannahxian on 2017/3/14.
 */

public interface IDaoSupport<T> {
    public void init(SQLiteDatabase database, Class<T> claz);

    public int insert(T t);
}
