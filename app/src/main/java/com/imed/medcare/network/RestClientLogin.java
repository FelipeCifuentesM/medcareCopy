package com.imed.medcare.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClientLogin {

    private static final String BASE_URL_LOGIN = "http://mimed.jumpittlabs.cl/";
    //private static final String BASE_URL_LOGIN = "http://192.168.2.142:8000/";
    private static final int CONNECT_TIMEOUT = 30;
    private static final int WRITE_TIMEOUT = 30;
    private static final int READ_TIMEOUT = 30;

    private static API api;

    static {
        setup();
    }

    private RestClientLogin() {
    }

    public static API get() {
        return api;
    }

    private static void setup() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);



        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_LOGIN)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        api = retrofit.create(API.class);
    }

}
