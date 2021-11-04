package byog.Core;

import java.awt.Font;
import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public class GameDrawer {
    private static int WIDTH = 80;
    private static int HEIGHT = 30;

    /** Set width and height */
    public static void init(int width, int height) {
        WIDTH = width;
        HEIGHT = height;

        StdDraw.setCanvasSize(WIDTH * 16, HEIGHT * 16);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.WHITE);
    }

    /** draw start menu ui */
    public static void drawStartFrame(String seed, boolean showCursor) {
        StdDraw.clear(Color.BLACK);

        Font font = new Font("Monaco", Font.PLAIN, 60);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, HEIGHT * 3 / 4, "CS61B: The Game");

        font = new Font("Monaco", Font.PLAIN, 20);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, HEIGHT / 4 + 2, "New Game (N)");
        StdDraw.text(WIDTH / 2, HEIGHT / 4, "Load Game (L)");
        StdDraw.text(WIDTH / 2, HEIGHT / 4 - 2, "Quit (Q)");
        if (showCursor) {
            StdDraw.text(WIDTH / 2, HEIGHT / 4 - 4, seed + "_");
        } else {
            StdDraw.text(WIDTH / 2, HEIGHT / 4 - 4, seed);
        }

        StdDraw.show();
    }
}
