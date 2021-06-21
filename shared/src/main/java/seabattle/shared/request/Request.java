package seabattle.shared.request;

import seabattle.shared.response.Response;

public interface Request {
    public abstract Response handle(RequestHandler requestHandler);
}
