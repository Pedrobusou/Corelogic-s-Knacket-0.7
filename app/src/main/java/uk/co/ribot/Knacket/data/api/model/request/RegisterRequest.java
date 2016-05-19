package uk.co.ribot.Knacket.data.api.model.request;

public class RegisterRequest {
    public String name, email, password, password_confirmation;

    public RegisterRequest(String name, String email, String password, String password_confirmation) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }
}