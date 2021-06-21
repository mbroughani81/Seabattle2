package seabattle.shared.request;

import seabattle.shared.response.Response;

public class GetBoard implements Request {

    int id;

    public GetBoard(int id) {
        this.id = id;
    }

    @Override
    public Response handle(RequestHandler requestHandler) {
        return requestHandler.getBoard(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
