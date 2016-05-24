package uk.co.ribot.Knacket.data.local.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

import uk.co.ribot.Knacket.data.model.Ad;
import uk.co.ribot.Knacket.data.model.User;

@DatabaseTable(tableName = "ad")
public class AdDatabase extends BaseTable{
    private final String COLUMN_SERVER_ID = "server_id";
    private final String COLUMN_TAG_ID = "tag_id";
    private final String COLUMN_USER_IS = "user_id";
    private final String COLUMN_TIME = "time";
    private final String COLUMN_TEXT = "text";
    private final String COLUMN_PRICE = "price";
    private final String COLUMN_MEDIA_URL = "media_url";
    private final String COLUMN_DURATION = "duration";
    private final String COLUMN_LOCATION = "location";
    private final String COLUMN_LOCATION_LATITUDE = "location_latitude";
    private final String COLUMN_LOCATION_LONGITUDE = "location_longitude";
    private final String COLUMN_CREATED_AT = "created_at";
    private final String COLUMN_UPDATED_AT = "updated_at";
    private final String COLUMN_DELETED_AT = "deleted_at";
    private final String COLUMN_DISTANCE = "distance";
    private final String COLUMN_BUYER_BRINGS_GEAR = "buyer_brings_gear";
    private final String COLUMN_USER_PROFILE = "user_profile";
    private final String COLUMN_USER = "user";
    private final String COLUMN_TAG = "tag";

    @DatabaseField(columnName = COLUMN_SERVER_ID)
    private String serverId;

    @DatabaseField(columnName = COLUMN_TAG_ID)
    private String tag_id;

    @DatabaseField(columnName = COLUMN_USER_IS)
    private String user_id;

    @DatabaseField(columnName = COLUMN_TIME)
    private String time;

    @DatabaseField(columnName = COLUMN_TEXT)
    private String text;

    @DatabaseField(columnName = COLUMN_PRICE)
    private String price;

    @DatabaseField(columnName = COLUMN_MEDIA_URL)
    private String media_url;

    @DatabaseField(columnName = COLUMN_DURATION)
    private String duration;

    @DatabaseField(columnName = COLUMN_LOCATION)
    private String location;

    @DatabaseField(columnName = COLUMN_LOCATION_LATITUDE)
    private String location_latitude;

    @DatabaseField(columnName = COLUMN_LOCATION_LONGITUDE)
    private String location_longitude;

    @DatabaseField(columnName = COLUMN_CREATED_AT)
    private String created_at;

    @DatabaseField(columnName = COLUMN_UPDATED_AT)
    private String updated_at;

    @DatabaseField(columnName = COLUMN_DELETED_AT)
    private String deleted_at;

    @DatabaseField(columnName = COLUMN_DISTANCE)
    private String distance;

    @DatabaseField(columnName = COLUMN_BUYER_BRINGS_GEAR)
    private String buyer_brings_gear;

    @DatabaseField(columnName = COLUMN_USER_PROFILE,foreign = true)
    private UserProfileDatabase userProfile;

    @DatabaseField(columnName = COLUMN_USER,foreign = true)
    private UserDatabase user;

    @DatabaseField(columnName = COLUMN_TAG,foreign = true)
    private TagDatabase tag;

    public AdDatabase(){}

    public AdDatabase(Ad ad, UserDatabase userDatabase,UserProfileDatabase userProfileDatabase,TagDatabase tagDatabase) {
        this.serverId = ad.getId();
        this.tag_id = ad.getTag_id();
        this.user_id = ad.getUser_id();
        this.time = ad.getTime();
        this.text = ad.getText();
        this.price = ad.getPrice();
        this.media_url = ad.getMedia_url();
        this.duration = ad.getDuration();
        this.location = ad.getLocation();
        this.location_latitude = ad.getLocation_latitude();
        this.location_longitude = ad.getLocation_longitude();
        this.created_at = ad.getCreated_at();
        this.updated_at = ad.getUpdated_at();
        this.deleted_at = ad.getDeleted_at();
        this.distance = ad.getDistance();
        this.buyer_brings_gear = ad.getBuyer_brings_gear();
        this.userProfile = userProfileDatabase;
        this.user = userDatabase;
        this.tag = tagDatabase;
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

    public AdDatabase(String time, String text, String price){
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



    public UserProfileDatabase getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileDatabase userProfile) {
        this.userProfile = userProfile;
    }

    public UserDatabase getUser() {
        return user;
    }

    public void setUser(UserDatabase user) {
        this.user = user;
    }

    public TagDatabase getTag() {
        return tag;
    }

    public void setTag(TagDatabase tag) {
        this.tag = tag;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String id) {
        this.serverId = id;
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

    @Override
    public String toString() {
        return "Ad{" +
                "user='" + user + '\'' +
                ", id=" + id +
                '}';
    }
}