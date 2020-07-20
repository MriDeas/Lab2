package com.dan.learn.lab2.net;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by: dan
 * Created time: 2020/7/20
 * Description:
 * Modify time:
 */
public interface ApiService {

    //1. 定义函数 包含（接口，请求方法，参数）
    //2. 解析

    @GET("/api/tallies/categories")
    Call<ResponseBody> requestData(@QueryMap HashMap<String, String> map);
}
