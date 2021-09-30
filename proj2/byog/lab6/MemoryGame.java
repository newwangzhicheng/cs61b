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
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
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

        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            int index = rand.nextInt(CHARACTERS.length);
            sb.append(CHARACTERS[index]);
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        StdDraw.clear(Color.BLACK);

        /** Draw base text */
        if (!gameOver) {
            Font fontInfo = new Font("Monaco", Font.BOLD, 20);
            StdDraw.setFont(fontInfo);
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.textLeft(1, height - 2, "Round: " + round);
            StdDraw.text(width / 2, height - 2, playerTurn ? "Type!" : "Watch!");
            StdDraw.textRight(width - 1, height - 2, ENCOURAGEMENT[round % 3]);
            StdDraw.line(0, height - 3, width, height - 3);
        }

        /** Draw game text */
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(width / 2, height / 2, s);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        for (String c : letters.split("")) {
            drawFrame(c);
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.show();
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        String input = "";
        while (input.length() < n) {
            if (StdDraw.hasNextKeyTyped()) {
                input += StdDraw.nextKeyTyped();
            }
            drawFrame(input);
        }
        StdDraw.pause(500);
        return input;
    }

    public void startGame() {
        round = 1;
        gameOver = false;
        playerTurn = false;

        while (!gameOver) {
            drawFrame("Round: " + round);
            StdDraw.pause(1500);
            String roundStr = generateRandomString(round);
            playerTurn = false;
            flashSequence(roundStr);
            playerTurn = true;
            String input = solicitNCharsInput(round).toLowerCase();
            if (!input.equals(roundStr)) {
                gameOver = true;
                drawFrame("Game Over! You made it to round: " + round);
            } else {
                drawFrame("Correct! Good job!");
                StdDraw.pause(1500);
            }
            round++;
        }
    }

}
