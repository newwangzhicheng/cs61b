package byog.Core.Rectangle;

import byog.Core.Point;
import byog.TileEngine.TETile;

public abstract class AbstractRectangle implements Rectangle {
    public int width;
    public int height;

    /** Get the width of the rectangle */
    @Override
    public int width() {
        return width;
    }

    /** Get the height of the rectangle */
    @Override
    public int height() {
        return height;
    }

    /** Get the short edge length of the rectangle */
    @Override
    public int shortEdge() {
        return width < height ? width : height;
    }

    /** Get the long edge length of the rectangle */
    @Override
    public int longEdge() {
        return width > height ? width : height;
    }

    /** Render the rectangle start at the point */
    @Override
    public void render(TETile[][] world, TETile floor, Point point) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int x = point.x + width;
                int y = point.y + height;
                world[x][y] = floor;
            }
        }
    }
}
