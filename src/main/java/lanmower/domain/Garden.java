package lanmower.domain;

public class Garden {
    private final int minX = 0;
    private final int minY = 0;

    private final int maxX;
    private final int maxY;

    public Garden(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
