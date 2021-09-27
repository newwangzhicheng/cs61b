package byog.Core;

/**
 * Point that represent the point in the world (0, 0) is the bottom-right corner
 * of the world
 */
public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** rewrite equals methods */
    @Override
    public boolean equals(Object obj) {
        Point p = (Point) obj;
        return this.x == p.x && this.y == p.y;
    }

}
