package com.example.starBooks.APIInterface;

import com.example.starBooks.dto.Book;
import com.example.starBooks.dto.CheckIdResponse;
import com.example.starBooks.dto.CommentRegisterRequest;
import com.example.starBooks.dto.CommentResponse;
import com.example.starBooks.dto.HeartResponse;
import com.example.starBooks.dto.LoginRequest;
import com.example.starBooks.dto.LoginResponse;
import com.example.starBooks.dto.Page;
import com.example.starBooks.dto.RegisterRequest;
import com.example.starBooks.dto.RegisterResponse;
import com.example.starBooks.dto.ResponseMessage;

import okhttp3.ResponseBody;
import retrofit2.Call;
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
    @GET("/api/books/{book_id}/comments")
    Call<CommentResponse> getCommentList(@Path("book_id") int bookId);

    @POST("/api/books/{book_id}/comments")
    Call<ResponseMessage> requestRegisterComment(@Header("Authorization") String accessToken, @Path("book_id") int bookId, @Body CommentRegisterRequest commentRegisterRequest);

    //리뷰 삭제
    @DELETE("/api/books/{book_id}/comments/{comment_id}")
    Call<ResponseMessage> requestDeleteComment(@Header("Authorization") String accessToken, @Path("book_id") int bookId, @Path("comment_id") long commentId);

    //리뷰 수정
    @PUT("/api/books/{book_id}/comments/{comment_id}")
    Call<ResponseMessage> requestReviseComment(@Header("Authorization") String accessToken,@Path("book_id") int bookId, @Path("comment_id") long commentId);

    //좋아요 조회
    @GET("/api/books/{book_id}/heart")
    Call<HeartResponse> getHeart(@Header("Authorization") String accessToken, @Path("book_id") int bookId);

    //좋아요 클릭
    @POST("/api/books/{book_id}/heart")
    Call<ResponseMessage> clickHeart(@Header("Authorization") String accessToken, @Path("book_id") int bookId);

    //좋아요 취소
    @DELETE("/api/books/{book_id}/heart")
    Call<ResponseMessage> requestDeleteHeart(@Header("Authorization") String accessToken, @Path("book_id") int bookId);

    /*@POST("/api/reissue")
    Call<ResponseMessage> requestRefreshToken()*/





}
