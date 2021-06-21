package seabattle.shared.response;

import seabattle.shared.game.Board;

public class UpdateBoard implements Response {

    int id;
    Board board;

    public UpdateBoard(int id, Board board) {
        this.id = id;
        this.board = board;
    }

    @Override
    public void handle(ResponseHandler responseHandler) {
        responseHandler.updateBoard(board, id);
    }
}
