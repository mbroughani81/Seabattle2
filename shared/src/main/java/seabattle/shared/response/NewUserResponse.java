package seabattle.shared.response;

public class NewUserResponse implements Response {
    int verdict;

    public NewUserResponse(int verdict) {
        this.verdict = verdict;
    }

    @Override
    public void handle(ResponseHandler responseHandler) {
        responseHandler.checkNewUserResponse(this);
    }

    public int getVerdict() {
        return verdict;
    }

    public void setVerdict(int verdict) {
        this.verdict = verdict;
    }
}
