package com.hannahxian.easseyjoke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hannahxian.baselibrary.IOC.CheckNet;
import com.hannahxian.baselibrary.IOC.OnClick;
import com.hannahxian.baselibrary.IOC.ViewById;
import com.hannahxian.baselibrary.IOC.ViewUtils;

public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.test_tv)
    private TextView mTextView;

    private int mPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewUtils.inject(this);

        mTextView.setText("MY IOC");
    }

    @OnClick({R.id.test_tv,R.id.test_iv})
    @CheckNet           //没不执行，打印没网的tosast
    private void onClick(){
        Toast.makeText(this, "test onclick", Toast.LENGTH_SHORT).show();
    }
}
