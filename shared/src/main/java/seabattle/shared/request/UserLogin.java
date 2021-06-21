package seabattle.shared.request;

import seabattle.shared.authtoken.AuthToken;
import seabattle.shared.response.Response;

public class UserLogin implements Request {
    String username;
    String password;
    AuthToken authToken;

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.loginUser(this);
    }

    @Override
    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    @Override
    public AuthToken getAuthToken() {
        return authToken;
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
}
