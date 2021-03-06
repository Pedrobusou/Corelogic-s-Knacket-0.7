package uk.co.ribot.Knacket.data.model;

import java.util.ArrayList;

public class Seller {
    private String name, description, price;
    private int id, rating;

    public Seller(){}

    public Seller(Seller seller){
        this.name = seller.getName();
        this.description = seller.getDescription();
        this.price = seller.getPrice();
        this.rating = seller.getRating();
    }

    public ArrayList<Seller> add6Seller(){
        ArrayList<Seller> sellers = new ArrayList<>();
        sellers.add(new Seller("Andreas", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr/h", 3));
        sellers.add(new Seller("Pedro",   "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "3000kr/h", 10));
        sellers.add(new Seller("Marcos",  "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr/h", 2));
        sellers.add(new Seller("Andreas", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr/h", 3));
        sellers.add(new Seller("Pedro",   "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "3000kr/h", 10));
        sellers.add(new Seller("Marcos",  "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr/h", 2));
        return sellers;
    }

    public Seller(String name, String description, String price, int rating){
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }

    public Seller(String name, String description, String price, int rating, int id){
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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