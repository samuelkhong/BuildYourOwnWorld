package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Scanner;

public class MapVisualizer {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;



    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        /* initalizees tiles in world */
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input seed:");
        long seed = scanner.nextLong();
        scanner.close();


        MapGen map = new MapGen(seed);
        map.generate(world);
        ter.renderFrame(world);
    }
}