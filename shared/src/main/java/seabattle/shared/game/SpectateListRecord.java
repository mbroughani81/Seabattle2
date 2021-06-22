package seabattle.shared.game;

public class SpectateListRecord {
    String username1;
    String username2;
    int moveNumber;
    int remainingBoatNumber1;
    int remainingBoatNumber2;
    int hitNumber1;
    int hitNumber2;

    public SpectateListRecord(String username1, String username2, int moveNumber, int remainingBoatNumber1, int remainingBoatNumber2, int hitNumber1, int hitNumber2) {
        this.username1 = username1;
        this.username2 = username2;
        this.moveNumber = moveNumber;
        this.remainingBoatNumber1 = remainingBoatNumber1;
        this.remainingBoatNumber2 = remainingBoatNumber2;
        this.hitNumber1 = hitNumber1;
        this.hitNumber2 = hitNumber2;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    public int getRemainingBoatNumber1() {
        return remainingBoatNumber1;
    }

    public void setRemainingBoatNumber1(int remainingBoatNumber1) {
        this.remainingBoatNumber1 = remainingBoatNumber1;
    }

    public int getRemainingBoatNumber2() {
        return remainingBoatNumber2;
    }

    public void setRemainingBoatNumber2(int remainingBoatNumber2) {
        this.remainingBoatNumber2 = remainingBoatNumber2;
    }

    public int getHitNumber1() {
        return hitNumber1;
    }

    public void setHitNumber1(int hitNumber1) {
        this.hitNumber1 = hitNumber1;
    }

    public int getHitNumber2() {
        return hitNumber2;
    }

    public void setHitNumber2(int hitNumber2) {
        this.hitNumber2 = hitNumber2;
    }
}
