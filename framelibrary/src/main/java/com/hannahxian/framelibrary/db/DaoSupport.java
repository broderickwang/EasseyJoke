package com.hannahxian.framelibrary.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannahxian on 2017/3/14.
 */

public class DaoSupport<T> implements IDaoSupport<T> {

    private SQLiteDatabase mSqlLiteDatabase;
    private Class<T> mClaz;

    @Override
    public void init(SQLiteDatabase database,Class<T> claz){
        this.mSqlLiteDatabase = database;
        this.mClaz = claz;

        StringBuilder sb = new StringBuilder();
        sb.append("create table if not exists "+mClaz.getSimpleName()+"(id integer primary key autoincrement, ");

        Field[] fields = mClaz.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            String name = field.getName();
            if(name.equalsIgnoreCase("$change") || name.equalsIgnoreCase("serialVersionUID"))
                continue;
            String type = field.getType().getSimpleName();// int string boolean
            String typeC = "";

            //type进行类型转换 int-->integer string-->text
            if(type.equalsIgnoreCase("int")){
                typeC = "integer";
            }else if(type.equalsIgnoreCase("string")){
                typeC = "text";
            }else if(type.equalsIgnoreCase("char")){
                typeC = "varchar";
            }else {
                typeC = type;
            }

            sb.append(name+" "+typeC+", ");


        }
        sb.replace(sb.length()-2,sb.length(),")");
        Log.i("TAG", "创建表语句: "+sb.toString());

        //创建表
        mSqlLiteDatabase.execSQL(sb.toString());
    }
    // 插入t 任意对象
    @Override
    public long insert(T t) {
        //获取表名，表名就是t的类名
        Class claz = t.getClass();
        String tableName = claz.getSimpleName();
        Field[] fields = claz.getDeclaredFields();
        ContentValues cv = new ContentValues();
        for (Field f : fields){
            if(f.getGenericType().toString().equalsIgnoreCase("class java.lang.String")){
                try {
                    Method m = claz.getMethod("get"+f.getName());
                    String val = (String) m.invoke(t);
                    cv.put(f.getName(),val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(f.getGenericType().toString().equalsIgnoreCase("int")){
                try {
                    Method m = claz.getMethod("get"+f.getName());
                    int val = (int) m.invoke(t);
                    cv.put(f.getName(),val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        mSqlLiteDatabase.beginTransaction();
        long id = mSqlLiteDatabase.insert(tableName,null,cv);
        Log.i("TAG", "插入成功， id === "+id);
        mSqlLiteDatabase.setTransactionSuccessful();
        mSqlLiteDatabase.endTransaction();
        return id;
    }

    @Override
    public void update(T t) {

    }

    @Override
    public List<T> query(String selction, String[] selectionArgs) {
//        String tableName = claz.getSimpleName();
        String tableName = this.mClaz.getSimpleName();
        Cursor c = mSqlLiteDatabase.query(tableName, null, selction, selectionArgs, null, null, null, null);
        T obj = null;
        List<T> objs = new ArrayList<>();
        try {
            obj = mClaz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(c != null){
            if(c.moveToFirst()) {
                for(int i=0;i<c.getCount();i++){
                    c.moveToPosition(i);
                    Field[] fs = mClaz.getDeclaredFields();
                    for (Field f: fs) {
                        Method m = null;
                        try {
                            Method[] mg = mClaz.getMethods();
                            for(int j=0;j<mg.length;j++){
                                if(mg[j].getName().contains("set") && mg[j].getName().contains(f.getName())){
                                    if(f.getGenericType().toString().equalsIgnoreCase("int")){
                                        mg[j].invoke(obj,c.getInt(c.getColumnIndex(f.getName())));
                                    }else{
                                        mg[j].invoke(obj,c.getString(c.getColumnIndex(f.getName())));
                                    }

                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    objs.add(obj);
                }

            }
        }
        return objs;
    }
}
