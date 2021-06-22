package seabattle.shared.response;

public class GetSpectateListResponse implements Response {

    String info;
    boolean isOpen;

    public GetSpectateListResponse(String info, boolean isOpen) {
        this.info = info;
        this.isOpen = isOpen;
    }

    @Override
    public void handle(ResponseHandler responseHandler) {
        responseHandler.checkGetSpectateListResponse(this);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
