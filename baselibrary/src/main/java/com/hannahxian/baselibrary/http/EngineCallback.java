package com.hannahxian.baselibrary.http;

/**
 * Created by hannahxian on 2017/3/12.
 */

public interface EngineCallback {
    //错误
    public void onError(Exception e);

    //成功
    public void onSuccess(String result);

    //默认的回调
    public final EngineCallback DEFAULTCALLBACK = new EngineCallback() {
        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onSuccess(String result) {

        }
    };
}
