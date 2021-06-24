package seabattle.server;

import seabattle.server.controller.SocketManager;
import seabattle.server.controller.dbcontroller.SessionController;
import seabattle.server.controller.dbcontroller.UserController;
import seabattle.shared.loop.Timer;
import seabattle.shared.request.NewUser;

import java.io.IOException;
import java.time.LocalDateTime;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        testData();

        SocketManager socketManager = new SocketManager();
        socketManager.start();
    }

    static private void testData() {
        UserController userController = new UserController();
        SessionController sessionController = new SessionController();
//        userController.clearUserDB();
        sessionController.clearSessionDB();
//        userController.addUser(new NewUser(
//                "mb",
//                "1381",
//                LocalDateTime.now()
//        ));
//        userController.addUser(new NewUser(
//                "1",
//                "",
//                LocalDateTime.now()
//        ));
//        userController.addUser(new NewUser(
//                "2",
//                "",
//                LocalDateTime.now()
//        ));
    }
}
