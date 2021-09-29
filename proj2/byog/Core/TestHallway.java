package byog.Core;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.List;

public class TestHallway {
    @Test
    public void testFloor() {
        Position from = new Position(0, 0);
        Position to = new Position(2, 3);
        Hallway hw = new Hallway(from, to);
        List<Position> floor = hw.floor();
        assertEquals(6, floor.size());
        assertEquals(new Position(0, 0), floor.get(0));
        assertEquals(new Position(1, 0), floor.get(1));
        assertEquals(new Position(2, 0), floor.get(2));
        assertEquals(new Position(2, 1), floor.get(3));
        assertEquals(new Position(2, 2), floor.get(4));
        assertEquals(new Position(2, 3), floor.get(5));

        Position from2 = new Position(2, 3);
        Position to2 = new Position(2, 1);
        Hallway hw2 = new Hallway(from2, to2);
        List<Position> floor2 = hw2.floor();
        assertEquals(3, floor2.size());
        assertEquals(new Position(2, 3), floor2.get(0));
        assertEquals(new Position(2, 2), floor2.get(1));
        assertEquals(new Position(2, 1), floor2.get(2));
    }

    @Test
    public void testWall() {
        Position from = new Position(1, 1);
        Position to = new Position(3, 4);
        Hallway hw = new Hallway(from, to);
        List<Position> wall = hw.wall();
        assertEquals(12, wall.size());

        Position from2 = new Position(3, 4);
        Position to2 = new Position(1, 1);
        Hallway hw2 = new Hallway(from2, to2);
        List<Position> wall2 = hw2.wall();
        assertEquals(12, wall2.size());

        Position from3 = new Position(1, 2);
        Position to3 = new Position(2, 1);
        Hallway hw3 = new Hallway(from3, to3);
        List<Position> wall3 = hw3.wall();
        assertEquals(6, wall3.size());
    }
}
