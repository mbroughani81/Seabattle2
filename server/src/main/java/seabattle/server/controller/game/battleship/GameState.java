package seabattle.server.controller.game.battleship;

import seabattle.shared.datatype.Pair;

public class GameState {
    Cell[][] player1Cells;
    Pair[][] player1Battleships;
    Pair[][] player1Cruisers;
    Pair[][] player1Destroyers;
    Pair[][] player1Frigates;
    Cell[][] player2Cells;
    Pair[][] player2Battleships;
    Pair[][] player2Cruisers;
    Pair[][] player2Destroyers;
    Pair[][] player2Frigates;
    int width;
    int height;

    public GameState(int width, int height) {
        this.width = width;
        this.height = height;
        player1Cells = new Cell[height][width];
        player2Cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int t = 0; t < width; t++) {
                player1Cells[i][t] = Cell.EMPTY;
                player2Cells[i][t] = Cell.EMPTY;
            }
        }

//        player1Battleships = new Pair[1][4];
//        player2Battleships = new Pair[1][4];
//        player1Battleships[0][0] = new Pair<>(0, 0);
//        player1Battleships[0][1] = new Pair<>(0, 1);
//        player1Battleships[0][2] = new Pair<>(0, 2);
//        player1Battleships[0][3] = new Pair<>(0, 3);
        RandomShip randomShip = new RandomShip();
        player1Battleships = randomShip.getPlayerBattleships();
        player1Cruisers = randomShip.getPlayerCruisers();
        player1Destroyers = randomShip.getPlayerDestroyers();
        player1Frigates = randomShip.getPlayerFrigates();
        randomShip = new RandomShip();
        player2Battleships = randomShip.getPlayerBattleships();
        player2Cruisers = randomShip.getPlayerCruisers();
        player2Destroyers = randomShip.getPlayerDestroyers();
        player2Frigates = randomShip.getPlayerFrigates();
//        RandomShip randomShip = new RandomShip();
//        player2Battleships[0][0] = new Pair<>(0, 0);
//        player2Battleships[0][1] = new Pair<>(1, 0);
//        player2Battleships[0][2] = new Pair<>(2, 0);
//        player2Battleships[0][3] = new Pair<>(3, 0);



    }

    public Cell[][] getPlayer1Cells() {
        return player1Cells;
    }

    public Cell[][] getPlayer2Cells() {
        return player2Cells;
    }

    public Cell[][] getPlayerCells(int id) {
        if (id == 1)
            return getPlayer1Cells();
        return getPlayer2Cells();
    }

    public Pair[][] getPlayer1Battleships() {
        return player1Battleships;
    }

    public Pair[][] getPlayer2Battleships() {
        return player2Battleships;
    }

    public Pair[][] getPlayerBattleships(int id) {
        if (id == 1)
            return getPlayer1Battleships();
        return getPlayer2Battleships();
    }

    public Pair[][] getPlayer1Cruisers() {
        return player1Cruisers;
    }

    public Pair[][] getPlayer2Cruisers() {
        return player2Cruisers;
    }

    public Pair[][] getPlayerCruisers(int id) {
        if (id == 1)
            return getPlayer1Cruisers();
        return getPlayer2Cruisers();
    }

    public Pair[][] getPlayer1Destroyers() {
        return player1Destroyers;
    }

    public Pair[][] getPlayer2Destroyers() {
        return player2Destroyers;
    }

    public Pair[][] getPlayerDestroyers(int id) {
        if (id == 1)
            return getPlayer1Destroyers();
        return getPlayer2Destroyers();
    }

    public Pair[][] getPlayer1Frigates() {
        return player1Frigates;
    }

    public Pair[][] getPlayer2Frigates() {
        return player2Frigates;
    }

    public Pair[][] getPlayerFrigates(int id) {
        if (id == 1)
            return getPlayer1Frigates();
        return getPlayer2Frigates();
    }

    public boolean isBattleshipDestroyed(int id, int index) {
        for (Pair pair : getPlayerBattleships(id)[index]) {
            Cell cell = getPlayerCells(id)[(int)pair.getFirst()][(int)pair.getSecond()];
            if (cell != Cell.FULL)
                return false;
        }
        return true;
    }

    public boolean isCruiserDestroyed(int id, int index) {
        for (Pair pair : getPlayerCruisers(id)[index]) {
            Cell cell = getPlayerCells(id)[(int)pair.getFirst()][(int)pair.getSecond()];
            if (cell != Cell.FULL)
                return false;
        }
        return true;
    }

    public boolean isDestroyerDestroyed(int id, int index) {
        for (Pair pair : getPlayerDestroyers(id)[index]) {
            Cell cell = getPlayerCells(id)[(int)pair.getFirst()][(int)pair.getSecond()];
            if (cell != Cell.FULL)
                return false;
        }
        return true;
    }

    public boolean isFrigateDestroyed(int id, int index) {
        for (Pair pair : getPlayerFrigates(id)[index]) {
            Cell cell = getPlayerCells(id)[(int)pair.getFirst()][(int)pair.getSecond()];
            if (cell != Cell.FULL)
                return false;
        }
        return true;
    }

    boolean isBoat(int row, int col, int id) {
        for (int i = 0; i < 1; i++) {
            for (Pair pair : getPlayerBattleships(id)[i])
                if ((int)pair.getFirst() == row && (int)pair.getSecond() == col)
                    return true;
        }
        for (int i = 0; i < 2; i++) {
            for (Pair pair : getPlayerCruisers(id)[i])
                if ((int)pair.getFirst() == row && (int)pair.getSecond() == col)
                    return true;
        }
        for (int i = 0; i < 3; i++) {
            for (Pair pair : getPlayerDestroyers(id)[i])
                if ((int)pair.getFirst() == row && (int)pair.getSecond() == col)
                    return true;
        }
        for (int i = 0; i < 4; i++) {
            for (Pair pair : getPlayerFrigates(id)[i])
                if ((int)pair.getFirst() == row && (int)pair.getSecond() == col)
                    return true;
        }
        return false;
    }
}
