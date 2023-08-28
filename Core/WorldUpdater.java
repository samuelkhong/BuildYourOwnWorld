package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.io.Serializable;
import java.util.Map;

public class WorldUpdater implements Serializable{
    public Player player;
    private MapGen.Pos keyLoaction;
    private MapGen.Pos doorLocation;
    private long seed;



    private static final long serialVersionUID = 45498234744734234L;


    // takes in the
    public TETile[][] startWorld(int WIDTH, int HEIGHT, String input) {


        InputParsing inputParsing = new InputParsing(input);
        char playerInput = inputParsing.playerSelection();
        TETile[][] world = null;

        /* initalizees tiles in world */
        world = new TETile[WIDTH][HEIGHT];

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }


        // creates a new randomly generated world

        if (playerInput == 'n') {


            // parses out the seed from input
            seed = inputParsing.seedNumber();

            // fill in and render randomly seeded world
            MapGen map = new MapGen(seed);
            map.generate(world);

            // get random room and place key
//            keyLoaction =  map.getRandomRoom().midPoint();
//            drawItem(world, keyLoaction, Tileset.SAND);

            // initializes player into world
            player = new Player(map.getRandomRoom());
            drawPlayer(world, player);

            // get random room and place door
//            doorLocation = map.getRandomRoom().bottomMiddle();
//            drawItem(world, doorLocation, Tileset.LOCKED_DOOR);



        }
        // loads a previously saved world file
        else if (playerInput == 'l') {
            // check if there is a previously saved world. If so loads if not, game will close

            // load old worldstate
            WorldUpdater oldUpdater = SaveLoad.loadWorld();
            seed = oldUpdater.seed;

            MapGen map = new MapGen(seed);
            player = oldUpdater.player;

            // fill in world
            map.generate(world);
            drawPlayer(world, player);

        }
        // any other input besides , treat it as the equivalent of Q and exits
        else if (playerInput == 'q') {
            System.exit(0);
        }
        // return error first input invalid
        else {
            System.out.println("Invalid input. 'n' 'l' 'q' allowed only");
            System.exit(1);
        }

        return  world;
    }



    TETile[][] drawPlayer(TETile[][] world, Player player) {
        int xPos = player.playerPos.getX();
        int yPos = player.playerPos.getY();

        // adds current player position to world
        world[xPos][yPos] = Tileset.PLAYER;
        return world;
    }

    public static void resetOldPosition(MapGen.Pos oldPos, TETile[][] world) {
        int xPos = oldPos.getX();
        int yPos = oldPos.getY();
        world[xPos][yPos] = Tileset.FLOOR;

    }

    TETile[][] drawItem(TETile[][] world, MapGen.Pos itemPos, TETile item) {
        int xPos = itemPos.getX();
        int yPos = itemPos.getY();

        // adds current player position to world
        world[xPos][yPos] = item;
        return world;
    }

}