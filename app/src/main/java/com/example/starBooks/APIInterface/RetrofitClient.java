package com.example.starBooks.APIInterface;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

import com.example.starBooks.LoginActivity;
import com.example.starBooks.dto.LoginResponse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    
    private static com.example.starBooks.APIInterface.RetrofitClient instance = null;
    private static com.example.starBooks.APIInterface.initMyApi initMyApi;
    private static String baseUrl = "http://13.209.146.204:8080/";
    private String accessToken;

    private LoginActivity loginActivity;



    private RetrofitClient() {


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
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
