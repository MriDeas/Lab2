package com.dan.learn.lab2.ui.activity;

import android.os.Bundle;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.net.ApiService;
import com.dan.learn.lab2.ui.base.BaseActivity;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 注解
 */
public class AnnotationActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_annotation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("注解");


    }

    private void configThirdNetApi(){
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = okHttpBuilder.build();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("");
        builder.client(okHttpClient);
        Retrofit build = builder.build();

        ApiService apiService = build.create(ApiService.class);

        Call<ResponseBody> call = apiService.requestData(null);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
