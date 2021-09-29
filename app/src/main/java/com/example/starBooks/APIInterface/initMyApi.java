package com.example.starBooks.APIInterface;

import com.example.starBooks.dto.Book;
import com.example.starBooks.dto.CheckIdResponse;
import com.example.starBooks.dto.CommentResponse;
import com.example.starBooks.dto.HeartResponse;
import com.example.starBooks.dto.LoginRequest;
import com.example.starBooks.dto.LoginResponse;
import com.example.starBooks.dto.Page;
import com.example.starBooks.dto.RegisterRequest;
import com.example.starBooks.dto.RegisterResponse;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface

initMyApi {
    @GET("/api/books")
    Call<Page> getPageResponse(@Query("sort") String sort,
                               @Query("page") int page,
                               @Query("size") int size);

    @POST("/api/signup")
    Call<RegisterResponse> getSignUp(@Body RegisterRequest registerRequest);

    @POST("/api/login")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @GET("/api/check/{userId}")
    Call<CheckIdResponse> getCheckId(@Path("userId") String userId);

    @GET("/api/books/{books_id}")
    Call<Book> getDetailBookList(@Path("books_id") int bookId);


    //리뷰 조회
    @GET("/api/books/{bookId}/comments")
    Call<CommentResponse> getCommentList(@Header("accessToken") String accessToken, @Path("books_Id") int bookId);

    //리뷰 삭제
    @DELETE("/api/books/{book_id}/comments/{comment_id}")
    Call<Response<Result>> requestDeleteComment(@Header("accessToken") String accessToken, @Path("books_Id") int bookId, @Path("comment_id") long commentId);

    //리뷰 수정
    @PUT("/api/books/{book_id}/comments/{comment_id}")
    Call<Response<Result>> requestReviseComment(@Header("accessToken") String accessToken,@Path("books_Id") int bookId, @Path("comment_id") long commentId);

    //좋아요 조회
    @GET("/api/books/{book_id}/heart")
    Call<HeartResponse> getHeart(@Header("accessToken") String accessToken, @Path("books_Id") int bookId);

    //좋아요 클릭
    @POST("/api/books/{book_id}/heart")
    Call<Response<Result>> ClickHeart(@Header("accessToken") String accessToken, @Path("books_Id") int bookId);

    //좋아요 취소
    @DELETE("/api/books/{book_id}/heart")
    Call<Response<Result>> requestDeleteHeart(@Header("accessToken") String accessToken, @Path("books_Id") int bookId);



}
