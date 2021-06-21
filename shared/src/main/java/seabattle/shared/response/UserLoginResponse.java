package seabattle.shared.response;

public class UserLoginResponse implements Response {

    String username;

    public UserLoginResponse(String username) {
        this.username = username;
    }

    @Override
    public void handle(ResponseHandler responseHandler) {
        responseHandler.checkUserLoginResponse(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
