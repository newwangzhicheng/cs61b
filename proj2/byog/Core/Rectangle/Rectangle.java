package byog.Core.Rectangle;

import byog.Core.Point;
import byog.TileEngine.TETile;

public interface Rectangle {
    /** Get the width of the rectangle */
    public int width();

    /** Get the height of the rectangle */
    public int height();

    /** Get the short edge length of the rectangle */
    public int shortEdge();

    /** Get the long edge length of the rectangle */
    public int longEdge();

    /** Render the rectangle start at the point */
    public void render(TETile[][] world, TETile floor, Point point);
}
