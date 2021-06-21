package seabattle.server.controller;

import seabattle.shared.request.Request;
import seabattle.shared.response.Response;

public interface ResponseSender {
    Request getRequest();
    void sendResponse(Response response);
    void close();
}
