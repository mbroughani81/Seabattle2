package seabattle.server.model;

public class Session {
    private int id;
    private int userId;

    public Session(int userId) {
        this.id = -1;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

