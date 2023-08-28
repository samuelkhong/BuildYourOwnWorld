package byog.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class InteractiveMenu {

    int width;
    int height;

    InteractiveMenu(int width, int height) {

        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.WHITE);
    }

    public void startingMenu() {
        int midWidth = width / 2;
        int midHeight = height / 2;

        StdDraw.clear();
        StdDraw.clear(Color.black);


        // Draw the actual text
        Font bigFont = new Font("Monaco", Font.BOLD, 50);
        StdDraw.setFont(bigFont);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(midWidth, height * .66, "CS 61:B The Game");

        Font smallFont = new Font("Monaco", Font.PLAIN, 20);
        StdDraw.setFont(smallFont);
        StdDraw.text(midWidth, midHeight, "New Game (N)");
        StdDraw.text(midWidth, midHeight - 2, "Load Game (L)");
        StdDraw.text(midWidth, midHeight - 4, "Quit (Q)");

        StdDraw.show();
    }

    public void drawframe(String s) {
        int midWidth = width / 2;
        int midHeight = height / 2;

        StdDraw.clear();
        StdDraw.clear(Color.black);

        // Draw the actual text
        Font bigFont = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(bigFont);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(midWidth, midHeight, s);
        StdDraw.show();
    }

    public String solicitCharMenuInput() {
        char c;
        String s = "";

        // wait till player input
        while (!StdDraw.hasNextKeyTyped()) {
        }
        c = StdDraw.nextKeyTyped();
        if (Character.isAlphabetic(c)) {
            c = Character.toLowerCase(c);
        }

        // if c != 'n' 'l' 'q' then wait again for input
        while (c != 'n' && c != 'l' && c != 'q') {
            // wait for input
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            // once input update c
            c = StdDraw.nextKeyTyped();

            if (Character.isAlphabetic(c)) {
                c = Character.toLowerCase(c);
            }
        }


        if (c == 'n') {
            //if 'n' loop till player adds numbers and presses 's'
            s += c;
            drawframe("Please Enter Seed");

            String seed = "";
            boolean isCompletedSeed = false;
            while (!isCompletedSeed) {

                while (!StdDraw.hasNextKeyTyped()) {
                    continue;
                }

                c = StdDraw.nextKeyTyped();
                if (Character.isDigit(c)) {
                    seed += c;
                    drawframe(seed);
                } else if (c == 's' && seed.length() > 0) {
                    seed += c;
                    drawframe(seed);

                    s += seed;
                    isCompletedSeed = true;
                }
            }
        }
        // if load or q, save character and return
        else if (c == 'l' || c == 'q') {
            s += c;
        }

        return s;
    }
}