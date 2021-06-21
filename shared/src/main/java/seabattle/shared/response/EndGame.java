package seabattle.shared.response;

public class EndGame implements Response {
    @Override
    public void handle(ResponseHandler responseHandler) {
        responseHandler.checkEndGameResponse(this);
    }
}
