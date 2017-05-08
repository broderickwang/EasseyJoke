package com.hannahxian.framelibrary.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by hannahxian on 2017/3/14.
 */

public class DaoSupportFactory {
    private static   DaoSupportFactory mFactory;


    //持有外部数据库的引用
    private SQLiteDatabase mSqlLiteDatabase;
    private DaoSupportFactory(){

        //吧数据库放到内存卡中 判断是否有存储卡 6.0动态申请权限
        File dbRoot = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()+File.separator+"nhdz"+File.separator+"database");
        if(!dbRoot.exists()){
            dbRoot.mkdirs();
        }
        File dbFile = new File(dbRoot,"nhdz.db");

        Log.i("TAG", "数据库路径: "+dbFile.toString());

        //打开或者创建一个数据库
        mSqlLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile,null);
    }

    public static DaoSupportFactory getFactory(){
        if(mFactory==null){
            synchronized (DaoSupportFactory.class){
                if(mFactory == null){
                    mFactory = new DaoSupportFactory();
                }
            }
        }
        return mFactory;
    }

    /**
     * 获取dao，class需要有默认的构造方法
     * @param clz
     * @param <T>
     * @return
     */
    public <T> IDaoSupport<T> getDao(Class<T> clz){
        IDaoSupport<T> daoSupport = new DaoSupport<T>();
        daoSupport.init(mSqlLiteDatabase,clz);
        return daoSupport;
    }
}
