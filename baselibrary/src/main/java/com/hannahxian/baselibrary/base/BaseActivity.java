package com.hannahxian.baselibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hannahxian.baselibrary.IOC.ViewUtils;

/**
 * Created by hannahxian on 2017/3/5.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //设置布局layout
        setContentView();

        //一些特定的算法，子类基本都会使用的
        ViewUtils.inject(this);

        //初始化头部
        initTile();

        //初始化界面
        initView();

        //初始化数据
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initTile();

    protected abstract void setContentView();

    protected void startActivity(Class<?> claz){
        Intent intent = new Intent(this,claz);
        startActivity(intent);
    }

    protected <T extends View> T viewById(int viewId){
        return (T)findViewById(viewId);
    }
}