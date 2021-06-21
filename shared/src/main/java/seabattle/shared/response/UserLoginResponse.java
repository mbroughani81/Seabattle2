package seabattle.shared.response;

import seabattle.shared.authtoken.AuthToken;

public class UserLoginResponse implements Response {

    String username;
    int verdict;
    AuthToken authToken;

    public UserLoginResponse(String username, int verdict, AuthToken authToken) {
        this.username = username;
        this.verdict = verdict;
        this.authToken = authToken;
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

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}
