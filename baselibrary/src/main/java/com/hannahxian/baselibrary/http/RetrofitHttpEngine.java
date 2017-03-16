package com.hannahxian.baselibrary.http;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Broderick on 2017/3/16.
 */

public class RetrofitHttpEngine<T> implements IHttpEngine {
	Class<T> mClaz;

	public RetrofitHttpEngine(Class<T> mClaz) {
		this.mClaz = mClaz;
	}

	@Override
	public void post(Context context, String url, Map<String, Object> params, final EngineCallback callback) {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(url).build();
//		T t = (T)retrofit.create(mClaz);

		try {
			Method m = mClaz.getMethod("post");

			Call<ResponseBody> call = (Call<ResponseBody>)m.invoke(retrofit.create(mClaz),changeParma(params));
			call.enqueue(new Callback<ResponseBody>() {
				@Override
				public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
					callback.onSuccess(response.toString());
				}

				@Override
				public void onFailure(Call<ResponseBody> call, Throwable t) {
					callback.onError((Exception)t);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	public void get(Context context, String url, Map<String, Object> params,final EngineCallback callback) {

	}
	private Map<String,String> changeParma(Map<String,Object> params){
		Map<String,String> map = new HashMap<>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			map.put(entry.getKey(),entry.getValue().toString());
		}
		return map;
	}
}
