package seabattle.client.listener;

import seabattle.shared.request.Request;
import seabattle.shared.response.Response;

public interface RequestSender {
    Response send(Request request);

    void close();
}
