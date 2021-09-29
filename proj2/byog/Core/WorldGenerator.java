package byog.Core;

import java.util.Random;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.List;
import java.util.ArrayList;

public class WorldGenerator {
    private int width;
    private int height;
    private TETile[][] world;
    private TETile floor;
    private TETile wall;
    private Random random;
    private List<Room> roomList;
    /** represent the type of the position; 0 = nothing; 1 = floor; 2 = wall */
    private int[][] type;

    public WorldGenerator(int w, int h, TETile[][] wd, TETile f, TETile wl, Random r) {
        width = w;
        height = h;
        random = r;
        world = wd;
        floor = f;
        wall = wl;
        roomList = new ArrayList<>();
        type = new int[width][height];

        /** fill the world with nothing at the beginning */
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                renderTile(i, j, 0);
            }
        }
    }

    /** init the world, fill with nothing */
    public static void initialize(TETile[][] wd, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                wd[i][j] = Tileset.NOTHING;
            }
        }
    }

    /** Generate room try count times */
    public void generatRoom(int count, boolean canRoomBePlaced) {
        int w, h, x, y;
        Position p;
        Room r;
        for (int i = 0; i < count; i++) {
            w = RandomUtils.uniform(random, 4, 11);
            h = RandomUtils.uniform(random, 4, 11);
            x = random.nextInt(width - w + 1);
            y = random.nextInt(height - h + 1);
            p = new Position(x, y);
            r = new Room(w, h, p);
            if (canRoomBePlaced(r, canRoomBePlaced)) {
                renderRoom(r);
                roomList.add(r);
            }
        }
    }

    /**
     * Sort first and the connect adjacent rooms, get random floor from a and b,
     * connect a and b with render
     */
    public void connectRoom() {
        sortRoomList();
        for (int i = 0; i < roomList.size() - 1; i++) {
            Room a = roomList.get(i);
            Room b = roomList.get(i + 1);
            Position pA = getRandomFloor(a);
            Position pB = getRandomFloor(b);
            renderHallway(pA, pB);
        }
    }

    /** sort the roomList, from left-bottom to top-right */
    private void sortRoomList() {
        roomList.sort((a, b) -> a.compareTo(b));
    }

    /** get a random position from the floor of room */
    private Position getRandomFloor(Room r) {
        int x = RandomUtils.uniform(random, r.left() + 1, r.right());
        int y = RandomUtils.uniform(random, r.bottom() + 1, r.top());
        return new Position(x, y);
    }

    /** render a hallway from pA to pB */
    private void renderHallway(Position pA, Position pB) {
        Hallway hw = new Hallway(pA, pB);
        for (Position p : hw.floor()) {
            renderTile(p.x, p.y, 1);
        }
        for (Position p : hw.wall()) {
            if (type[p.x][p.y] != 1) {
                renderTile(p.x, p.y, 2);
            }
        }
    }

    /** loop each room in roomList to check if this room is overlap */
    private boolean canRoomBePlaced(Room room, boolean canAdjacent) {
        for (Room r : roomList) {
            if (r.overlap(room, canAdjacent)) {
                return false;
            }
        }
        return true;
    }

    /** Render rooms with floor and wall */
    private void renderRoom(Room r) {
        int startX = r.left();
        int startY = r.bottom();
        int endX = r.right();
        int endY = r.top();
        /** Scan by column */
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (i == startX || i == endX || j == startY || j == endY) {
                    /** Wall render */
                    renderTile(i, j, 2);
                } else {
                    /** Floor render */
                    renderTile(i, j, 1);
                }
            }
        }
    }

    /** render a tile */
    private void renderTile(int x, int y, int t) {
        type[x][y] = t;
        switch (t) {
        case 0:
            world[x][y] = Tileset.NOTHING;
            break;
        case 1:
            world[x][y] = floor;
            break;
        case 2:
            world[x][y] = wall;
            break;
        default:
            break;
        }
    }
}
