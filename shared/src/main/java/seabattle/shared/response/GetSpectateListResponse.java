package seabattle.shared.response;

import seabattle.shared.game.SpectateListRecord;

public class GetSpectateListResponse implements Response {

    SpectateListRecord[] records;
    boolean isOpen;

    public GetSpectateListResponse(SpectateListRecord[] records, boolean isOpen) {
        this.records = records;
        this.isOpen = isOpen;
    }

    @Override
    public void handle(ResponseHandler responseHandler) {
        responseHandler.checkGetSpectateListResponse(this);
    }

    public SpectateListRecord[] getRecords() {
        return records;
    }

    public void setRecords(SpectateListRecord[] records) {
        this.records = records;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
