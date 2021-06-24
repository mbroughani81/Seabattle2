package seabattle.shared.response;

import seabattle.shared.game.Board;

public class UpdateSpectateBoard implements Response {

    int id;
    Board board;

    public UpdateSpectateBoard(int id, Board board) {
        this.id = id;
        this.board = board;
    }

    @Override
    public void handle(ResponseHandler responseHandler) {
        responseHandler.updateSpectateBoard(board, id);
    }
}
