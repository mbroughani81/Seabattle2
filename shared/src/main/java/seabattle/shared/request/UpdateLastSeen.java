package seabattle.shared.request;

import seabattle.shared.authtoken.AuthToken;
import seabattle.shared.response.Response;

public class UpdateLastSeen implements Request {

    String username;
    AuthToken authToken;

    public UpdateLastSeen(String username) {
        this.username = username;
    }

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.updateLastSeen(this);
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
}
