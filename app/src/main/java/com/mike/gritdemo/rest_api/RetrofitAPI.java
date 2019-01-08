package com.mike.gritdemo.rest_api;

import com.mike.gritdemo.apputils.GritUtils;
import com.mike.gritdemo.rest_api.service.MoviesAPI;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit helper class for constructing and getting instance for the Rest API call
 */
public class RetrofitAPI {

    private static final int HTTP_CONNECT_TIMEOUT = 5000;
    private static RetrofitAPI retrofitAPI;
    private MoviesAPI moviesAPI;

    private RetrofitAPI() {

        // Always adds the api key params to the service calls
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalUrl = originalRequest.url();
                HttpUrl urlBuilder = originalUrl.newBuilder().addQueryParameter("api_key", GritUtils.API_KEY).build();

                Request.Builder requestBuilder = originalRequest.newBuilder().url(urlBuilder);
                Request newRequest = requestBuilder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()

                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GritUtils.BASE_URL)
                .client(okHttpClient)
                .build();

        moviesAPI = retrofit.create(MoviesAPI.class);

    }

    public static RetrofitAPI getRetrofitInstance() {
        synchronized (RetrofitAPI.class) {

            if (retrofitAPI == null) {
                retrofitAPI = new RetrofitAPI();
            }
        }

        return retrofitAPI;
    }

    public MoviesAPI getMoviesAPI() {
        return moviesAPI;
    }

    public void setMovieMoviesApi(MoviesAPI moviesApi) {
        this.moviesAPI = moviesApi;
    }

}
