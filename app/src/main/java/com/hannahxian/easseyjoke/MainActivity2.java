package com.hannahxian.easseyjoke;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hannahxian.baselibrary.IOC.CheckNet;
import com.hannahxian.baselibrary.IOC.Column;
import com.hannahxian.baselibrary.IOC.OnClick;
import com.hannahxian.baselibrary.IOC.ViewById;
import com.hannahxian.baselibrary.dialog.AlertDialog;
import com.hannahxian.baselibrary.http.HttpUtils;
import com.hannahxian.baselibrary.http.RetrofitEngine;
import com.hannahxian.easseyjoke.mode.Cuse;
import com.hannahxian.easseyjoke.mode.Person;
import com.hannahxian.easseyjoke.mode.ResultBean;
import com.hannahxian.framelibrary.BaseSkinActivity;
import com.hannahxian.framelibrary.DefaultNavigationBar;
import com.hannahxian.framelibrary.DefaultNavigationBar2;
import com.hannahxian.framelibrary.HttpCallBack;
import com.hannahxian.framelibrary.db.DaoSupportFactory;
import com.hannahxian.framelibrary.db.IDaoSupport;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscription;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends BaseSkinActivity {

    @ViewById(R.id.test_tv)
    private TextView mTextView;

    @Column("student-name")
    private String name = "sdf";

    private IDaoSupport<Cuse> cuseDao;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] READ = {Manifest.permission.READ_EXTERNAL_STORAGE};
    private static String[] WRITE ={ Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static String[] CAMERA = { Manifest.permission.CAMERA};

    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};

    private TextView mResult;

    @Override
    protected void initData() {
        verifyStoragePermissions(this);
//        requertData();
//        retrofit2();
    }

    @Override
    protected void initView() {
        mTextView = viewById(R.id.test_tv);
        mResult = viewById(R.id.result);
    }

    @Override
    protected void initTile() {
        DefaultNavigationBar2 navigationBar2 =
                new DefaultNavigationBar2
                        .Builder(this, (ViewGroup) findViewById(R.id.content))
                        .setTitle("投稿")
                        .setRightText("发布")
                        .setRightClickListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity2.this, "ceshi", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .builder();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }


    //@CheckNet           //没网不执行，打印没网的tosast
    @OnClick({R.id.test_tv,R.id.test_iv,R.id.createtb,R.id.create1,R.id.query1,R.id.retro})
    private void onClick(View v){
        switch (v.getId()){
            case R.id.test_iv:
            case R.id.test_tv:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setContentView(R.layout.detail_comment_dialog)

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
                        Toast.makeText(MainActivity2.this, com_et.getText().toString(), Toast.LENGTH_SHORT).show();
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
            case R.id.retro:
                Toast.makeText(this, "sd", Toast.LENGTH_SHORT).show();
                retrofit();
                break;
        }


    }
    private void retrofit(){

        Observer<MovieEnty> subscriber = new Observer<MovieEnty>() {


            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieEnty value) {
//                mResult.setText(value.getCount());
                Toast.makeText(MainActivity2.this, value.getCount(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                mResult.setText(e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity2.this, "complete", Toast.LENGTH_SHORT).show();
            }
        };

        HttpMethodsRetrofit.getInstance().getTopMovie(subscriber,0,10);

    }
    private void retrofit2(){
        Map<String,String> params = new HashMap<>();
        params.put("webp","1");
        params.put("essence","1");
        params.put("content_type","-10");
        params.put("message_cursor","-1");
        params.put("longitude","116.4121485");
        params.put("latitude","39.9365054");
        params.put("am_longitude","116.41828");
        params.put("am_latitude","39.937848");
        params.put("am_city","北京市");params.put("am_loc_time","1483686438786");
        params.put("count","30");
        params.put("min_time","1483929653");
        params.put("screen_width","1080");
        params.put("device_id","34822199408");
        params.put("ac","wifi");
        params.put("channel","baidu");
        params.put("aid","7");
        params.put("app_name","joke_essay");
        params.put("version_code","590");
        params.put("version_name","5.9.0");
        params.put("device_platform","android");
        params.put("ssmix","a");
        params.put("device_type","Nexus%2B5");
        params.put("device_brand","google");
        params.put("os_api","25");
        params.put("os_version","7.1");
        params.put("manifest_version_code","590");
        params.put("resolution","1080*1776");
        params.put("dpi","480");
        params.put("update_version_code","5903");
        params.put("mpic","1");
        params.put("uuid","359250050588035");
        params.put("openudid","12645e537a2f0f25");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://is.snssdk.com/neihan/stream/mix/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetZhihuService github = retrofit.create(GetZhihuService.class);
        Call<ResponseBody> call = github.Get(params);

        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("TAG", "onResponse: "+response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void requertData(){
        HttpUtils.with(this)
                .url("http://is.snssdk.com/neihan/stream/mix/v1/")
                .addParam("uuid","359250050588035")
                .addParam("openudid","12645e537a2f0f25")
                .exchangeEngine(new RetrofitEngine(GetZhihuService.class))
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
                        Log.i("TAG", "onPreExcute: MainActivity2");
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
