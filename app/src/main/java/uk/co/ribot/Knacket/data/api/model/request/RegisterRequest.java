package uk.co.ribot.Knacket.data.api.model.request;

public class RegisterRequest { //MAYBE ADD TOKEN
    String name;
    String email;
    String pass;

    public RegisterRequest(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }
}