package seabattle.shared.request;

import seabattle.shared.authtoken.AuthToken;
import seabattle.shared.response.Response;

public class GetPlayerInfo implements Request {

    String username;
    AuthToken authToken;

    public GetPlayerInfo(String username) {
        this.username = username;
    }

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.getPlayerInfoChecker(this);
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
}
