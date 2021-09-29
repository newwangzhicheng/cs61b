package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = { "You can do this!", "I believe in you!",
            "You got this!", "You're a star!", "Go Bears!", "Too easy for you!",
            "Wow, so impressive!" };

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40);
        game.startGame();
    }

    public MemoryGame(int width, int height) {
        /*
         * Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as
         * its canvas Also sets up the scale so the top left is (0,0) and the bottom
         * right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        // TODO: Initialize random number generator
        flashSequence("asdgf");
    }

    public String generateRandomString(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            int index = rand.nextInt(26);
            sb.append(CHARACTERS[index]);
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        StdDraw.clear(Color.BLACK);
        Font font = new Font(null, Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(width / 2, height / 2, s);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        for (String c : letters.split("")) {
            drawFrame(c);
            StdDraw.pause(1000);
            StdDraw.clear(Color.BLACK);
            StdDraw.show();
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        // TODO: Read n letters of player input
        return null;
    }

    public void startGame() {
        // TODO: Set any relevant variables before the game starts

        // TODO: Establish Game loop
    }

}
