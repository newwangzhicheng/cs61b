package byog.Core;

import java.util.List;
import java.util.ArrayList;

/** Hallway is defined by two Position */
public class Hallway {
    private Position from;
    private Position to;
    // private boolean priorX;

    public Hallway(Position f, Position t) {
        from = f;
        to = t;
    }

    /** get a point list that reprenest the floor of the hallway */
    public List<Position> floor() {
        List<Position> pList = new ArrayList<>();
        Position p = new Position(from.x, from.y);
        Position corner = new Position(to.x, from.y);

        /** Scan from "from" to "corner" */
        int stepX = (to.x == from.x) ? 0 : Math.abs(to.x - from.x) / (to.x - from.x);
        while (!p.equals(corner)) {
            pList.add(p);
            p = new Position(p.x + stepX, p.y);
        }

        /** Scan from "corner" to "to" */
        int stepY = (to.y == from.y) ? 0 : Math.abs(to.y - from.y) / (to.y - from.y);
        while (!p.equals(to)) {
            pList.add(p);
            p = new Position(p.x, p.y + stepY);
        }
        pList.add(to);

        return pList;

    }

    /**
     * get a point list that reprenest the wall of the hallway, includes inner and
     * outer wall
     */
    public List<Position> wall() {
        List<Position> pList = new ArrayList<>();
        Position innerFrom, innerTo, outerFrom, outerTo;
        Hallway innerHw, outerHw;
        if (stepX() == 0) {
            /** Vertical line */
            innerFrom = new Position(from.x - 1, from.y);
            innerTo = new Position(to.x - 1, to.y);
            outerFrom = new Position(from.x + 1, from.y);
            outerTo = new Position(to.x + 1, to.y);
        } else if (stepY() == 0) {
            /** Horizontal line */
            innerFrom = new Position(from.x, from.y - 1);
            innerTo = new Position(to.x, to.y - 1);
            outerFrom = new Position(from.x + 1, from.y + 1);
            outerTo = new Position(to.x + 1, to.y + 1);
        } else if (stepX() * stepY() > 0) {
            innerFrom = new Position(from.x, from.y + 1);
            innerTo = new Position(to.x - 1, to.y);
            outerFrom = new Position(from.x, from.y - 1);
            outerTo = new Position(to.x + 1, to.y);
        } else {
            innerFrom = new Position(from.x, from.y - 1);
            innerTo = new Position(to.x - 1, to.y);
            outerFrom = new Position(from.x, from.y + 1);
            outerTo = new Position(to.x + 1, to.y);
        }
        innerHw = new Hallway(innerFrom, innerTo);
        outerHw = new Hallway(outerFrom, outerTo);
        pList.addAll(innerHw.floor());
        pList.addAll(outerHw.floor());
        return pList;
    }

    /** Get the step of x-axis, can be -1, 0, 1 */
    private int stepX() {
        return (to.x == from.x) ? 0 : Math.abs(to.x - from.x) / (to.x - from.x);
    }

    /** Get the step of y-axis, can be -1, 0, 1 */
    private int stepY() {
        return (to.y == from.y) ? 0 : Math.abs(to.y - from.y) / (to.y - from.y);
    }
}
