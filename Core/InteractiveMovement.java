package byog.Core;

import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

public class InteractiveMovement {

    public void moveCharacterIteractively(WorldUpdater updater, TETile[][] world) {
            char directionalInput;

            // wait till player input
            while (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            directionalInput = StdDraw.nextKeyTyped();

            // check if quit ":q" is pressed, if so save and exit
            if (directionalInput == ':') {
              // wait for next input
                while (!StdDraw.hasNextKeyTyped()) {
                    continue;
                }

                char nextChar = StdDraw.nextKeyTyped();
                // make nextChar lowercase
                if (Character.isAlphabetic(nextChar)) {
                    nextChar = Character.toLowerCase(nextChar);
                }
                if (nextChar == 'q') {
                    SaveLoad.saveWorld(updater);
                    System.exit(0);
                }
            }
            updater.player.updatePlayerPosition(directionalInput, world);



    }
}