package seabattle.shared.request;

import seabattle.shared.response.Response;

public class UserLogin implements Request {
    String username;

    public UserLogin(String username) {
        this.username = username;
    }

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.loginUser(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
