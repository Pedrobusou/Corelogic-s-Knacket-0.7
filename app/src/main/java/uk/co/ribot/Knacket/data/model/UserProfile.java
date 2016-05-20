package uk.co.ribot.Knacket.data.model;

public class UserProfile {
    String description, picture_url, video_url, location,
           location_longitude, location_latitude, created_at, updated_at;
    String id, user_id;

    public UserProfile(){}

    public UserProfile(String description, String picture_url, String video_url, String location, String location_longitude, String location_latitude, String created_at, String updated_at, String id, String user_id) {
        this.description = description;
        this.picture_url = picture_url;
        this.video_url = video_url;
        this.location = location;
        this.location_longitude = location_longitude;
        this.location_latitude = location_latitude;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
