package byog.Core.Rectangle;

/**
 * Hallway is a element that has the listed rules: length >= 1; orientation must
 * be horizontal or vertical; just includes floor; bottom > 0, left > 0, top <
 * world.HEIGHT, right < world.WIDTH;
 */
public class Hallway extends AbstractRectangle {
    public Hallway(String orientation, int length) {
        if (orientation == "horizontal") {
            width = length;
            height = 1;
        } else if (orientation == "vertical") {
            width = 1;
            height = length;
        } else {
            throw new IllegalArgumentException("orientation must be horizontal or vertical");
        }
    }
}
