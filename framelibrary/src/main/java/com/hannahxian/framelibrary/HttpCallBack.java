package com.hannahxian.framelibrary;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.hannahxian.baselibrary.http.EngineCallback;
import com.hannahxian.baselibrary.http.HttpUtils;

import java.util.Map;
import java.util.zip.GZIPOutputStream;

/**
 * Created by hannahxian on 2017/3/12.
 */

public abstract class HttpCallBack<T> implements EngineCallback {
    @Override
    public void onPreExcute(Context context, Map<String, Object> params) {
        //大大方方的添加公用方法 与项目业务逻辑有关的
//        params.put("1","1");
        //  &=&=&=480&=

        Log.i("TAG", "onPreExcute: HttpCallBack");

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


        onPreExcute();
    }

    //开始执行
    public void onPreExcute() {

    }

    @Override
    public void onSuccess(String result) {
        Log.i("TAG", "onSuccess: "+result);

        Gson gson = new Gson();
        T objResult = (T) gson.fromJson(result,HttpUtils.analysisClazzInfo(this));

        onSuccess(objResult);

    }
    //返回可以直接操作的对象
    public abstract void onSuccess(T result);
}
