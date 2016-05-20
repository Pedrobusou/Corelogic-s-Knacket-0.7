package uk.co.ribot.Knacket.data.model;

import java.util.ArrayList;

public class Ad {
    String name, category, time, text, price, media_url, duration, location, location_latitude, location_longitude, deleted_at, created_at, updated_at, distance;
    int id, tag_id, user_id, rating, buyer_brings_gear;

    public Ad(){}

    public Ad(Ad ad){
        this.name = ad.getName();
        this.category = ad.getCategory();
        this.time = ad.getTime();
        this.text = ad.getText();
        this.price = ad.getPrice();
        this.rating = ad.getRating();
    }

    public ArrayList<Ad> add6Ads(){
        ArrayList<Ad> ads = new ArrayList<>();
        ads.add(new Ad("Andreas", "Hardcoded Ad", "3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr", 3));
        ads.add(new Ad("Pedro", "Hardcoded Ad", "3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "3000kr", 10));
        ads.add(new Ad("Marcos", "Hardcoded Ad", "3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr", 2));
        ads.add(new Ad("Andreas", "Hardcoded Ad", "3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr", 3));
        ads.add(new Ad("Pedro", "Hardcoded Ad", "3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "3000kr", 10));
        ads.add(new Ad("Marcos", "Hardcoded Ad", "3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr", 2));
        return ads;
    }

    public Ad(String name, String category, String time, String text, String price, int rating){
        this.name = name;
        this.category = category;
        this.time = time;
        this.text = text;
        this.price = price;
        this.rating = rating;
    }

    public Ad(String name, String category, String time, String text, String price, int rating, int id){
        this.name = name;
        this.category = category;
        this.time = time;
        this.text = text;
        this.price = price;
        this.rating = rating;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}