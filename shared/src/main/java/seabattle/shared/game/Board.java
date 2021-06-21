package seabattle.shared.game;

import java.awt.*;

public class Board {

    BoardCell[][] boardCells;
    int width;
    int height;
    boolean isTurnBoard;
    String playerUsername;

    public Board(int width, int height, String playerUsername, boolean isTurnBoard) {
        this.width = width;
        this.height = height;
        this.playerUsername = playerUsername;
        this.isTurnBoard = isTurnBoard;
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
}
