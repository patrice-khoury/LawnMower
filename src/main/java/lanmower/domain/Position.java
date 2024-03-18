package lanmower.domain;

public class Position {
    private final int x;
    private final int y;
    private final Orientation orientation;

    public Position(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Position rotate(final Orientation newOrientation) {
        return new Position(x, y, newOrientation);
    }

    public Position move(final int newX, final int newY) {
        return new Position(newX, newY, orientation);
    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation;
    }
}
