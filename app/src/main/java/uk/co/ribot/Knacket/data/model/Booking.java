package uk.co.ribot.Knacket.data.model;

/**
 * Created by pedroramos on 10.05.16.
 */
import java.util.ArrayList;

public class Booking {
    String name, date, description;
    int id;

    public Booking(){}

    public Booking(Ad ad){
        this.name = ad.getName();
        this.date = ad.getDate();
        this.description = ad.getDescription();
    }

    public ArrayList<Booking> add6Bookings(){
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking("Booking 1", "3-mar 17:05", "Booking description"));
        bookings.add(new Booking("Booking 2", "3-mar 17:05", "Booking description"));
        bookings.add(new Booking("Booking 3", "3-mar 17:05", "Booking description"));
        bookings.add(new Booking("Booking 4", "3-mar 17:05", "Booking description"));
        bookings.add(new Booking("Booking 5", "3-mar 17:05", "Booking description"));
        bookings.add(new Booking("Booking 6", "3-mar 17:05", "Booking description"));
        return bookings;
    }

    public Booking(String name, String date, String description){
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public Booking(String name, String date, String description, int id){
        this.name = name;
        this.date = date;
        this.description = description;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
