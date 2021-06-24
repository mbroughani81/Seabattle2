package seabattle.server.model;

public enum Side {
    PLAYER_ONE{
        @Override
        public Side getOther() {
            return PLAYER_TWO;
        }

        @Override
        public int getIndex() {
            return 1;
        }

    },PLAYER_TWO{
        @Override
        public Side getOther() {
            return PLAYER_ONE;
        }

        @Override
        public int getIndex() {
            return 2;
        }
    },SPECTATOR{
        @Override
        public Side getOther() {
            return SPECTATOR;
        }

        @Override
        public int getIndex() {
            return 3;
        }
    };

    public abstract Side getOther();

    public abstract int getIndex();
}
