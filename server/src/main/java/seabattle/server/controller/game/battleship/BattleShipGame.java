package seabattle.server.controller.game.battleship;

import seabattle.server.controller.game.Game;
import seabattle.server.model.Side;
import seabattle.shared.datatype.Pair;
import seabattle.shared.game.Board;
import seabattle.shared.game.BoardCell;
import seabattle.shared.loop.Timer;

import java.awt.*;

public class BattleShipGame implements Game {

    static int MAX_GAME_ID = 0;
    int gameId;
    private GameState gameState;
    Timer player1placingTime;
    Timer player2placingTime;
    Side sideToTurn;
    int width;
    int height;
    String username1;
    String username2;
    int moveNumber;
    int player1HitNumber;
    int player2HitNumber;
    int player1NumberOfChanges;
    int player2NumberOfChanges;
    boolean isPlayer1Finalized;
    boolean isPlayer2Finalized; // can be false even after game started

//    int cnt = 0;

    public BattleShipGame(int width, int height, String username1, String username2) {
        this.gameId = MAX_GAME_ID;
        MAX_GAME_ID++;
        this.player1placingTime = new Timer(10000);
        this.player1placingTime.start();
        this.player2placingTime = new Timer(10000);
        this.player2placingTime.start();
        this.gameState = new GameState(width, height);
        this.sideToTurn = Side.PLAYER_ONE;
        this.width = width;
        this.height = height;
        this.username1 = username1;
        this.username2 = username2;
    }

    @Override
    public void click(int row, int col, int id, Side side) {
//        if (side == Side.PLAYER_ONE && !player1placingTime.isFinished())
//            return;
//        if (side == Side.PLAYER_TWO && !player2placingTime.isFinished())
//            return;
        if (!player1placingTime.isFinished() || !player2placingTime.isFinished())
            return;
        if (row < 0 || row >= 10 || col < 0 || col >= 10)
            return;
        if (side.getIndex() == id || side != sideToTurn)
            return;
        Cell[][] playerCells = gameState.getPlayerCells(id);
        if (playerCells[row][col] == Cell.FULL)
            return;
//        cnt++;
        playerCells[row][col] = Cell.FULL;
        moveNumber++;
        if (side == Side.PLAYER_ONE)
            player1HitNumber++;
        else
            player2HitNumber++;
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
    public void newBoard(Side side) {
        if (side.getIndex() == 1 && (isPlayer1Finalized || player1NumberOfChanges >= 3))
            return;
        if (side.getIndex() == 2 && (isPlayer2Finalized || player2NumberOfChanges >= 3))
            return;
        gameState.setNewSide(side);
        if (side.getIndex() == 1) {
            player1placingTime.setTime(player1placingTime.getTime() + 5000);
            player1NumberOfChanges++;
        } else {
            player2placingTime.setTime(player2placingTime.getTime() + 5000);
            player2NumberOfChanges++;
        }
    }

    @Override
    public void acceptBoard(Side side) {
        if (side.getIndex() == 1) {
            setPlayer1Finalized(true);
            player1placingTime.setTime(0);
        } else {
            setPlayer2Finalized(true);
            player2placingTime.setTime(0);
        }
    }

    @Override
    public Board getBoard(int id, Side reciever) {
        Board board = new Board(
                width,
                height,
                (id == 1) ? getUsername1() : getUsername2(),
                id == sideToTurn.getIndex()
        );
        if (id == reciever.getIndex()) {
            board.setTimer(
                    (id == 1) ? player1placingTime.getRemainingSec() : player2placingTime.getRemainingSec()
            );
            if (id == 1) {
                if (player1placingTime.isFinished() && !player2placingTime.isFinished()) {
                    board.setMessage("(Wait for other player)");
                }
            }
            if (id == 2) {
                if (player2placingTime.isFinished() && !player1placingTime.isFinished()) {
                    board.setMessage("(Wait for other player)");
                }
            }
        }
        // chosed ones, black
        Cell[][] playerCells = gameState.getPlayerCells(id);
        for (int i = 0; i < height; i++) {
            for (int t = 0; t < width; t++) {
                BoardCell boardCell = board.getBoardCells()[i][t];
                if (playerCells[i][t] == Cell.FULL)
                    boardCell.setColor(Color.BLACK);
            }
        }

        Pair[][] ships = gameState.getPlayerBattleships(id);
        for (int i = 0; i < ships.length; i++) {
            for (int t = 0; t < ships[i].length; t++) {
                BoardCell boardCell = board.getBoardCells()[(int) ships[i][t].getFirst()][(int) ships[i][t].getSecond()];
                if (reciever.getIndex() == id && playerCells[(int) ships[i][t].getFirst()][(int) ships[i][t].getSecond()] == Cell.EMPTY) {
                    boardCell.setColor(Color.MAGENTA);
                }
                if (gameState.isBattleshipDestroyed(id, i))
                    boardCell.setColor(Color.RED);
            }
        }
        ships = gameState.getPlayerCruisers(id);
        for (int i = 0; i < ships.length; i++) {
            for (int t = 0; t < ships[i].length; t++) {
                BoardCell boardCell = board.getBoardCells()[(int) ships[i][t].getFirst()][(int) ships[i][t].getSecond()];
                if (reciever.getIndex() == id && playerCells[(int) ships[i][t].getFirst()][(int) ships[i][t].getSecond()] == Cell.EMPTY) {
                    boardCell.setColor(Color.GRAY);
                }
                if (gameState.isCruiserDestroyed(id, i))
                    boardCell.setColor(Color.RED);
            }
        }
        ships = gameState.getPlayerDestroyers(id);
        for (int i = 0; i < ships.length; i++) {
            for (int t = 0; t < ships[i].length; t++) {
                BoardCell boardCell = board.getBoardCells()[(int) ships[i][t].getFirst()][(int) ships[i][t].getSecond()];
                if (reciever.getIndex() == id && playerCells[(int) ships[i][t].getFirst()][(int) ships[i][t].getSecond()] == Cell.EMPTY) {
                    boardCell.setColor(Color.CYAN);
                }
                if (gameState.isDestroyerDestroyed(id, i))
                    boardCell.setColor(Color.RED);
            }
        }
        ships = gameState.getPlayerFrigates(id);
        for (int i = 0; i < ships.length; i++) {
            for (int t = 0; t < ships[i].length; t++) {
                BoardCell boardCell = board.getBoardCells()[(int) ships[i][t].getFirst()][(int) ships[i][t].getSecond()];
                if (reciever.getIndex() == id && playerCells[(int) ships[i][t].getFirst()][(int) ships[i][t].getSecond()] == Cell.EMPTY) {
                    boardCell.setColor(Color.GREEN);
                }
                if (gameState.isFrigateDestroyed(id, i))
                    boardCell.setColor(Color.RED);
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
                    forceClick((int) pair.getFirst(), (int) pair.getSecond() + 1, id);
                    forceClick((int) pair.getFirst(), (int) pair.getSecond() - 1, id);
                    forceClick((int) pair.getFirst() + 1, (int) pair.getSecond(), id);
                    forceClick((int) pair.getFirst() + 1, (int) pair.getSecond() + 1, id);
                    forceClick((int) pair.getFirst() + 1, (int) pair.getSecond() - 1, id);
                    forceClick((int) pair.getFirst() - 1, (int) pair.getSecond(), id);
                    forceClick((int) pair.getFirst() - 1, (int) pair.getSecond() + 1, id);
                    forceClick((int) pair.getFirst() - 1, (int) pair.getSecond() - 1, id);
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            if (gameState.isCruiserDestroyed(id, i)) {
                for (Pair pair : gameState.getPlayerCruisers(id)[i]) {
                    forceClick((int) pair.getFirst(), (int) pair.getSecond() + 1, id);
                    forceClick((int) pair.getFirst(), (int) pair.getSecond() - 1, id);
                    forceClick((int) pair.getFirst() + 1, (int) pair.getSecond(), id);
                    forceClick((int) pair.getFirst() + 1, (int) pair.getSecond() + 1, id);
                    forceClick((int) pair.getFirst() + 1, (int) pair.getSecond() - 1, id);
                    forceClick((int) pair.getFirst() - 1, (int) pair.getSecond(), id);
                    forceClick((int) pair.getFirst() - 1, (int) pair.getSecond() + 1, id);
                    forceClick((int) pair.getFirst() - 1, (int) pair.getSecond() - 1, id);
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameState.isDestroyerDestroyed(id, i)) {
                for (Pair pair : gameState.getPlayerDestroyers(id)[i]) {
                    forceClick((int) pair.getFirst(), (int) pair.getSecond() + 1, id);
                    forceClick((int) pair.getFirst(), (int) pair.getSecond() - 1, id);
                    forceClick((int) pair.getFirst() + 1, (int) pair.getSecond(), id);
                    forceClick((int) pair.getFirst() + 1, (int) pair.getSecond() + 1, id);
                    forceClick((int) pair.getFirst() + 1, (int) pair.getSecond() - 1, id);
                    forceClick((int) pair.getFirst() - 1, (int) pair.getSecond(), id);
                    forceClick((int) pair.getFirst() - 1, (int) pair.getSecond() + 1, id);
                    forceClick((int) pair.getFirst() - 1, (int) pair.getSecond() - 1, id);
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            if (gameState.isFrigateDestroyed(id, i)) {
                for (Pair pair : gameState.getPlayerFrigates(id)[i]) {
                    forceClick((int) pair.getFirst(), (int) pair.getSecond() + 1, id);
                    forceClick((int) pair.getFirst(), (int) pair.getSecond() - 1, id);
                    forceClick((int) pair.getFirst() + 1, (int) pair.getSecond(), id);
                    forceClick((int) pair.getFirst() + 1, (int) pair.getSecond() + 1, id);
                    forceClick((int) pair.getFirst() + 1, (int) pair.getSecond() - 1, id);
                    forceClick((int) pair.getFirst() - 1, (int) pair.getSecond(), id);
                    forceClick((int) pair.getFirst() - 1, (int) pair.getSecond() + 1, id);
                    forceClick((int) pair.getFirst() - 1, (int) pair.getSecond() - 1, id);
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

    public int getMoveNumber() {
        return moveNumber;
    }

    public int getPlayerRemainingBoatNumber(int id) {
        int remainingBoatNumber = 0;
        for (int i = 0; i < 1; i++) {
            if (!gameState.isBattleshipDestroyed(id, i))
                remainingBoatNumber++;
        }
        for (int i = 0; i < 2; i++) {
            if (!gameState.isCruiserDestroyed(2, i))
                remainingBoatNumber++;
        }
        for (int i = 0; i < 3; i++) {
            if (!gameState.isDestroyerDestroyed(2, i))
                remainingBoatNumber++;
        }
        for (int i = 0; i < 4; i++) {
            if (!gameState.isFrigateDestroyed(2, i))
                remainingBoatNumber++;
        }

        return remainingBoatNumber;
    }

    public int getPlayerHitNumber(int id) {
        if (id == 1) {
            return player1HitNumber;
        } else {
            return player2HitNumber;
        }
    }

    @Override
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean isPlayer1Finalized() {
        return isPlayer1Finalized;
    }

    public void setPlayer1Finalized(boolean player1Finalized) {
        isPlayer1Finalized = player1Finalized;
    }

    public boolean isPlayer2Finalized() {
        return isPlayer2Finalized;
    }

    public void setPlayer2Finalized(boolean player2Finalized) {
        isPlayer2Finalized = player2Finalized;
    }
}
