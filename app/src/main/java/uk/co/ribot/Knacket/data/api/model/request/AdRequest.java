package uk.co.ribot.Knacket.data.api.model.request;

/**
 * Created by pedroramos on 19.05.16.
 */
public class AdRequest {
    public String tag_id, buyer_brings_gear, text, media_url, time, duration, location, price, location_longitude, location_latitude, title;

    public AdRequest(String tag_id, String buyer_brings_gear, String text, String media_url, String time, String duration,
                     String location, String price, String location_longitude, String location_latitude, String title){
        this.tag_id = tag_id;
        this.buyer_brings_gear = buyer_brings_gear;
        this.text = text;
        this.media_url = media_url;
        this.time = time;
        this.duration = duration;
        this.location = location;
        this.price = price;
        this.location_longitude = location_longitude;
        this.location_latitude = location_latitude;
        this.title = title;
    }
}