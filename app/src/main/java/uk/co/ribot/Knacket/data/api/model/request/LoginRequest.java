package uk.co.ribot.Knacket.data.api.model.request;

public class LoginRequest {
    public String email, password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}