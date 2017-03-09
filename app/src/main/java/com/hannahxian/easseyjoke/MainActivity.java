package com.hannahxian.easseyjoke;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hannahxian.baselibrary.IOC.CheckNet;
import com.hannahxian.baselibrary.IOC.OnClick;
import com.hannahxian.baselibrary.IOC.ViewById;
import com.hannahxian.baselibrary.dialog.AlertDialog;
import com.hannahxian.framelibrary.BaseSkinActivity;

public class MainActivity extends BaseSkinActivity {

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
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setContentView(R.layout.detail_comment_dialog)
                /*.setOnClickListner(R.id.wb, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "weibo", Toast.LENGTH_SHORT).show();
                    }
                })*/
                .setText(R.id.txt,"自己设置的")
                .fullWindow()
                .fromBottom(false)
                .addDefaultAnimation()
                .show();

        final EditText com_et = dialog.getView(R.id.commont);

        //弹出软键盘
       /* com_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    closeKeyBoard(com_et);
            }
        });*/
        showKeyBoard(com_et);

        // 如果setonclicklistner放到上面，获取不到，edittext的内容，以后可能会操作一些特殊的，比如listview  recyclelistview等，只能通过getview方法得到
        dialog.setOnClickListner(R.id.wb, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, com_et.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
       // Toast.makeText(this, "test onclick", Toast.LENGTH_SHORT).show();
    }
    /**
     * 弹出软键盘
     */
    private void showKeyBoard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }
    /**
     * 收起软键盘
     */
    public void closeKeyBoard(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
