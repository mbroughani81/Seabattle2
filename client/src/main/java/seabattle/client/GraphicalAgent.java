package seabattle.client;

import seabattle.client.graphic.authentication.AuthenticationWindow;
import seabattle.client.graphic.game.GameWindow;
import seabattle.client.graphic.mainmenu.MainMenuWindow;
import seabattle.client.listener.UserData;
import seabattle.shared.game.Board;
import seabattle.shared.game.ScoreboardRecord;
import seabattle.shared.request.RequestListener;

public class GraphicalAgent {

    private final RequestListener listener;
    private AuthenticationWindow authenticationWindow;
    private GameWindow gameWindow;
    private MainMenuWindow mainMenuWindow;
    private UserData userData;

    public GraphicalAgent(RequestListener listener) {
        this.listener = listener;
        this.authenticationWindow = new AuthenticationWindow(listener);
//        this.authenticationWindow.addStringListener(new StringListener() {
//            @Override
//            public void run(String s) {
//                if (s.equals("LoginFinished")) {
//                    GraphicalAgent.this.mainMenuWindow.initialize();
//                }
//            }
//        });
        this.mainMenuWindow = new MainMenuWindow(listener);
        this.mainMenuWindow.addStringListener(this::mainMenuAction);
//        this.mainMenuWindow.addStringListener(new StringListener() {
//            @Override
//            public void run(String s) {
//                if (s.equals("NewGameChosen")) {
//                    GraphicalAgent.this.gameWindow.initialize();
//                }
//            }
//        });
        this.gameWindow = new GameWindow(listener);
    }

    public void initialize() {
        authenticationWindow.initialize();
    }

    public void updateBoard(Board board, int id) {
        this.gameWindow.updateBoard(board, id);
    }

    public void newUserRegistered(int verdict) {
        if (verdict == -1) {
            System.out.println("Username already userd GraphicalAgent");
        } else {
            authenticationWindow.deactivate();
            authenticationWindow = new AuthenticationWindow(listener);
//        mainMenuWindow.initialize();
            authenticationWindow.initialize();
        }
    }

    public void userLoggedIn(UserData userData, int verdict) {
        if (verdict == -1) {
            System.out.println("Wrong username GraphicalAgent");
        } else
        if (verdict == -2) {
            System.out.println("Wrong password GraphicalAgent");
        } else {
            this.userData = userData;
            mainMenuWindow.setUserData(userData);
            authenticationWindow.deactivate();
            authenticationWindow = new AuthenticationWindow(listener);
            mainMenuWindow.initialize();
        }
    }

    public void showPlayerInfo(String info) {
        mainMenuWindow.showPlayerInfo(info);
    }

    public void gameEnded() {
        if (gameWindow.isActive()) {
            gameWindow.deactivate();
            gameWindow = new GameWindow(listener);
            mainMenuWindow.initialize();
        }
    }

    public void showScoreboard() {
        mainMenuWindow.showScoreboard();
    }

    public void updateScoreboard(ScoreboardRecord[] scoreboardRecords) {
        mainMenuWindow.updateScoreboard(scoreboardRecords);
    }

    private void mainMenuAction(String s) {
        if (s.equals("NewGameChosen")) {
            mainMenuWindow.deactivate();
            mainMenuWindow = new MainMenuWindow(listener);
            mainMenuWindow.setUserData(userData);
            mainMenuWindow.addStringListener(this::mainMenuAction);
            gameWindow.initialize();
        }
    }

}
