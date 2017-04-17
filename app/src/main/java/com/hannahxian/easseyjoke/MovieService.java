package com.hannahxian.easseyjoke;



import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Broderick on 2017/4/17.
 */

public interface MovieService {
	@GET("top250")
	Observable<MovieEnty> getTopMovie(@Query("start")int start, @Query("count")int count);
}
