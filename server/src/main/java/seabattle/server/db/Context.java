package seabattle.server.db;

import seabattle.server.model.Session;
import seabattle.server.model.User;

public class Context {
    public DBSet<User> Users = new UserDB();
    public DBSet<Session> Sessions = new SessionDB();
}
