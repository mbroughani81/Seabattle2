package seabattle.client.listener;

import seabattle.client.GraphicalAgent;
import seabattle.client.listener.network.SocketRequestSender;
import seabattle.shared.authtoken.AuthToken;
import seabattle.shared.game.Board;
import seabattle.shared.loop.Loop;
import seabattle.shared.request.Request;
import seabattle.shared.response.*;

import java.util.LinkedList;
import java.util.List;

public class MainResponseHandler implements ResponseHandler {
    private final SocketRequestSender sender;
    private final List<Request> requests;
    private final Loop loop;
    private final GraphicalAgent graphicalAgent;
    private UserData userData;
    private AuthToken authToken;

    public MainResponseHandler(SocketRequestSender sender) {
        this.sender = sender;
        this.requests = new LinkedList<>();
        this.loop = new Loop(10, this::sendRequests);
        this.graphicalAgent = new GraphicalAgent(this::addRequest);
        this.authToken = null;
    }

    public void start() {
        loop.start();
        graphicalAgent.initialize();
    }

    private void sendRequests() {
        List<Request> temp;
        synchronized (requests) {
            temp = new LinkedList<>(requests);
            requests.clear();
        }
        for (Request request : temp) {
            request.setAuthToken(authToken);
            Response response = sender.send(request);
            if (response == null) {
                System.out.println("shit is null! MainResponseHandler");
            } else {

                response.handle(this);
            }
        }
    }

    private void addRequest(Request request) {
        synchronized (requests) {
            requests.add(request);
        }
    }

    @Override
    public void showMessage(String s) {
        System.out.println("Message has came : " + s);
    }

    @Override
    public void updateBoard(Board board, int id) {
        graphicalAgent.updateBoard(board, id);
    }

    @Override
    public void checkNewUserResponse(NewUserResponse newUserResponse) {
        graphicalAgent.newUserRegistered(newUserResponse.getVerdict());
    }

    @Override
    public void checkUserLoginResponse(UserLoginResponse userLoginResponse) {
        if (userLoginResponse.getVerdict() == 0) {
            authToken = userLoginResponse.getAuthToken();
        }
        userData = new UserData(userLoginResponse.getUsername());
        graphicalAgent.userLoggedIn(userData, userLoginResponse.getVerdict());
    }

    @Override
    public void checkGetPlayerInfoResponse(GetPlayerInfoResponse getPlayerInfoResponse) {
//        System.out.println("info : " + getPlayerInfoResponse.getInfo());
        graphicalAgent.showPlayerInfo(getPlayerInfoResponse.getInfo());
    }

    @Override
    public void checkEndGameResponse(EndGame endGame) {
//        System.out.println("I should quit the game mainresponsehandler");
        graphicalAgent.gameEnded();
    }

    @Override
    public void checkGetScoreboardResponse(GetScoreboardResponse getScoreboardResponse) {
//        System.out.println("info is : " + getScoreboardResponse.getInfo());
        if (getScoreboardResponse.isOpen()) {
            graphicalAgent.showScoreboard();
        } else {
            graphicalAgent.updateScoreboard(getScoreboardResponse.getRecords());
        }
    }

    @Override
    public void checkGetSpectateListResponse(GetSpectateListResponse getSpectateListResponse) {
        System.out.println("I got : " + getSpectateListResponse.isOpen() + " main resonsehandler");
        if (getSpectateListResponse.isOpen()) {
            graphicalAgent.showSpectateList(getSpectateListResponse.getRecords().length);
        } else {
            graphicalAgent.updateSpectateList(getSpectateListResponse.getRecords());
        }
    }

    @Override
    public void checkGetSpectateGameResponse(GetSpectateGameResponse getSpectateGameResponse) {
        graphicalAgent.showSpectateGame();
    }
}
