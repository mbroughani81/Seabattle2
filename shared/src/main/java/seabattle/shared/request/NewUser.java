package seabattle.shared.request;

import seabattle.shared.response.Response;

import java.time.LocalDateTime;

public class NewUser implements Request {

    String username;
    String password;
    LocalDateTime registerTime;

    public NewUser(String username, String password, LocalDateTime registerTime) {
        this.username = username;
        this.password = password;
        this.registerTime = registerTime;
    }

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.addNewUser(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }
}
