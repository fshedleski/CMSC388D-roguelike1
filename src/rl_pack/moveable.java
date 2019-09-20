package rl_pack;

public interface moveable {
    public enum Direction {
        Up(0), Right(1), Down(2), Left(3);

        private int value;
        Direction(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public void move(Direction _dir);

}
