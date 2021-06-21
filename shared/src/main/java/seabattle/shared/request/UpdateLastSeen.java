package seabattle.shared.request;

import seabattle.shared.response.Response;

public class UpdateLastSeen implements Request {

    String username;

    public UpdateLastSeen(String username) {
        this.username = username;
    }

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.updateLastSeen(this);
    }

    public String getUsername() {
        return username;
    }
}
