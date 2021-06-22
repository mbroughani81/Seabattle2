package seabattle.shared.request;

import seabattle.shared.authtoken.AuthToken;
import seabattle.shared.response.Response;

public class GetSpectateList implements Request {

    boolean isOpen;
    AuthToken authToken;

    public GetSpectateList(boolean isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.getSpectateList(this);
    }

    @Override
    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    @Override
    public AuthToken getAuthToken() {
        return authToken;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
