package com.hannahxian.easseyjoke;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.hannahxian.baselibrary.http.XutilsHttpEngine;
import com.hannahxian.easseyjoke.mode.Cuse;
import com.hannahxian.easseyjoke.mode.Person;
import com.hannahxian.easseyjoke.mode.ResultBean;
import com.hannahxian.framelibrary.BaseSkinActivity;
import com.hannahxian.framelibrary.HttpCallBack;
import com.hannahxian.framelibrary.db.DaoSupportFactory;
import com.hannahxian.framelibrary.db.IDaoSupport;
import com.hannahxian.framelibrary.utils.PermissionUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends BaseSkinActivity {

    @ViewById(R.id.test_tv)
    private TextView mTextView;

    private IDaoSupport<Cuse> cuseDao;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] READ = {Manifest.permission.READ_EXTERNAL_STORAGE};
    private static String[] WRITE ={ Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static String[] CAMERA = { Manifest.permission.CAMERA};

    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};

    @Override
    protected void initData() {
        verifyStoragePermissions(this);

        x.http().post(new RequestParams("http://192.168.9.68:8990/servlet/UpdateServlet"), new Callback.CommonCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                Log.i("TAG", "onSuccess: "+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError: ", ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

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

    @OnClick({R.id.test_tv,R.id.test_iv,R.id.createtb,R.id.create1,R.id.query1})
    @CheckNet           //没网不执行，打印没网的tosast
    private void onClick(View v){
        switch (v.getId()){
            case R.id.test_iv:
            case R.id.test_tv:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setContentView(R.layout.detail_comment_dialog)
                /*.setOnClickListner(R.id.wb, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "weibo", Toast.LENGTH_SHORT).show();
                    }
                })*/
                        .setText(R.id.txt,"自己设置的")
                        .setTitle("从底部弹出")
                        .fullWindow()
                        .fromBottom(false)
                        .addDefaultAnimation()
                        .show();

                final EditText com_et = dialog.getView(R.id.commont);
                // 如果setonclicklistner放到上面，获取不到，edittext的内容，以后可能会操作一些特殊的，比如listview  recyclelistview等，只能通过getview方法得到
                dialog.setOnClickListner(R.id.wb, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, com_et.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                com_et.setFocusable(true);
                com_et.setFocusableInTouchMode(true);
                com_et.requestFocus();
                InputMethodManager inputManager =
                        (InputMethodManager)com_et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(com_et, 0);
                break;
            case R.id.createtb:
                IDaoSupport<Person> daoSupport = DaoSupportFactory.getFactory().getDao(Person.class);
                //最少的知识原则
//                daoSupport.insert(new Person("marc",22));
                String[] args = {"marc"};
                List<Person>  obs  = daoSupport.query("name=?", args);
                Log.i("TAG", "查询到: "+obs.size()+" 第一条数据是："+obs.get(0).getname() + "-"+obs.get(0).getage());
                break;
            case R.id.create1:
                cuseDao = DaoSupportFactory.getFactory().getDao(Cuse.class);
                cuseDao.insert(new Cuse("语文",2));
                break;
            case R.id.query1:
                String[] args2 = {"语文"};
                List<Cuse>  obs2  = cuseDao.query("mName=?",args2);
                Log.i("TAG", "查询到: "+obs2.size()+"条数据， 第一条数据是："+obs2.get(0).getmName() + "-"+obs2.get(0).getmTime());
                break;
        }


    }

    private void requertData(){
        HttpUtils.with(this)
                .url("http://is.snssdk.com/neihan/stream/mix/v1/")
                .addParam("uuid","359250050588035")
                .addParam("openudid","12645e537a2f0f25")
                .exchangeEngine(new XutilsHttpEngine())
                .post()
                .excute(new HttpCallBack<ResultBean>() {
                    @Override
                    public void onError(Exception e) {
                        Log.e("Tag", "onError: ", e);
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
    /**
     * 弹出软键盘
     */
    private void showKeyBoard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }
    /**
     * 收起软键盘
     */
    public void closeKeyBoard(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    /**
     * 动态申请权限
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }
}
