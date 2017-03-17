package com.hannahxian.baselibrary.http;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Broderick on 2017/3/17.
 */

public interface IRetrofitService<T> {
	Call<ResponseBody> Get(T t);
	Call<ResponseBody> Post(T t);
}
