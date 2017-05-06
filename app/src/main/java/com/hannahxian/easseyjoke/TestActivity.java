package com.hannahxian.easseyjoke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hannahxian.baselibrary.IOC.OnClick;
import com.hannahxian.baselibrary.base.SelectImageActivity;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this, SelectImageActivity.class));
            }
        });
    }
    @OnClick({R.id.button})
    protected void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                Toast.makeText(this, "aa", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SelectImageActivity.class));
                break;
        }
    }
}
