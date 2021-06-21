package seabattle.server.controller.game.battleship;

import seabattle.server.controller.game.Game;
import seabattle.server.model.Side;
import seabattle.shared.datatype.Pair;
import seabattle.shared.game.Board;
import seabattle.shared.game.BoardCell;

import java.awt.*;

public class BattleShipGame implements Game {
    private GameState gameState;
    Side sideToTurn;
    int width;
    int height;
    String username1;
    String username2;

//    int cnt = 0;

    public BattleShipGame(int width, int height, String username1, String username2) {
        this.gameState = new GameState(width, height);
        this.sideToTurn = Side.PLAYER_ONE;
        this.width = width;
        this.height = height;
        this.username1 = username1;
        this.username2 = username2;
    }

    @Override
    public void click(int row, int col, int id, Side side) {
        if (row < 0 || row >= 10 || col < 0 || col >= 10)
            return;
        if (side.getIndex() == id || side != sideToTurn)
            return;
        Cell[][] playerCells = gameState.getPlayerCells(id);
        if (playerCells[row][col] == Cell.FULL)
            return;
//        cnt++;
        playerCells[row][col] = Cell.FULL;
        if (gameState.isBoat(row, col, id)) {
            checkBoats(id);
        } else {
            setSideToTurn(sideToTurn.getOther());
        }
    }

    private void forceClick(int row, int col, int id) {
        if (row < 0 || row >= 10 || col < 0 || col >= 10)
            return;
        Cell[][] playerCells = gameState.getPlayerCells(id);
        playerCells[row][col] = Cell.FULL;
    }

    @Override
    public Board getBoard(int id, Side reciever) {
        Board board = new Board(
                width,
                height,
                (id == 1) ? getUsername1() : getUsername2(),
                id == sideToTurn.getIndex()
        );
        Cell[][] playerCells = gameState.getPlayerCells(id);
        for (int i = 0; i < height; i++) {
            for (int t = 0; t < width; t++) {
                BoardCell boardCell = board.getBoardCells()[i][t];
                if (playerCells[i][t] == Cell.FULL)
                    boardCell.setColor(Color.BLACK);
            }
        }
        if (reciever.getIndex() == id) {
            Pair[][] ships = gameState.getPlayerBattleships(id);
            for (int i = 0; i < ships.length; i++) {
                for (int t = 0; t < ships[i].length; t++) {
                    BoardCell boardCell = board.getBoardCells()[(int) ships[i][t].getFirst()][(int) ships[i][t].getSecond()];
                    if (playerCells[(int)ships[i][t].getFirst()][(int)ships[i][t].getSecond()] == Cell.EMPTY)
                        boardCell.setColor(Color.MAGENTA);
                }
            }
            ships = gameState.getPlayerCruisers(id);
            for (int i = 0; i < ships.length; i++) {
                for (int t = 0; t < ships[i].length; t++) {
                    BoardCell boardCell = board.getBoardCells()[(int) ships[i][t].getFirst()][(int) ships[i][t].getSecond()];
                    if (playerCells[(int)ships[i][t].getFirst()][(int)ships[i][t].getSecond()] == Cell.EMPTY)
                        boardCell.setColor(Color.GRAY);
                }
            }
            ships = gameState.getPlayerDestroyers(id);
            for (int i = 0; i < ships.length; i++) {
                for (int t = 0; t < ships[i].length; t++) {
                    BoardCell boardCell = board.getBoardCells()[(int) ships[i][t].getFirst()][(int) ships[i][t].getSecond()];
                    if (playerCells[(int)ships[i][t].getFirst()][(int)ships[i][t].getSecond()] == Cell.EMPTY)
                        boardCell.setColor(Color.CYAN);
                }
            }
            ships = gameState.getPlayerFrigates(id);
            for (int i = 0; i < ships.length; i++) {
                for (int t = 0; t < ships[i].length; t++) {
                    BoardCell boardCell = board.getBoardCells()[(int) ships[i][t].getFirst()][(int) ships[i][t].getSecond()];
                    if (playerCells[(int)ships[i][t].getFirst()][(int)ships[i][t].getSecond()] == Cell.EMPTY)
                        boardCell.setColor(Color.GREEN);
                }
            }

        }
        return board;

//        Pair[][] player1Cruisers;
//        Pair[][] player1Destroyers;
//        Pair[][] player1Frigate;
    }

    @Override
    public int isEnded() {
//        if (cnt > 5)
//            return true;
        boolean isOneWinner = true;
        boolean isTwoWinner = true;
        for (int i = 0; i < 1; i++) {
            if (!gameState.isBattleshipDestroyed(2, i))
                isOneWinner = false;
            if (!gameState.isBattleshipDestroyed(1, i))
                isTwoWinner = false;
        }
        for (int i = 0; i < 2; i++) {
            if (!gameState.isCruiserDestroyed(2, i))
                isOneWinner = false;
            if (!gameState.isCruiserDestroyed(1, i))
                isTwoWinner = false;
        }
        for (int i = 0; i < 3; i++) {
            if (!gameState.isDestroyerDestroyed(2, i))
                isOneWinner = false;
            if (!gameState.isDestroyerDestroyed(1, i))
                isTwoWinner = false;
        }
        for (int i = 0; i < 4; i++) {
            if (!gameState.isFrigateDestroyed(2, i))
                isOneWinner = false;
            if (!gameState.isFrigateDestroyed(1, i))
                isTwoWinner = false;
        }
        if (isOneWinner)
            return 1;
        if (isTwoWinner)
            return 2;
        return 0;
    }

    public void setSideToTurn(Side sideToTurn) {
        this.sideToTurn = sideToTurn;
    }

    private void checkBoats(int id) {
        for (int i = 0; i < 1; i++) {
            if (gameState.isBattleshipDestroyed(id, i)) {
                for (Pair pair : gameState.getPlayerBattleships(id)[i]) {
                    forceClick((int)pair.getFirst(), (int)pair.getSecond()+1, id);
                    forceClick((int)pair.getFirst(), (int)pair.getSecond()-1, id);
                    forceClick((int)pair.getFirst()+1, (int)pair.getSecond(), id);
                    forceClick((int)pair.getFirst()+1, (int)pair.getSecond()+1, id);
                    forceClick((int)pair.getFirst()+1, (int)pair.getSecond()-1, id);
                    forceClick((int)pair.getFirst()-1, (int)pair.getSecond(), id);
                    forceClick((int)pair.getFirst()-1, (int)pair.getSecond()+1, id);
                    forceClick((int)pair.getFirst()-1, (int)pair.getSecond()-1, id);
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            if (gameState.isCruiserDestroyed(id, i)) {
                for (Pair pair : gameState.getPlayerCruisers(id)[i]) {
                    forceClick((int)pair.getFirst(), (int)pair.getSecond()+1, id);
                    forceClick((int)pair.getFirst(), (int)pair.getSecond()-1, id);
                    forceClick((int)pair.getFirst()+1, (int)pair.getSecond(), id);
                    forceClick((int)pair.getFirst()+1, (int)pair.getSecond()+1, id);
                    forceClick((int)pair.getFirst()+1, (int)pair.getSecond()-1, id);
                    forceClick((int)pair.getFirst()-1, (int)pair.getSecond(), id);
                    forceClick((int)pair.getFirst()-1, (int)pair.getSecond()+1, id);
                    forceClick((int)pair.getFirst()-1, (int)pair.getSecond()-1, id);
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameState.isDestroyerDestroyed(id, i)) {
                for (Pair pair : gameState.getPlayerDestroyers(id)[i]) {
                    forceClick((int)pair.getFirst(), (int)pair.getSecond()+1, id);
                    forceClick((int)pair.getFirst(), (int)pair.getSecond()-1, id);
                    forceClick((int)pair.getFirst()+1, (int)pair.getSecond(), id);
                    forceClick((int)pair.getFirst()+1, (int)pair.getSecond()+1, id);
                    forceClick((int)pair.getFirst()+1, (int)pair.getSecond()-1, id);
                    forceClick((int)pair.getFirst()-1, (int)pair.getSecond(), id);
                    forceClick((int)pair.getFirst()-1, (int)pair.getSecond()+1, id);
                    forceClick((int)pair.getFirst()-1, (int)pair.getSecond()-1, id);
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            if (gameState.isFrigateDestroyed(id, i)) {
                for (Pair pair : gameState.getPlayerFrigates(id)[i]) {
                    forceClick((int)pair.getFirst(), (int)pair.getSecond()+1, id);
                    forceClick((int)pair.getFirst(), (int)pair.getSecond()-1, id);
                    forceClick((int)pair.getFirst()+1, (int)pair.getSecond(), id);
                    forceClick((int)pair.getFirst()+1, (int)pair.getSecond()+1, id);
                    forceClick((int)pair.getFirst()+1, (int)pair.getSecond()-1, id);
                    forceClick((int)pair.getFirst()-1, (int)pair.getSecond(), id);
                    forceClick((int)pair.getFirst()-1, (int)pair.getSecond()+1, id);
                    forceClick((int)pair.getFirst()-1, (int)pair.getSecond()-1, id);
                }
            }
        }
    }

    public String getUsername1() {
        return username1;
    }

    public String getUsername2() {
        return username2;
    }
}