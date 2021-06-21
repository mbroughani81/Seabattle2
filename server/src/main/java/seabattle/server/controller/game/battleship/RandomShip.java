package seabattle.server.controller.game.battleship;

import seabattle.shared.datatype.Pair;

import java.util.Random;

public class RandomShip {

    private final Pair[][] playerBattleships = new Pair[1][4];
    private final Pair[][] playerCruisers = new Pair[2][3];;
    private final Pair[][] playerDestroyers = new Pair[3][2];;
    private final Pair[][] playerFrigates = new Pair[4][1];;

    public RandomShip() {
        Random random = new Random();

        while(true) {
            for (int i = 0; i < 1; i++) {
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                int type = (random.nextInt(2) % 2 == 0) ? 0 : 1; // 0 : horizontal 1 : vertical
                fill(playerBattleships[i], row, col, type, 4);
            }
            for (int i = 0; i < 2; i++) {
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                int type = (random.nextInt(2) % 2 == 0) ? 0 : 1; // 0 : horizontal 1 : vertical
                fill(playerCruisers[i], row, col, type, 3);
            }
            for (int i = 0; i < 3; i++) {
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                int type = (random.nextInt(2) % 2 == 0) ? 0 : 1; // 0 : horizontal 1 : vertical
                fill(playerDestroyers[i], row, col, type, 2);
            }
            for (int i = 0; i < 4; i++) {
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                int type = (random.nextInt(2) % 2 == 0) ? 0 : 1; // 0 : horizontal 1 : vertical
                fill(playerFrigates[i], row, col, type, 1);
            }
            if (isOk())
                break;
        }
    }

    private void fill(Pair[] arr, int row, int col, int type, int cnt) {
        if (type == 0) {
            for (int i = 0; i < cnt; i++)
                arr[i] = new Pair(row + i, col);
        } else {
            for (int i = 0; i < cnt; i++)
                arr[i] = new Pair(row, col + i);
        }
    }

    private boolean isOk() {
        boolean[][] isInBoat = new boolean[10][10];
        for (int i = 0; i < 1; i++) {
            for (int t = 0; t < 4; t++) {
                if ((int)playerBattleships[i][t].getFirst() < 0 || (int)playerBattleships[i][t].getFirst() >= 10)
                    return false;
                if ((int)playerBattleships[i][t].getSecond() < 0 || (int)playerBattleships[i][t].getSecond() >= 10)
                    return false;
                isInBoat[(int)playerBattleships[i][t].getFirst()][(int)playerBattleships[i][t].getSecond()] = true;
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int t = 0; t < 3; t++) {
                if ((int)playerCruisers[i][t].getFirst() < 0 || (int)playerCruisers[i][t].getFirst() >= 10)
                    return false;
                if ((int)playerCruisers[i][t].getSecond() < 0 || (int)playerCruisers[i][t].getSecond() >= 10)
                    return false;
                isInBoat[(int)playerCruisers[i][t].getFirst()][(int)playerCruisers[i][t].getSecond()] = true;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int t = 0; t < 2; t++) {
                if ((int)playerDestroyers[i][t].getFirst() < 0 || (int)playerDestroyers[i][t].getFirst() >= 10)
                    return false;
                if ((int)playerDestroyers[i][t].getSecond() < 0 || (int)playerDestroyers[i][t].getSecond() >= 10)
                    return false;
                isInBoat[(int)playerDestroyers[i][t].getFirst()][(int)playerDestroyers[i][t].getSecond()] = true;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int t = 0; t < 1; t++) {
                if ((int)playerFrigates[i][t].getFirst() < 0 || (int)playerFrigates[i][t].getFirst() >= 10)
                    return false;
                if ((int)playerFrigates[i][t].getSecond() < 0 || (int)playerFrigates[i][t].getSecond() >= 10)
                    return false;
                isInBoat[(int)playerFrigates[i][t].getFirst()][(int)playerFrigates[i][t].getSecond()] = true;
            }
        }
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (!isInBoat[x][y])
                    continue;
                int cnt = 0;
                for (int i = 0; i < 1; i++) {
                    if (isNear(playerBattleships[i], x, y))
                        cnt++;
                }
                for (int i = 0; i < 2; i++) {
                    if (isNear(playerCruisers[i], x, y))
                        cnt++;
                }
                for (int i = 0; i < 3; i++) {
                    if (isNear(playerDestroyers[i], x, y))
                        cnt++;
                }
                for (int i = 0; i < 4; i++) {
                    if (isNear(playerFrigates[i], x, y))
                        cnt++;
                }

                if (cnt > 1)
                    return false;
            }
        }
        return true;
    }

    private boolean isNear(Pair[] arr, int x, int y) {
        for (Pair pair : arr) {
            int s1 = Math.abs((int) pair.getFirst() - x);
            int s2 = Math.abs((int) pair.getSecond() - y);
            if (Math.max(s1, s2) <= 1)
                return true;
        }
        return false;
    }

    public Pair[][] getPlayerBattleships() {
        return playerBattleships;
    }

    public Pair[][] getPlayerCruisers() {
        return playerCruisers;
    }

    public Pair[][] getPlayerDestroyers() {
        return playerDestroyers;
    }

    public Pair[][] getPlayerFrigates() {
        return playerFrigates;
    }
}
