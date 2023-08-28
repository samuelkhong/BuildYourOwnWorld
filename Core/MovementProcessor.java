package byog.Core;

import byog.TileEngine.TETile;

public class MovementProcessor {
    public static void processMovement(String stringCommands, TETile[][] world, WorldUpdater updater) {
        char currentChar;
        char nextChar;
        char[] charCommands = stringCommands.toCharArray();
        Player player = updater.player;

        // iterates through char[] charComamnds and inputs char to updatePlayerPosition()
        for (int i = 0; i < stringCommands.length(); i++) {
            // checks if there is a quit command
            currentChar = charCommands[i];

            if (i < charCommands.length - 1) {
                nextChar = charCommands[i + 1];

                // if in the command array ":q" found, save then exit
                if (currentChar == ':' && nextChar == 'q') {
                    SaveLoad.saveWorld(updater);
                    System.exit(0);
                }
            }
            player.updatePlayerPosition(currentChar, world);
        }
    }


}