package com.imed.medcare.network;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestClient {

    //public static final String BASE_URL = "http://35.198.26.74/";
    //local
    //public static final String BASE_URL = "http://192.168.2.130:8000/";
    //test cliente
    //private
    //public static final String BASE_URL = "https://medcare.jumpittlabs.cl/";
    //develop
    //public static final String BASE_URL = "https://dev.medcare.jumpittlabs.cl/";
    //imed prod
    public static final String BASE_URL = "http://pre-medcare.i-med.cl/";

    private static final int CONNECT_TIMEOUT = 30;
    private static final int WRITE_TIMEOUT = 30;
    private static final int READ_TIMEOUT = 30;

    private static API api;

    static {
        setup();
    }

    private RestClient() {
    }

    public static API get() {
        return api;
    }

    private static void setup() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gsonDeserialize = new GsonBuilder()
                .create();


        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonDeserialize))
                .client(okHttpClient)
                .build();

        api = retrofit.create(API.class);
    }

}