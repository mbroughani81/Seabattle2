package seabattle.shared.response;

public class NewMessage implements Response {

    String text;

    public NewMessage(String text) {
        this.text = text;
    }

    @Override
    public void handle(ResponseHandler responseHandler) {
        responseHandler.showMessage(text);
    }
}
