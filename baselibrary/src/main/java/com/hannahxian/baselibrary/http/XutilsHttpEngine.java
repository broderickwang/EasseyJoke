package com.hannahxian.baselibrary.http;

import android.content.Context;
import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

public class XutilsHttpEngine implements IHttpEngine {

    @Override
    public void post(Context context, String url, Map<String, Object> params, final EngineCallback callback) {
        final String jointUrl = HttpUtils.jointParams(url, params);//打印
        Log.i("xutils POST请求路径：", jointUrl);
        x.http().post(changeParams(url, params), new Callback.CommonCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                callback.onSuccess(result.toString());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onError((Exception) ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onError(cex);
            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void get(Context context, String url, Map<String, Object> params, final EngineCallback callback) {
        x.http().get(changeParams(url, params), new Callback.CommonCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                callback.onSuccess(result.toString());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onError((Exception)ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onError(cex);
            }

            @Override
            public void onFinished() {

            }
        });
    }

    private RequestParams changeParams(String url,Map<String,Object> params){
        RequestParams rParams = new RequestParams(url) ;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            rParams.addBodyParameter(entry.getKey(),entry.getValue().toString());
        }
        return rParams;
    }
}
