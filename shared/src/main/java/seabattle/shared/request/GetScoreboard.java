package seabattle.shared.request;

import seabattle.shared.response.Response;

public class GetScoreboard implements Request {

    boolean open;

    public GetScoreboard(boolean open) {
        this.open = open;
    }

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.getScoreboard(this);
    }

    public boolean isOpen() {
        return open;
    }
}
