package uk.co.ribot.Knacket.data.local.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import uk.co.ribot.Knacket.data.model.UserProfile;

@DatabaseTable(tableName = "user_profile")
public class UserProfileDatabase extends BaseTable{
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PICTURE_URL = "picture_url";
    public static final String COLUMN_IS_VIDEO_URL = "video_url";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_LOCATION_LONGITUDE = "location_longitude";
    public static final String COLUMN_LOCATION_LATITUDE = "location_latitude";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_SERVER_ID = "server_id";
    public static final String COLUMN_USER_ID = "user_id";

    @DatabaseField(columnName = COLUMN_SERVER_ID)
    String serverId;

    @DatabaseField(columnName = COLUMN_USER_ID)
    String user_id;

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    String description;

    @DatabaseField(columnName = COLUMN_PICTURE_URL)
    String picture_url;

    @DatabaseField(columnName = COLUMN_IS_VIDEO_URL)
    String video_url;

    @DatabaseField(columnName = COLUMN_LOCATION)
    String location;

    @DatabaseField(columnName = COLUMN_LOCATION_LONGITUDE)
    String location_longitude;

    @DatabaseField(columnName = COLUMN_LOCATION_LATITUDE)
    String location_latitude;

    @DatabaseField(columnName = COLUMN_CREATED_AT)
    String created_at;

    @DatabaseField(columnName = COLUMN_UPDATED_AT)
    String updated_at;

    public UserProfileDatabase(){}

    public UserProfileDatabase(UserProfile userProfile) {
        this.description = userProfile.getDescription();
        this.picture_url = userProfile.getPicture_url();
        this.video_url = userProfile.getVideo_url();
        this.location = userProfile.getLocation();
        this.location_longitude = userProfile.getLocation_longitude();
        this.location_latitude = userProfile.getLocation_latitude();
        this.created_at = userProfile.getCreated_at();
        this.updated_at = userProfile.getUpdated_at();
        this.serverId = userProfile.getId();
        this.user_id = userProfile.getUser_id();
    }

    public UserProfileDatabase(String description, String picture_url, String video_url, String location, String location_longitude, String location_latitude, String created_at, String updated_at, String id, String user_id) {
        this.description = description;
        this.picture_url = picture_url;
        this.video_url = video_url;
        this.location = location;
        this.location_longitude = location_longitude;
        this.location_latitude = location_latitude;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.serverId = id;
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation_longitude() {
        return location_longitude;
    }

    public void setLocation_longitude(String location_longitude) {
        this.location_longitude = location_longitude;
    }

    public String getLocation_latitude() {
        return location_latitude;
    }

    public void setLocation_latitude(String location_latitude) {
        this.location_latitude = location_latitude;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String id) {
        this.serverId = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "userProfile{" +
                "ID='" + serverId + '\'' +
                ", DESCRIPTION=" + description +
                '}';
    }
}