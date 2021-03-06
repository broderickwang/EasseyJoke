package com.hannahxian.framelibrary.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by hannahxian on 2017/3/14.
 */

public interface IDaoSupport<T> {
    public void init(SQLiteDatabase database, Class<T> claz);

    public long insert(T t);

    public void insert(List<T> datas);

    public int delete(String whereClause,String... whereArgs);

    public int update(T t,String whereClause,String... whereArgs);

    public List<T> query(String selction, String[] selectionArgs);
}
