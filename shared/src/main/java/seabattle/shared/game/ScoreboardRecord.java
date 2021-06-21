package seabattle.shared.game;

public class ScoreboardRecord {
    String username;
    boolean isOnline;
    int score;

    public ScoreboardRecord(String username, boolean isOnline, int score) {
        this.username = username;
        this.isOnline = isOnline;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
