package uk.co.ribot.Knacket.data.api;


import java.util.List;

import de.toliart.babbler.data.api.model.Group;
import de.toliart.babbler.data.api.model.VoiceMessage;
import de.toliart.babbler.data.api.model.request.AddAvatarRequest;
import de.toliart.babbler.data.api.model.request.ContactsRequest;
import de.toliart.babbler.data.api.model.request.CreateGroupRequest;
import de.toliart.babbler.data.api.model.request.EditGcmIdRequest;
import de.toliart.babbler.data.api.model.request.RegistrationRequest;
import de.toliart.babbler.data.api.model.response.ContactsResponse;
import de.toliart.babbler.data.api.model.response.CreateGroupResponse;
import de.toliart.babbler.data.api.model.response.RegistrationResponse;
import de.toliart.babbler.data.api.model.response.UserAvatarResponse;
import de.toliart.babbler.data.preferences.PreferencesManager;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * RestAPI for the BablApp service.
 *
 * @see <a href="https://redmine.corelogic.pl/projects/bablapp/wiki/API_REST_-_docs">Api documentation</a>
 */

public interface RestService {

    @POST("activation/register")
    Observable<RegistrationResponse> register(@Body RegistrationRequest request);

    @POST("contacts")
    Observable<ContactsResponse> postContacts(@Body ContactsRequest request);

    /**
     * Get blabs for user.
     *
     * @param appHash required, applicationId of the caller, this parameter is validated for security reasons (can be obtained in {@link PreferencesManager})
     * @param msisdns optional, comma separated values of phone numbers of the authors of the babls
     * @param groups  optional, comma separated values of group ids for which you want to retrieve babls
     * @param data    optional, set to true if you want to retrieve the voice message file (mp4)
     * @param avatar  optional, set to true if you want to receive the avatar file of the author for each babl
     * @return observable with list of blabs
     */
    @GET("voice-messages")
    Observable<List<VoiceMessage>> getVoiceMessages(@Query("app-id") String appHash, @Query("msisdns") String msisdns,
                                                    @Query("groups") String groups, @Query("data") Boolean data,
                                                    @Query("avatar") Boolean avatar);

    @GET("voice-messages/{id}")
    Observable<VoiceMessage> getVoiceMessage(@Path("id") Long voiceMessageId, @Query("app-id") String appHash);

    @GET("voice-messages")
    Observable<List<VoiceMessage>> getVoiceMessages(@Query("app-id") String appHash);

    @GET("voice-messages")
    Observable<List<VoiceMessage>> getVoiceMessagesForNumbers(@Query("app-id") String appHash, @Query("msisdns") String msisdns);

    @GET("voice-messages")
    Observable<List<VoiceMessage>> getVoiceMessagesForGroups(@Query("app-id") String appHash, @Query("groups") long groups);

    @GET("voice-messages")
    Observable<List<VoiceMessage>> getVoiceMessagesForGroupsForUser(@Query("app-id") String appHash, @Query("groups") long groups, @Query("msisdns") String msisdns);

    @POST("voice-messages")
    Observable<Void> postVoiceMessage(@Body VoiceMessage voiceMessage);

    @GET("contacts/get-avatar")
    Observable<UserAvatarResponse> getUserAvatar(@Query("app-id") String appHash, @Query("msisdn") String msisdn);

    /**
     * Use to create or update group.
     *
     * @param request body request. If id = null request creates new group. If id is filled with
     *                server id data existing group is being edited.
     * @return response with a group id
     */
    @POST("groups")
    Observable<CreateGroupResponse> postGroup(@Body CreateGroupRequest request);

    @GET("groups")
    Observable<List<Group>> getGroups(@Query("app-id") String appHash);

    @POST("user/avatar")
    Observable<Void> setUserAvatar(@Body AddAvatarRequest request);

    @POST("groups/remove")
    Observable<Void> deleteGroup(@Body Group group);

    @POST("application/edit-gcm-id")
    Observable<Void> updateTokenRS(@Body EditGcmIdRequest update);
}