package uk.co.ribot.Knacket.data.api;

import uk.co.ribot.Knacket.data.PreferencesManager;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.ribot.Knacket.data.api.model.request.RegisterRequest;

/**
 * RestAPI for the BablApp service.
 *
 * @see <a href="https://redmine.corelogic.pl/projects/bablapp/wiki/API_REST_-_docs">Api documentation</a>
 */

public interface RestService {

    @POST("activation/register")
    Observable<RegisterRequest> register(@Body RegisterRequest request);

    /*@POST("contacts")
    Observable<ContactsResponse> postContacts(@Body ContactsRequest request);

    @POST("voice-messages")
    Observable<Void> postVoiceMessage(@Body VoiceMessage voiceMessage);

    @GET("contacts/get-avatar")
    Observable<UserAvatarResponse> getUserAvatar(@Query("app-id") String appHash, @Query("msisdn") String msisdn);


    @GET("groups")
    Observable<List<Group>> getGroups(@Query("app-id") String appHash);

    @POST("user/avatar")
    Observable<Void> setUserAvatar(@Body AddAvatarRequest request);


    @POST("application/edit-gcm-id")
    Observable<Void> updateTokenRS(@Body EditGcmIdRequest update);*/
}