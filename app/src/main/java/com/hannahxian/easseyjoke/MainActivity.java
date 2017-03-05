package com.hannahxian.easseyjoke;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hannahxian.baselibrary.IOC.CheckNet;
import com.hannahxian.baselibrary.IOC.OnClick;
import com.hannahxian.baselibrary.IOC.ViewById;
import com.hannahxian.baselibrary.IOC.ViewUtils;
import com.hannahxian.baselibrary.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @ViewById(R.id.test_tv)
    private TextView mTextView;

    private int mPage;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTextView = viewById(R.id.test_tv);
    }

    @Override
    protected void initTile() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
//        startActivity(MainActivity.class);
    }

    @OnClick({R.id.test_tv,R.id.test_iv})
    @CheckNet           //没不执行，打印没网的tosast
    private void onClick(){
        Toast.makeText(this, "test onclick", Toast.LENGTH_SHORT).show();
    }
}
