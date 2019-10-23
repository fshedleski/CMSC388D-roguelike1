package game;

public enum Direction {
    Up(0), Right(1), Down(2), Left(3);

    private int value;
    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // rotate clockwise
    public void rotateCW() {
        value = (value + 3) % 4;
    }

    // rotate counter-clockwise
    public void rotateCCW() {
        value = (value + 1) % 4;
    }
}
