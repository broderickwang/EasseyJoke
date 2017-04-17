package com.hannahxian.easseyjoke;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Broderick on 2017/4/17.
 */

public class HttpMethodsRetrofit {
	public static final String BASE_URL = "https://api.douban.com/v2/movie/";

	private static final int DEFAULT_TIMEOUT = 5;

	private Retrofit mRetrofit;

	private MovieService mMovieService;

	private HttpMethodsRetrofit(){
		OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
		httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

		mRetrofit = new Retrofit.Builder()
				.client(httpClientBuilder.build())
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl(BASE_URL)
				.build();

		mMovieService = mRetrofit.create(MovieService.class);
	}

	public static class SingletonHolder{
		private static final HttpMethodsRetrofit INSTANCE = new HttpMethodsRetrofit();
	}

	public static HttpMethodsRetrofit getInstance(){
		return SingletonHolder.INSTANCE;
	}

	public void getTopMovie(Observer<MovieEnty> subscriber, int start, int count){
		mMovieService.getTopMovie(start,count)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(subscriber);

	}

}
