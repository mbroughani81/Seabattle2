package seabattle.server.controller;

import seabattle.server.controller.dbcontroller.SessionController;
import seabattle.server.controller.dbcontroller.UserController;
import seabattle.server.controller.game.Game;
import seabattle.server.controller.game.GameLobby;
import seabattle.server.model.Side;
import seabattle.server.model.User;
import seabattle.shared.authtoken.AuthToken;
import seabattle.shared.request.*;
import seabattle.shared.response.*;

import java.util.Random;

public class ClientThread extends Thread implements RequestHandler {

    private final ResponseSender sender;
    private final GameLobby gameLobby;
    private Game game;
    private Side side;
    private volatile boolean running;
    private UserController userController = new UserController();
    private SessionController sessionController = new SessionController();
    private UserData userData;
    private AuthToken authToken;

    public ClientThread(ResponseSender sender, GameLobby gameLobby) {
        this.sender = sender;
        this.gameLobby = gameLobby;
    }

    @Override
    public synchronized void start() {
        running = true;
        super.start();
    }

    @Override
    public void run() {
        while (running) {
            Request request = sender.getRequest();
            if (authToken != null) {
                if (request.getAuthToken().getVal() != authToken.getVal()) {
                    sender.sendResponse(new NullResponse());
                    continue;
                }
            }
            sender.sendResponse(request.handle(this));
        }
        sender.close();
    }

    @Override
    public Response startGame() {
//        System.out.println("ClientThread : I should start the game");
//        return new NewMessage();
        gameLobby.startNewGame(this);
        return new NewMessage("new game started");
    }

    @Override
    public Response getBoard(GetBoard getBoard) {
        if (game == null) {
            return new NewMessage("board is not ready");
        }
        if (game.isEnded() != 0) {
            if (game.isEnded() == side.getIndex()) {
                userController.addScore(userData.getUsername(), 1);
            } else {
                userController.addScore(userData.getUsername(), -1);
            }
            game = null;
            return new EndGame();
        }
        return new UpdateBoard(getBoard.getId(), game.getBoard(getBoard.getId(), side));
    }

    @Override
    public Response clickOnBoard(ClickOnBoard clickOnBoard) {
        game.click(clickOnBoard.getRow(), clickOnBoard.getCol(), clickOnBoard.getId(), side);
        return new UpdateBoard(clickOnBoard.getId(), game.getBoard(clickOnBoard.getId(), side));
    }

    @Override
    public Response addNewUser(NewUser newUser) {
        return new NewUserResponse(userController.addUser(newUser));
    }

    @Override
    public Response loginUser(UserLogin userLogin) {
        if (userController.getUserId(userLogin.getUsername()) == -1)
            return new UserLoginResponse(userLogin.getUsername(), -1, null);
        if (!userController.isPasswordOk(userLogin.getUsername(), userLogin.getPassword())) {
            return new UserLoginResponse(userLogin.getUsername(), -2, null);
        }
        userData = new UserData(userLogin.getUsername());
        sessionController.loginUser(userController.getUserId(userLogin.getUsername()));
        userController.updateLastOnline(userLogin.getUsername());
        Random rand = new Random();
        authToken = new AuthToken(rand.nextLong());
        return new UserLoginResponse(userLogin.getUsername(), 0, authToken);
    }

    @Override
    public Response getPlayerInfoChecker(GetPlayerInfo getPlayerInfo) {
        User user = userController.getUser(getPlayerInfo.getUsername());
        return new GetPlayerInfoResponse(user.getUsername() + "\nWin : " + user.getWin() +
                "\nLose : " + user.getLose() + "\nScore : " + user.getScore());
    }

    @Override
    public Response getScoreboard(GetScoreboard getScoreboard) {
        return new GetScoreboardResponse(userController.getScoreboard(), getScoreboard.isOpen());
    }

    @Override
    public Response updateLastSeen(UpdateLastSeen updateLastSeen) {
        userController.updateLastOnline(updateLastSeen.getUsername());
        return new NullResponse();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public UserData getUserData() {
        return userData;
    }
}
