package com.hannahxian.easseyjoke;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hannahxian.baselibrary.IOC.CheckNet;
import com.hannahxian.baselibrary.IOC.OnClick;
import com.hannahxian.baselibrary.IOC.ViewById;
import com.hannahxian.baselibrary.dialog.AlertDialog;
import com.hannahxian.baselibrary.http.HttpUtils;
import com.hannahxian.easseyjoke.mode.ResultBean;
import com.hannahxian.framelibrary.BaseSkinActivity;
import com.hannahxian.framelibrary.HttpCallBack;

public class MainActivity extends BaseSkinActivity {

    @ViewById(R.id.test_tv)
    private TextView mTextView;

    private int mPage;

    @Override
    protected void initData() {
        /*HttpUtils.with(this).url("http://news-at.zhihu.com/api/4/news/latest").get().excute(new EngineCallback() {
            @Override
            public void onError(Exception e) {

            }
http://is.snssdk.com/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-101&message_cursor=-1
&longitude=116.4121485&latitude=39.9365054&am_longitude=116.41828&am_latitude=39.937848&am_city=%E5%8C%97%E4%BA%AC%E5%B8%82
&am_loc_time=1483686438786&count=30&min_time=1483929653&screen_width=1080&iid=7164180604&device_id=34822199408&ac=wifi
&channel=baidu&aid=7&app_name=joke_essay&version_code=590&version_name=5.9.0&device_platform=android&ssmix=a
&device_type=Nexus%2B5&device_brand=google&os_api=25&os_version=7.1&uuid=359250050588035&openudid=12645e537a2f0f25
&manifest_version_code=590&resolution=1080*1776&dpi=480&update_version_code=5903
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "onSuccess: "+result );
            }
        });*/
        HttpUtils.with(this).url("http://is.snssdk.com/neihan/stream/mix/v1/").addParam("uuid","359250050588035")
                .addParam("openudid","12645e537a2f0f25").get().excute(new HttpCallBack<ResultBean>() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onSuccess(ResultBean result) {
                //可以取消进度条
                //转换成可操作的对象

                Log.i("TAG", "onSuccess: Main = "+result.getData().getTip() );
            }

            @Override
            public void onPreExcute() {
                super.onPreExcute();
                //可以加载进度条
                Log.i("TAG", "onPreExcute: MainActivity");
            }
        });
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
