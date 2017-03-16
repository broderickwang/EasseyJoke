package com.hannahxian.baselibrary.http;

import android.content.Context;

import java.util.Map;
import java.util.Objects;

/**
 * 引擎规范
 * Created by hannahxian on 2017/3/12.
 */

public interface IHttpEngine {
	//POST
	void post(Context context, String url, Map<String, Object> params, EngineCallback callback);

	//GET
	void get(Context context, String url, Map<String, Object> params, EngineCallback callback);

	//Download

	//Upload

	//https添加证书
}
