package seabattle.shared.response;

public class GetSpectateGameResponse implements Response {
    @Override
    public void handle(ResponseHandler responseHandler) {
        responseHandler.checkGetSpectateGameResponse(this);
    }
}
