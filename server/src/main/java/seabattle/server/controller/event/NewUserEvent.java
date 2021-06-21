package seabattle.server.controller.event;

import java.time.LocalDateTime;

public class NewUserEvent {
    private String username;
    private String password;
    private LocalDateTime registerTime;

    public NewUserEvent(String username, String password, LocalDateTime registerTime) {
        this.username = username;
        this.password = password;
        this.registerTime = registerTime;
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

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }
}
