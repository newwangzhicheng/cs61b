package byog.Core;

import edu.princeton.cs.introcs.StdDraw;

public class GameMode {
    char mode;

    /** select mode with keyboard */
    public void selectMode() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                mode = Character.toUpperCase(StdDraw.nextKeyTyped());
                if (mode == 'N' || mode == 'S' || mode == 'Q') {
                    return;
                }
            }

        }
    }

    /** change the view and load a game */
    public String execute() {
        switch (mode) {
            case 'N':
                return newGame();
            case 'L':
                return loadGame();
            case 'Q':
                return quitGame();
            default:
                return "";
        }
    }

    /** return a string like "NxxxxxS" represents new a game */
    private String newGame() {
        String input = "";
        GameDrawer.drawStartFrame(input, true);
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char typedKey = Character.toUpperCase(StdDraw.nextKeyTyped());
                if (typedKey >= '0' && typedKey <= '9') {
                    input += typedKey;
                }
                if (typedKey == 'S') {
                    return mode + input + typedKey;
                }
                GameDrawer.drawStartFrame(input, true);
            }

        }
    }

    /** return a string like "NxxxxxL" represents load a game */
    private String loadGame() {
        return "";
    }

    /** return a string like "Q" represents quit */
    private String quitGame() {
        return "";
    }
}
