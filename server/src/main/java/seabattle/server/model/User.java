package seabattle.server.model;

import java.time.LocalDateTime;

public class User {

    private int id;
    private String username;
    private String password;
    private LocalDateTime lastOnline;
    private int win;
    private int lose;
    private int score;

    public User(String username, String password, LocalDateTime lastOnline) {
        this.id = -1;
        this.username = username;
        this.password = password;
        this.lastOnline = lastOnline;
        this.win = 0;
        this.lose = 0;
        this.score = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(LocalDateTime lastOnline) {
        this.lastOnline = lastOnline;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
