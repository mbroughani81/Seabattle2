package seabattle.server.controller.game;

import seabattle.server.controller.ClientThread;
import seabattle.server.controller.game.battleship.BattleShipGame;
import seabattle.server.model.Side;

public class GameLobby {
    private ClientThread waiting;

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
//                System.out.println(waiting.getUserData().getUsername() + " ss " + clientThread.getUserData().getUsername() + " GAMELOBBY");
                clientThread.setSide(Side.PLAYER_TWO);
                waiting.setGame(game);
                clientThread.setGame(game);
                waiting = null;
            }
        }
    }
}
