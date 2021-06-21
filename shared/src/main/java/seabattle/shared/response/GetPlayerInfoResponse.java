package seabattle.shared.response;

public class GetPlayerInfoResponse implements Response {

    String info;

    public GetPlayerInfoResponse(String info) {
        this.info = info;
    }

    @Override
    public void handle(ResponseHandler responseHandler) {
        responseHandler.checkGetPlayerInfoResponse(this);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
