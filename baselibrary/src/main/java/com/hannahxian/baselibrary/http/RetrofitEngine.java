package com.hannahxian.baselibrary.http;

import android.content.Context;
import android.util.Log;

import org.xutils.http.RequestParams;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Broderick on 2017/3/17.
 */

public class RetrofitEngine<T> implements IHttpEngine {
	Class<T> mClaz;

	public RetrofitEngine(Class<T> mClaz) {
		this.mClaz = mClaz;
	}

	@Override
	public void post(Context context, String url, Map<String, Object> params, EngineCallback callback) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(url)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		IRetrofitService service = (IRetrofitService)retrofit.create(mClaz);
		Call<ResponseBody> call =  service.Get(changeParams(params));
		call.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				Log.i("TAG", "onResponse: "+response);
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {

			}
		});
	}

	@Override
	public void get(Context context, String url, Map<String, Object> params, EngineCallback callback) {

	}
	private Map<String,String> changeParams( Map<String, Object> params) {
		Map<String,String> rParams = new HashMap<>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			rParams.put(entry.getKey(), entry.getValue().toString());
		}
		return rParams;
	}
}
