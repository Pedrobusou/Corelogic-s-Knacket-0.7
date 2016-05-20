package uk.co.ribot.Knacket.data.model;

import java.util.ArrayList;

public class Ad {
    String time, text, price, media_url, duration, location, location_latitude, location_longitude, deleted_at, created_at, updated_at, distance;
    String id, tag_id, user_id, buyer_brings_gear;

    UserProfile userProfile;
    User user;
    Tag tag;

    public Ad(){}

    public Ad(Ad ad){
        this.time = ad.getTime();
        this.text = ad.getText();
        this.price = ad.getPrice();
    }

    public ArrayList<Ad> add6Ads(){
        ArrayList<Ad> ads = new ArrayList<>();
        ads.add(new Ad("3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr"));
        ads.add(new Ad("3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "3000kr"));
        ads.add(new Ad("3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr"));
        ads.add(new Ad("3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr"));
        ads.add(new Ad("3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "3000kr"));
        ads.add(new Ad("3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr"));
        return ads;
    }

    public Ad(String time, String text, String price){
        this.time = time;
        this.text = text;
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation_latitude() {
        return location_latitude;
    }

    public void setLocation_latitude(String location_latitude) {
        this.location_latitude = location_latitude;
    }

    public String getLocation_longitude() {
        return location_longitude;
    }

    public void setLocation_longitude(String location_longitude) {
        this.location_longitude = location_longitude;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }



    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyer_brings_gear() {
        return buyer_brings_gear;
    }

    public void setBuyer_brings_gear(String buyer_brings_gear) {
        this.buyer_brings_gear = buyer_brings_gear;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }
}