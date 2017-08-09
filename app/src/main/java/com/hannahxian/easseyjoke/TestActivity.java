package com.hannahxian.easseyjoke;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.hannahxian.baselibrary.base.SelectImageActivity;
import com.hannahxian.baselibrary.permission.PermissionFailed;
import com.hannahxian.baselibrary.permission.PermissionHelper;
import com.hannahxian.baselibrary.permission.PermissionSuccess;
import com.hannahxian.baselibrary.utils.CustumToast;
import com.hannahxian.baselibrary.utils.ImageSelector;
import com.hannahxian.baselibrary.utils.PermissionUtils;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestActivity extends AppCompatActivity {
    private static final int CALL_PHONE_REQUEST_CODE = 0x0011;

    private static final int CODE_FOR_MULTIPLE_PERMISSION = 0x0022;

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
               // ImageSelector.create().count(9).camera(true).multi().origenlist(al).start(TestActivity.this,1);
                /*int permission = ContextCompat.checkSelfPermission(TestActivity.this, Manifest.permission.CALL_PHONE);
                //返回值 只有两个 授予 PERMISSION_GRANTED  拒绝 PERMISSION_DENIED
                if (permission == PackageManager.PERMISSION_GRANTED) {
                    //授予过，直接拨打电话
                    callPhone();
                } else {
                    //需要申请权限
                    //第一个申请的字符串数组 第二个请求吗用来获取用户反馈的
                    // ActivityCompat.requestPermissions(TestActivity.this,new String[]{Manifest.permission.CALL_PHONE},CALL_PHONE_REQUEST_CODE);
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_REQUEST_CODE);
                }*/
                PermissionHelper.with(TestActivity.this)
                        .requestPermission( Manifest.permission.CALL_PHONE,Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA,Manifest.permission.READ_CONTACTS)
                        .requestCode(CODE_FOR_MULTIPLE_PERMISSION)
                        .request();

                /*Toast t = new Toast(TestActivity.this);
                t.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);
                t.setView(LayoutInflater.from(TestActivity.this).inflate(R.layout.toast_view,null));
                t.setDuration(Toast.LENGTH_LONG);
                t.show();*/
                CustumToast.makeToast(TestActivity.this,"stest",Toast.LENGTH_LONG,CustumToast.CENTER).show();

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /*if (requestCode == CALL_PHONE_REQUEST_CODE) {
            if (grantResults != null && grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //授予了
                    callPhone();
                }
            }else{
                Toast.makeText(this, "用户拒绝了！", Toast.LENGTH_SHORT).show();
            }
        }*/
       PermissionHelper.requestPermissionResult(this,requestCode,permissions);
    }



    @PermissionSuccess(requestCode = CODE_FOR_MULTIPLE_PERMISSION)
    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:18561588037");
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    @PermissionFailed(requestCode = CODE_FOR_MULTIPLE_PERMISSION)
    private void callCancle(){
        Toast.makeText(this, "拒绝打电话", Toast.LENGTH_SHORT).show();
    }


   /* @PermissionFailed(requestCode = CALL_PHONE_REQUEST_CODE)
    private void exfailed(){
        Toast.makeText(this, "拒绝内存卡读取", Toast.LENGTH_SHORT).show();
    }
    @PermissionSuccess(requestCode = CALL_PHONE_REQUEST_CODE)
    private void exSuccess(){
        Toast.makeText(this, "允许了内存卡读取", Toast.LENGTH_SHORT).show();
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1 && resultCode==SelectImageActivity.SELECT_RESULT_OK){
            al = data.getStringArrayListExtra(SelectImageActivity.EXTRA_RESULT);
        }
        /*for (int i = 0; i < al.size(); i++) {
            Toast.makeText(this, al.get(i), Toast.LENGTH_SHORT).show();
        }*/
    }
}
