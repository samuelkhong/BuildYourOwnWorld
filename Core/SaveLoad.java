package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TETile;

import java.io.*;

public class SaveLoad {


    public static WorldUpdater loadWorld() {
        File f = new File("./world.txt");

        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                WorldUpdater updater = (WorldUpdater) os.readObject();
                os.close();
                return updater;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }

        /* In the case no World has been saved yet, we exit  */
        System.exit(0);
        return null;
    }

    public static void saveWorld(WorldUpdater updater) {

        File f = new File("./world.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(updater);
            os.close();
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
        // once saved exit
        System.exit(0);
    }

}