package seabattle.server.controller.game;

import seabattle.server.controller.ClientThread;
import seabattle.server.controller.game.battleship.BattleShipGame;
import seabattle.server.model.Side;
import seabattle.shared.game.Board;
import seabattle.shared.game.SpectateListRecord;

import java.util.LinkedList;

public class GameLobby {
    private ClientThread waiting;
    private final LinkedList<Game> allGames = new LinkedList<>();
    private final LinkedList<Game> allCreatedGames = new LinkedList<>();

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
                allCreatedGames.add(game);
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

    public synchronized SpectateListRecord[] getSpectateList() {
        SpectateListRecord[] records = new SpectateListRecord[allGames.size()];
        for (int i = 0; i < allGames.size(); i++) {
            BattleShipGame game = (BattleShipGame) allGames.get(i);
            records[i] = new SpectateListRecord(
                    game.getGameId(),
                    game.getUsername1(),
                    game.getUsername2(),
                    game.getMoveNumber(),
                    game.getPlayerRemainingBoatNumber(1),
                    game.getPlayerRemainingBoatNumber(2),
                    game.getPlayerHitNumber(1),
                    game.getPlayerHitNumber(2)
            );
        }
        return records;
    }

    public Board getBoard(int gameId, int id) {
        for (Game game : allCreatedGames) {
            if (game.getGameId() == gameId)
                return game.getBoard(id, Side.SPECTATOR);
        }
        return null;
    }
}
