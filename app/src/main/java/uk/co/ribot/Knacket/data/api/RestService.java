package uk.co.ribot.Knacket.data.api;

import uk.co.ribot.Knacket.data.PreferencesManager;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.ribot.Knacket.data.api.model.request.AdRequest;
import uk.co.ribot.Knacket.data.api.model.request.LoginRequest;
import uk.co.ribot.Knacket.data.api.model.request.RegisterRequest;
import uk.co.ribot.Knacket.data.api.model.request.UserRequest;
import uk.co.ribot.Knacket.data.model.Ad;

public interface RestService {

    @POST("activation/register")
    Observable<RegisterRequest> register(@Body RegisterRequest registerRequest);

    @POST("activation/login")
    Observable<LoginRequest> login(@Body LoginRequest loginRequest);

    @POST("create ad")
    Observable<AdRequest> createAd(@Body AdRequest adRequest);

    @POST("create user profile")
    Observable<UserRequest> createUser(@Body UserRequest userRequest);

    /*
    @GET("contacts/get-avatar")
    Observable<UserAvatarResponse> getUserAvatar(@Query("app-id") String appHash, @Query("msisdn") String msisdn);


    @GET("groups")
    Observable<List<Group>> getGroups(@Query("app-id") String appHash);

    @POST("user/avatar")
    Observable<Void> setUserAvatar(@Body AddAvatarRequest request);


    @POST("application/edit-gcm-id")
    Observable<Void> updateTokenRS(@Body EditGcmIdRequest update);*/
}