package seabattle.shared.request;

import seabattle.shared.authtoken.AuthToken;
import seabattle.shared.response.Response;

public class GetSpectateGame implements Request {

    AuthToken authToken;

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.getSpectateGame(this);
    }

    @Override
    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    @Override
    public AuthToken getAuthToken() {
        return authToken;
    }

}
