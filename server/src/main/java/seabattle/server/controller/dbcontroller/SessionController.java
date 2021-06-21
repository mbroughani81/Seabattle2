package seabattle.server.controller.dbcontroller;

import seabattle.server.model.Session;

public class SessionController extends AbstractController{
    public void loginUser(int userId) {
        System.out.println(userId + "is userid Sessioncontroller");
        if (userId == -1)
            return;
        Session session = new Session(userId);
        context.Sessions.add(session);
    }

    public void clearSessionDB() {
        context.Sessions.clear();
    }
}
