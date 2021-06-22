package seabattle.server.controller.game;

import seabattle.server.controller.ClientThread;
import seabattle.server.controller.game.battleship.BattleShipGame;
import seabattle.server.model.Side;

import java.util.LinkedList;

public class GameLobby {
    private ClientThread waiting;
    private final LinkedList<Game> allGames = new LinkedList<>();

    public synchronized void startNewGame(ClientThread clientThread) {
        if (waiting == null) {
            waiting = clientThread;
            clientThread.setSide(Side.PLAYER_ONE);
        } else {
            if (waiting != clientThread) {
                Game game = new BattleShipGame(
                        10,
                        10,
                        waiting.getUserData().getUsername(),
                        clientThread.getUserData().getUsername()
                );
                allGames.add(game);
//                System.out.println(waiting.getUserData().getUsername() + " ss " + clientThread.getUserData().getUsername() + " GAMELOBBY");
                clientThread.setSide(Side.PLAYER_TWO);
                waiting.setGame(game);
                clientThread.setGame(game);
                waiting = null;
            }
        }
    }

    public synchronized void removeGame(Game game) {
        allGames.remove(game);
    }

    public synchronized String getSpectateList() {
        String info = "game here";
        return info;
    }
}
