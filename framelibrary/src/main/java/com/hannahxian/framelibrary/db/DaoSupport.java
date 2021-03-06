package com.hannahxian.framelibrary.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.ArrayMap;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hannahxian on 2017/3/14.
 */

public class DaoSupport<T> implements IDaoSupport<T> {

    private SQLiteDatabase mSqlLiteDatabase;
    private Class<T> mClaz;

    public static final Object[] mPutMethodArgs = new Object[2];

    public static final Map<String,Method> mPutMethods = new ArrayMap<>();

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
        ContentValues cv = contentValuesByObj(t);
        String tableName = t.getClass().getSimpleName();
        mSqlLiteDatabase.beginTransaction();
        long id = mSqlLiteDatabase.insert(tableName,null,cv);
        Log.i("TAG", "插入成功， id === "+id);
        mSqlLiteDatabase.setTransactionSuccessful();
        mSqlLiteDatabase.endTransaction();
        return id;
    }

    /**
     * 把Object对象转成ContentValues
     * @param obj
     * @return
     */
    private ContentValues contentValuesByObj(T obj){
        Class claz = obj.getClass();
        Field[] fields = claz.getDeclaredFields();
        ContentValues cv = new ContentValues();
        for (Field f : fields){
            try {
                //设置权限
                f.setAccessible(true);
                String key = f.getName();
                Object value = f.get(key);

                mPutMethodArgs[0] = key;
                mPutMethodArgs[1] = value;

                // 缓存方法
                String fieldTypeName = f.getType().getName();
                Method putMethod = mPutMethods.get(fieldTypeName);
                if(putMethod == null){
                    putMethod = ContentValues.class.getDeclaredMethod("put",
                            String.class,value.getClass());
                    mPutMethods.put(fieldTypeName,putMethod);
                }

                putMethod.invoke(cv,mPutMethodArgs);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                mPutMethodArgs[0] = null;
                mPutMethodArgs[1] = null;
            }

        }
        return cv;
    }

    @Override
    public void insert(List<T> datas) {
        for (T data : datas) {
            insert(data);
        }
    }

    @Override
    public int update(T t,String whereClause,String... whereArgs) {
        ContentValues cv = contentValuesByObj(t);
        return mSqlLiteDatabase.update(mClaz.getSimpleName(),cv,whereClause,whereArgs);
    }

    @Override
    public List<T> query(String selction, String[] selectionArgs) {
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

        Field[] fs = mClaz.getDeclaredFields();
        Method[] mg = mClaz.getMethods();

        if(c != null){
            if(c.moveToFirst()) {
                for(int i=0;i<c.getCount();i++){
                    c.moveToPosition(i);
                    for (Field f: fs) {
                        try {
                            for(int j=0;j<mg.length;j++){
                                if(mg[j].getName().contains("set") && mg[j].getName().contains(f.getName())){
                                    if(f.getGenericType().toString().equalsIgnoreCase("int")){
                                        mg[j].invoke(obj,c.getInt(c.getColumnIndex(f.getName())));
                                    }else if(f.getGenericType().toString().equalsIgnoreCase("boolean")){
                                        mg[j].invoke(obj,c.getBlob(c.getColumnIndex(f.getName())));
                                    }else if(f.getGenericType().toString().equalsIgnoreCase("long")){
                                        mg[j].invoke(obj,c.getLong(c.getColumnIndex(f.getName())));
                                    }else if(f.getGenericType().toString().equalsIgnoreCase("doble")){
                                        mg[j].invoke(obj,c.getDouble(c.getColumnIndex(f.getName())));
                                    }else if(f.getGenericType().toString().equalsIgnoreCase("float")){
                                        mg[j].invoke(obj,c.getFloat(c.getColumnIndex(f.getName())));
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

    @Override
    public int delete(String whereClause, String... whereArgs) {
        return mSqlLiteDatabase.delete(mClaz.getSimpleName(),whereClause,whereArgs);
    }
}
