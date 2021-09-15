package com.example.starBooks.APIInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    
    private static com.example.starBooks.APIInterface.RetrofitClient instance = null;
    private static com.example.starBooks.APIInterface.initMyApi initMyApi;
    private static String baseUrl = "http://13.209.146.204:8080/";



    private RetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        initMyApi = retrofit.create(initMyApi.class);
    }

    public static com.example.starBooks.APIInterface.RetrofitClient getInstance() {
        if (instance == null) {
            instance = new com.example.starBooks.APIInterface.RetrofitClient();
        }
        return instance;
    }

    public static initMyApi getRetrofitInterface() {
        return initMyApi;
    }
}
