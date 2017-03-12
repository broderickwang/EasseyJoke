package com.hannahxian.baselibrary.http;

import android.content.Context;

import java.util.Map;

/**
 * Created by hannahxian on 2017/3/12.
 */

public interface EngineCallback {
    //错误
    public void onError(Exception e);

    //成功
    public void onSuccess(String result);

    //在执行之前会回调的方法
    public void onPreExcute(Context context, Map<String,Object> params);

    //默认的回调
    public final EngineCallback DEFAULTCALLBACK = new EngineCallback() {
        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onSuccess(String result) {

        }

        @Override
        public void onPreExcute(Context context, Map<String, Object> params) {

        }
    };
}
