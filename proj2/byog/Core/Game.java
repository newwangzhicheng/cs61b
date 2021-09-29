package byog.Core;

// import java.awt.Color;
// import java.awt.Font;
import java.util.Random;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
// import edu.princeton.cs.introcs.StdDraw;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main
     * menu.
     */
    public void playWithKeyboard() {
        // drawStartGUI();
    }

    /**
     * Method used for autograding and testing the game code. The input string will
     * be a series of characters (for example, "n123sswwdasdassadwas", "n123sss:q",
     * "lwww". The game should behave exactly as if the user typed these characters
     * into the game after playing playWithKeyboard. If the string ends in ":q", the
     * same world should be returned as if the string did not end with q. For
     * example "n123sss" and "n123sss:q" should return the same world. However, the
     * behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string
     * "l", we'd expect to get the exact same world back again, since this
     * corresponds to loading the saved game.
     * 
     * @param input
     *            the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        long seed = seed(input);
        Random random = new Random(seed);

        WorldGenerator wg = new WorldGenerator(WIDTH, HEIGHT, finalWorldFrame, Tileset.CUSTOM_FLOOR,
                Tileset.CUSTOM_WALL, random);
        wg.generatRoom(300, false);
        wg.connectRoom();

        // ter.initialize(WIDTH, HEIGHT);
        // ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

    /** Get seed from input */
    private long seed(String input) {
        String seedStr = "";
        for (int i = 1; i < input.length(); i++) {
            char item = input.charAt(i);
            if (item >= '0' && item <= '9') {
                seedStr += item;
            } else {
                break;
            }
        }
        return Long.parseLong(seedStr);
    }

    // private void drawStartGUI() {
    // initializeCanvas();

    // Font font = new Font("Monaco", Font.PLAIN, 60);
    // StdDraw.setFont(font);
    // StdDraw.text(WIDTH / 2, HEIGHT * 3 / 4, "CS61B: The Game");

    // font = new Font("Monaco", Font.PLAIN, 20);
    // StdDraw.setFont(font);
    // StdDraw.text(WIDTH / 2, HEIGHT / 4 + 2, "New Game (N)");
    // StdDraw.text(WIDTH / 2, HEIGHT / 4, "Load Game (L)");
    // StdDraw.text(WIDTH / 2, HEIGHT / 4 - 2, "Quit (Q)");

    // StdDraw.show();
    // }

    // /** draw gui */
    // private void initializeCanvas() {
    // StdDraw.setCanvasSize(WIDTH * 16, HEIGHT * 16);
    // StdDraw.setXscale(0, WIDTH);
    // StdDraw.setYscale(0, HEIGHT);
    // StdDraw.clear(Color.BLACK);
    // StdDraw.enableDoubleBuffering();
    // StdDraw.setPenColor(Color.WHITE);

    // }
}
