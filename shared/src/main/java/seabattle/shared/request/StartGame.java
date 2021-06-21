package seabattle.shared.request;

import seabattle.shared.response.Response;

public class StartGame implements Request {
    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.startGame();
    }
}
