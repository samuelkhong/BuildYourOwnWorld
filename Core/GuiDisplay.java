package byog.Core;

import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.awt.Font;

import java.awt.*;

public class GuiDisplay {
    int width;
    int height;

    public GuiDisplay(int width, int height) {
        this.width = width;
        this.height = height;

    }

    public void drawGui(TETile[][] world) {
        Font font = new Font("Monaco", Font.BOLD, 14);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.white);

        if (StdDraw.isMousePressed()) {
            StdDraw.textLeft(1, this.height , hoverOverTile(world));
        }
    }

//    // returns the name of a tile when the string is held over a tile
    public String hoverOverTile(TETile[][] world) {

        double xIndex =  StdDraw.mouseX();
        double yIndex = StdDraw.mouseY();

        // if click outside world ie gui boundary, return empty string
        if (xIndex < 0 || xIndex > width || yIndex < 0 || yIndex > height) {
            return "";
        }

        return world[(int) xIndex][(int) yIndex].description();
    }
}