package seabattle.server.controller.game;

import seabattle.server.model.Side;
import seabattle.shared.game.Board;

public interface Game {

    void click(int x, int y, int id, Side side);
    Board getBoard(int id, Side reciever);
    int isEnded();
    int getGameId();
    void newBoard(Side side);
}
