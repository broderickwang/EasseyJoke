package com.hannahxian.easseyjoke;

import com.hannahxian.baselibrary.http.IRetrofitService;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Broderick on 16/9/12.
 */
public interface GetZhihuService extends IRetrofitService {
	@GET("/api/4/start-image/{size}")
	Call<ResponseBody> getSplash(@Path("size") String size);

	@GET("/repos/{owner}/{repo}/contributors")
	Call<ResponseBody> contributors(
			@Path("owner") String owner,
			@Path("repo") String repo);
	@GET("/api/4/news/{time}")
	Call<ResponseBody> getZhihuLastData(@Path("time") String time);

	@GET("/api/4/news/{time}")
	Call<ResponseBody> getZhihuDescData(@Path("time") String time);

	@GET("/api/4/story/{id}/long-comments")
	Call<ResponseBody> getLongComment(@Path("id") String id);

	@GET("/api/4/story/{id}/short-comments")
	Call<ResponseBody> getShortComment(@Path("id") String id);

	@GET(".")
	Call<ResponseBody> Get(@QueryMap Map<String,String>options);
}
