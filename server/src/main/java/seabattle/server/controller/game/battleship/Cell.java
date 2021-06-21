package seabattle.server.controller.game.battleship;

public enum Cell {
    EMPTY {
        @Override
        public int getIndex() {
            return 0;
        }
    },FULL {
        @Override
        public int getIndex() {
            return 1;
        }
    };

    public abstract int getIndex();
}
