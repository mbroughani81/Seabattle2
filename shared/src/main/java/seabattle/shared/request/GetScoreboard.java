package seabattle.shared.request;

import seabattle.shared.authtoken.AuthToken;
import seabattle.shared.response.Response;

public class GetScoreboard implements Request {

    boolean open;
    AuthToken authToken;

    public GetScoreboard(boolean open) {
        this.open = open;
    }

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.getScoreboard(this);
    }

    @Override
    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    @Override
    public AuthToken getAuthToken() {
        return authToken;
    }

    public boolean isOpen() {
        return open;
    }
}
