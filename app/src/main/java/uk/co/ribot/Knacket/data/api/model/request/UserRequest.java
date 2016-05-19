package uk.co.ribot.Knacket.data.api.model.request;

/**
 * Created by pedroramos on 19.05.16.
 */
public class UserRequest {
    public String description, location_latitude, location_longitude, location;

    public UserRequest(String description, String location_latitude, String location_longitude, String location){
        this.description = description;
        this.location_latitude = location_latitude;
        this.location_longitude = location_longitude;
        this.location = location;
    }
}