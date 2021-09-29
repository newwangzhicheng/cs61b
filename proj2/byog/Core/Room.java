package byog.Core;

/**
 * define a room that minWidth is 4(includes wall)
 */
public class Room implements Comparable<Room> {
    private int width;
    private int height;
    private Position position;

    public Room(int w, int h, Position p) {
        width = w;
        height = h;
        position = p;
    }

    /** left position of the room */
    public int left() {
        return position.x;
    }

    /** top position of the room */
    public int top() {
        return position.y + height - 1;
    }

    /** right position of the room */
    public int right() {
        return position.x + width - 1;
    }

    /** bottom position of the room */
    public int bottom() {
        return position.y;
    }

    /**
     * check if two room is overlap, calculate the projection x-axis and y-axis,
     * check if two projections are crossing
     */
    public boolean overlap(Room r, boolean canAdjacent) {
        int gap = canAdjacent ? 0 : -1;
        int minRight = Math.min(r.right(), this.right());
        int maxLeft = Math.max(r.left(), this.left());
        int minTop = Math.min(r.top(), this.top());
        int maxBottom = Math.max(r.bottom(), this.bottom());
        boolean crossingX = minRight - maxLeft >= gap;
        boolean crossingY = minTop - maxBottom >= gap;
        return crossingX && crossingY;
    }

    /** the room on the left*left + bottom*bottom is smaller */
    @Override
    public int compareTo(Room r) {
        // /** Compare left first */
        // if (this.left() < r.left()) {
        // return -1;
        // }
        // if (this.left() > r.left()) {
        // return 1;
        // }

        // /** Compare bottom then */
        // if (this.bottom() < r.bottom()) {
        // return -1;
        // }
        // if (this.bottom() > r.bottom()) {
        // return 1;
        // }

        // return 0;

        return (this.left() * this.left() + this.bottom() * this.bottom())
                - (r.left() * r.left() + r.bottom() * r.bottom());
    }
}
