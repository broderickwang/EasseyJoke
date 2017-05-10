package com.hannahxian.easseyjoke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hannahxian.baselibrary.base.SelectImageActivity;
import com.hannahxian.baselibrary.utils.ImageSelector;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(TestActivity.this, SelectImageActivity.class);
                i.putExtra(SelectImageActivity.EXTRA_DEFAULT_SELECTED_LIST,al);
                startActivityForResult(i,1);*/
                ImageSelector.create().count(9).camera(true).multi().origenlist(al).start(TestActivity.this,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1 && resultCode==SelectImageActivity.SELECT_RESULT_OK){
            al = data.getStringArrayListExtra(SelectImageActivity.EXTRA_RESULT);
        }
        for (int i = 0; i < al.size(); i++) {
            Toast.makeText(this, al.get(i), Toast.LENGTH_SHORT).show();
        }
    }
}
