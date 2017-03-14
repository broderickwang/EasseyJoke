package com.hannahxian.framelibrary.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Field;

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
    public int insert(T t) {
        //

//        mSqlLiteDatabase.insert()
        return 0;
    }
}
