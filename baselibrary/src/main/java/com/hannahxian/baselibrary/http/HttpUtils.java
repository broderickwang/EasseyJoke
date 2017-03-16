package com.hannahxian.baselibrary.http;

import android.content.Context;
import android.util.ArrayMap;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 自己的一套实现
 * Created by hannahxian on 2017/3/12.
 */

public class HttpUtils {

	private static final int POST_TYPE = 0x0011;
	private static final int GET_TYPE = 0x0022;

	//参数采用链式调用，方便扩展
	//地址
	private String mUrl;
	//提交方式
	private int mType = GET_TYPE;
	//上下文
	private Context mContext;
	//往后台发送的参数
	private Map<String, Object> mParams;

	private HttpUtils(Context context) {
		mContext = context;
//        mParams = new ArrayMap<>(); //ArrayMap比HashMap更高效
		mParams = new HashMap<>();
	}

	public static HttpUtils with(Context context) {
		return new HttpUtils(context);
	}

	public HttpUtils url(String url) {
		mUrl = url;
		return this;
	}

	//
	public HttpUtils post() {
		mType = POST_TYPE;
		return this;
	}

	//
	public HttpUtils get() {
		mType = GET_TYPE;
		return this;
	}

	//默认okhttp引擎
	private static IHttpEngine mHttpEngine = new OkHttpEngine();

	//初始化引擎
	public static void init(IHttpEngine engine) {
		mHttpEngine = engine;
	}

	//添加参数
	public HttpUtils addParam(String key, Object value) {
		mParams.put(key, value);
		return this;
	}

	//添加多个参数
	public HttpUtils addParams(Map<String, Object> param) {
		mParams.putAll(param);
		return this;
	}

	//添加回调，执行
	public void excute(EngineCallback callback) {

		//1.baselibrary不包含业务逻辑
		//2.每一个项目，如果有多条业务线

		//让callback回调去
		callback.onPreExcute(mContext, mParams);

		if (callback == null) {
			callback = EngineCallback.DEFAULTCALLBACK;
		}
		//判断执行方法

		if (mType == POST_TYPE) {
			post(mUrl, mParams, callback);
		}
		if (mType == GET_TYPE) {
			get(mUrl, mParams, callback);
		}
	}

	public void excute() {
		excute(null);
	}

	//每次可以自带引擎，改变引擎
	public HttpUtils exchangeEngine(IHttpEngine httpEngine) {
		mHttpEngine = httpEngine;
		return this;
	}


	private void post(String url, Map<String, Object> params, EngineCallback callback) {
		mHttpEngine.post(mContext, url, params, callback);
	}

	private void get(String url, Map<String, Object> params, EngineCallback callback) {
		mHttpEngine.get(mContext, url, params, callback);
	}

	/**
	 * 拼接参数
	 */
	public static String jointParams(String url, Map<String, Object> params) {
		if (params == null || params.size() <= 0) {
			return url;
		}

		StringBuffer stringBuffer = new StringBuffer(url);
		if (!url.contains("?")) {
			stringBuffer.append("?");
		} else {
			if (!url.endsWith("?")) {
				stringBuffer.append("&");
			}
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "&");
		}

		stringBuffer.deleteCharAt(stringBuffer.length() - 1);

		return stringBuffer.toString();
	}

	/**
	 * 解析一个类上面的class信息
	 */
	public static Class<?> analysisClazzInfo(Object object) {
		Type genType = object.getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		return (Class<?>) params[0];
	}
}
