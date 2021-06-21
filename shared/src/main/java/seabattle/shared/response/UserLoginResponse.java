package seabattle.shared.response;

public class UserLoginResponse implements Response {

    String username;
    int verdict;

    public UserLoginResponse(String username, int verdict) {
        this.username = username;
        this.verdict = verdict;
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

    public int getVerdict() {
        return verdict;
    }

    public void setVerdict(int verdict) {
        this.verdict = verdict;
    }
}
