package byog.Core.Rectangle;

/**
 * Room is a element that has the listed rules: width >= 1 height >= 1; just
 * includes floor; bottom > 0, left > 0, top < world.HEIGHT, right <
 * world.WIDTH;
 */
public class Room extends AbstractRectangle {
    public Room(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
