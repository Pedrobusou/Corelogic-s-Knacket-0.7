package uk.co.ribot.Knacket.data.api;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.ribot.Knacket.data.api.model.request.AdRequest;
import uk.co.ribot.Knacket.data.api.model.request.LoginRequest;
import uk.co.ribot.Knacket.data.api.model.request.RegisterRequest;
import uk.co.ribot.Knacket.data.api.model.request.UserRequest;
import uk.co.ribot.Knacket.data.api.model.response.AdsResponse;
import uk.co.ribot.Knacket.data.api.model.response.TokenResponse;
import uk.co.ribot.Knacket.data.model.Ad;

public interface RestService {
    @POST("register")
    Observable<TokenResponse> register(@Body RegisterRequest registerRequest);

    @POST("login")
    Observable<TokenResponse> login(@Body LoginRequest loginRequest);

    @Headers("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxOCIsImlzcyI6Imh0dHA6XC9cLzM3LjEzOS4yNi4xOFwvYXBpXC92MVwvbG9naW4iLCJpYXQiOjE0NjAwMjM1MDQsImV4cCI6MTQ2MDAyNzEwNCwibmJmIjoxNDYwMDIzNTA0LCJqdGkiOiIxNDdkZGZhNDcwNzg1ZDJlYzg2MGRiNGI2MDRmZTc1MyJ9.JRwd056uXsjaIVRZw0rN_9cbgJPUsBRfkCAM-2WGOu0")
    @POST("create ad")
    Observable<Void> createAd(@Body AdRequest adRequest);

    @POST("create user profile")
    Observable<UserRequest> createUser(@Body UserRequest userRequest);

    @GET("ads")
    Observable<AdsResponse> getAdsWeekend(@Header("Authorization") String token, @Query("longitude_start") String longitude, @Query("latitude_start") String latitude, @Query("limit") int limit, @Query("weekend") int time);

    @GET("ads")
    Observable<List<Ad>> getAdsEvening(@Header("Authorization") String token, @Query("longitude_start") String longitude, @Query("latitude_start") String latitude, @Query("limit") int limit, @Query("evening") int time);

    @GET("ads")
    Observable<List<Ad>> getAdsWeekday(@Header("Authorization") String token, @Query("longitude_start") String longitude, @Query("latitude_start") String latitude, @Query("limit") int limit, @Query("weekday") int time);

    @GET("ads")
    Observable<List<Ad>> getAdsWithId(@Query("app-id") String appHash, @Query("id") String id);
}