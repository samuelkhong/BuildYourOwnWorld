package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        TETile[][] finalWorldFrame = null;
        // draw selection screen
        InteractiveMenu menu = new InteractiveMenu(40, 40);
        menu.startingMenu();
        String menuSelection = menu.solicitCharMenuInput();

        WorldUpdater updater = new WorldUpdater();
        finalWorldFrame = updater.startWorld(WIDTH, HEIGHT, menuSelection);

        ter.initialize(WIDTH, HEIGHT + 2);
        GuiDisplay gui = new GuiDisplay(WIDTH, HEIGHT);
        ter.renderFrame(finalWorldFrame);


        InteractiveMovement playerMovement = new InteractiveMovement();
        while (true) {
            playerMovement.moveCharacterIteractively(updater, finalWorldFrame);
            updater.drawPlayer(finalWorldFrame, updater.player);
            ter.renderFrame(finalWorldFrame);
            gui.drawGui(finalWorldFrame);
            StdDraw.show();
        }


    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        // reads string and starts new world if player input starts with 'n', loads world if input starts with 'l' or quits if player input is 'q'
        WorldUpdater updater = new WorldUpdater();
        finalWorldFrame = updater.startWorld(WIDTH, HEIGHT, input);

        // store user inputs as string
        InputParsing inputParsing = new InputParsing(input);
        String playerMovement = inputParsing.playerCommands();

        // read player movement commands char by char and calls functions to update world
        MovementProcessor.processMovement(playerMovement, finalWorldFrame, updater);
        updater.drawPlayer(finalWorldFrame, updater.player);



        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(finalWorldFrame);



        return finalWorldFrame;
    }
}
