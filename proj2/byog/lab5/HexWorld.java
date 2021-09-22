package byog.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    /** Draw a hexagon tile with specific tile */
    public static void addHexagon(TETile[][] world, TETile t, int x, int y, int s) {
        for (int i = 0; i < s * 2; i++) {
            int startX = x + getOffsetX(i, s);
            int startY = y + i;
            int width = getWidth(i, s);
            addLine(world, t, startX, startY, width);
        }
    }

    /** Draw a line with specific tile */
    private static void addLine(TETile[][] world, TETile t, int x, int y, int width) {
        for (int i = 0; i < width; i++) {
            world[x + i][y] = t;
        }
    }

    /** Calculate the offset of the ith row , i start from 0 */
    private static int getOffsetX(int i, int s) {
        int half = s - 1;
        if (i <= half) {
            return half - i;
        }
        return i - s;
    }

    /** Calculate the width if the ith row */
    private static int getWidth(int i, int s) {
        int half = s - 1;
        if (i <= half) {
            return s + 2 * i;
        }
        return (5 * s - 2) - 2 * i;
    }

    /** Draw specific shape with 19 hexagon */
    public static void drawThisWorld(TETile[][] world) {
        TETile randomMountianTeTile = TETile.colorVariant(Tileset.MOUNTAIN, 32, 32, 32, new Random());
        addHexagon(world, randomMountianTeTile, 10, 0, 3);
        addHexagon(world, Tileset.FLOWER, 5, 3, 3);
        addHexagon(world, randomMountianTeTile, 15, 3, 3);
        addHexagon(world, Tileset.GRASS, 0, 6, 3);
        addHexagon(world, randomMountianTeTile, 10, 6, 3);
        addHexagon(world, Tileset.SAND, 20, 6, 3);
        addHexagon(world, randomMountianTeTile, 5, 9, 3);
        addHexagon(world, Tileset.TREE, 15, 9, 3);
        addHexagon(world, Tileset.GRASS, 0, 12, 3);
        addHexagon(world, randomMountianTeTile, 10, 12, 3);
        addHexagon(world, Tileset.TREE, 20, 12, 3);
        addHexagon(world, randomMountianTeTile, 5, 15, 3);
        addHexagon(world, Tileset.SAND, 15, 15, 3);
        addHexagon(world, randomMountianTeTile, 0, 18, 3);
        addHexagon(world, randomMountianTeTile, 10, 18, 3);
        addHexagon(world, Tileset.FLOWER, 20, 18, 3);
        addHexagon(world, Tileset.GRASS, 5, 21, 3);
        addHexagon(world, Tileset.FLOWER, 15, 21, 3);
        addHexagon(world, Tileset.TREE, 10, 24, 3);
    }

    /** Test getWidth */
    @Test
    public void testWidth() {
        assertEquals(7, getWidth(3, 3));
        assertEquals(8, getWidth(2, 4));
    }

    public static void main(String[] args) {
        final int WIDTH = 27;
        final int HEIGHT = 30;

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        /** set the hole world empty */
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
        drawThisWorld(world);
        ter.renderFrame(world);
    }
}
