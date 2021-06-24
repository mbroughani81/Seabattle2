package seabattle.shared.request;

import seabattle.shared.authtoken.AuthToken;
import seabattle.shared.response.Response;

public class GetSpectateBoard implements Request {

    AuthToken authToken;
    int gameId;
    int id;

    public GetSpectateBoard(int gameId, int id) {
        this.gameId = gameId;
        this.id = id;
    }

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.getSpectateBoard(this);
    }

    @Override
    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    @Override
    public AuthToken getAuthToken() {
        return authToken;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
