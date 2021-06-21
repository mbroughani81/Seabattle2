package seabattle.shared.response;

import seabattle.shared.game.ScoreboardRecord;

public class GetScoreboardResponse implements Response {

    ScoreboardRecord[] records;
    boolean isOpen;


    public GetScoreboardResponse(ScoreboardRecord[] records, boolean isOpen) {
        this.records = records;
        this.isOpen = isOpen;
    }

    @Override
    public void handle(ResponseHandler responseHandler) {
        responseHandler.checkGetScoreboardResponse(this);
    }

    public ScoreboardRecord[] getRecords() {
        return records;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
