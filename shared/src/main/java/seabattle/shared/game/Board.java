package seabattle.shared.game;

import java.awt.*;

public class Board {

    BoardCell[][] boardCells;
    int width;
    int height;
    boolean isTurnBoard;
    String playerUsername;
    int timer;

    public Board(int width, int height, String playerUsername, boolean isTurnBoard) {
        this.width = width;
        this.height = height;
        this.playerUsername = playerUsername;
        this.isTurnBoard = isTurnBoard;
        this.timer = -1;
        boardCells = new BoardCell[height][width];
        for (int i = 0; i < height; i++) {
            for (int t = 0; t < width; t++) {
                if ((i + t) % 2 == 0)
                    boardCells[i][t] = new BoardCell(Color.ORANGE);
                else
                    boardCells[i][t] = new BoardCell(Color.BLUE);
            }
        }
    }

    public Board(int width, int height, String playerUsername, boolean isTurnBoard, int timer) {
        this(width, height, playerUsername, isTurnBoard);
        this.timer = timer;
    }


    public BoardCell[][] getBoardCells() {
        return boardCells;
    }

    public void setBoardCells(BoardCell[][] boardCells) {
        this.boardCells = boardCells;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isTurnBoard() {
        return isTurnBoard;
    }

    public void setTurnBoard(boolean turnBoard) {
        isTurnBoard = turnBoard;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        if (boardCells == null)
            return "null";
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int t = 0; t < width; t++) {
                res.append(boardCells[i][t].getColor()).append(" ");
            }
            res.append("\n");
        }
        return res.toString();
    }
}
