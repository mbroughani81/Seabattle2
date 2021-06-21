package seabattle.shared.request;

import seabattle.shared.response.Response;

public class GetPlayerInfo implements Request {

    String username;

    public GetPlayerInfo(String username) {
        this.username = username;
    }

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.getPlayerInfoChecker(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
