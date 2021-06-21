package seabattle.shared.request;

import seabattle.shared.authtoken.AuthToken;
import seabattle.shared.response.Response;

public class GetBoard implements Request {

    int id;
    AuthToken authToken;

    public GetBoard(int id) {
        this.id = id;
    }

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.getBoard(this);
    }

    @Override
    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    @Override
    public AuthToken getAuthToken() {
        return authToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
