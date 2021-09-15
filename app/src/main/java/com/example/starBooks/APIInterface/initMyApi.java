package com.example.starBooks.APIInterface;

import com.example.starBooks.DTO.LoginRequest;
import com.example.starBooks.DTO.LoginResponse;
import com.example.starBooks.DTO.Page;
import com.example.starBooks.DTO.RegisterRequest;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface initMyApi {
    @GET("/api/books")
    Call<Page> getPageResponse(@Query("sort") String sort,
                               @Query("page") int page,
                               @Query("size") int size);

    @POST("/api/signup")
    Call<Response> getSignUp(@Body RegisterRequest registerRequest);

    @POST("/api/login")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

}