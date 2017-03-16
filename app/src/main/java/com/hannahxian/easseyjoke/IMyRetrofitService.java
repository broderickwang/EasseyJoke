package com.hannahxian.easseyjoke;

import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Broderick on 2017/3/16.
 */

public interface IMyRetrofitService   {
	@GET("neihan/stream/mix/v1/")
	Call<ResponseBody> post(@QueryMap Map<String,String> options);
}
