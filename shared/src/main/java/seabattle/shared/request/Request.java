package seabattle.shared.request;

import seabattle.shared.authtoken.AuthToken;
import seabattle.shared.response.Response;

public interface Request {
    public abstract Response handle(RequestHandler requestHandler);
    public abstract void setAuthToken(AuthToken authToken);
    public abstract AuthToken getAuthToken();
}
