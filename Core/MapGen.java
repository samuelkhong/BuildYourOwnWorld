package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.*;
import java.io.Serializable;

public class MapGen implements Serializable{
    private final long SEED;
    private  final Random RANDOM;
    private  List<Room> rooms;
    private  final int gameWIDTH = 80;
    private  final int gameHEIGHT = 30;
    private static final long serialVersionUID = 45498234744734234L;

    public MapGen(long seed) {
        SEED = seed;
        RANDOM = new Random(SEED);
        rooms = new ArrayList<Room>();
    }

    public static class Pos implements Serializable{
        private int x;
        private int y;
        private static final long serialVersionUID = 45498234744734234L;

        public Pos() {
        }
        public Pos(Pos x) {
            this.x = x.getX();
            this.y = x.getY();
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }
    }

    private Pos randomPosition() {
        Pos randomRoom = new Pos();
        randomRoom.x = RANDOM.nextInt(gameWIDTH);
        randomRoom.y = RANDOM.nextInt(gameHEIGHT);

        return randomRoom;
    }

    private void drawLargerL(Room previousRoom, Room currentRoom, int hallType, TETile[][] world, TETile terrain) {
        Pos lHallStart = new Pos(previousRoom.midPoint());
        Pos lHallEnd = new Pos(currentRoom.midPoint());

        int xDif = previousRoom.midPoint().getX() - currentRoom.midPoint().getX();
        int yDif = previousRoom.midPoint().getY() - currentRoom.midPoint().getY();
        // if rooms are vertical or horizontal to one another, hallType changes
        if ( xDif == 0) {
            hallType = 2;
        }
        else if (yDif == 0)  {
            hallType = 3;
        }

        //draws correct Type of hall based on hallType
        switch(hallType) {
            case 0: {
                if (xDif < 0) {
                    if (yDif < 0) {
                        lHallStart.y = lHallStart.y - 1;
                        lHallEnd.x = lHallEnd.x + 1;

                    }
                    else {
                        lHallStart.y = lHallStart.y + 1;
                        lHallEnd.x = lHallEnd.x + 1;
                    }
                }
                else {
                    if (yDif < 0) {
                        lHallStart.y = lHallStart.y - 1;
                        lHallEnd.x = lHallEnd.x - 1;
                    }
                    else {
                        lHallStart.y = lHallStart.y + 1;
                        lHallEnd.x = lHallEnd.x - 1;
                    }
                }

                drawHorizontalHall(lHallStart.getX(), lHallEnd.getX(), lHallStart.getY(), world, terrain);
                drawVerticlalHall(lHallStart.getY(), lHallEnd.getY(), lHallEnd.getX(), world, terrain);
                break;
            }

            case 1: {
                if (xDif < 0) { // if prev room is to the left of current room
                    if (yDif < 0) { // if prev room below current room
                        lHallStart.x = lHallStart.x - 1;
                        lHallEnd.y = lHallEnd.y + 1;
                    } else {
                        lHallStart.x = lHallStart.x - 1;
                        lHallEnd.y = lHallEnd.y - 1;
                    }
                }
                // if previous room is to the right of the current room
                else {
                    if (yDif < 0) {
                        lHallStart.x = lHallStart.x + 1;
                        lHallEnd.y = lHallEnd.y + 1;
                    }
                    else {
                        lHallStart.x = lHallStart.x + 1;
                        lHallEnd.y = lHallEnd.y - 1;
                    }
                }

                drawVerticlalHall(lHallStart.getY(), lHallEnd.getY(), lHallStart.getX(), world, terrain);
                drawHorizontalHall(lHallStart.getX(), lHallEnd.getX(), lHallEnd.getY(), world, terrain);
                break;
            }
            // if two rooms have same x value
            case 2: {
                lHallStart.x = lHallStart.x + 1;
                lHallEnd.x = lHallEnd.x + 1;

                drawVerticlalHall(lHallStart.getY(), lHallEnd.getY(), lHallStart.getX(), world, terrain);
                break;
            }
            // if midpoints have the same y value, the larger Lhall will be above the middle
            case 3: {
                lHallStart.y = lHallStart.y + 1;
                lHallEnd.y = lHallEnd.y + 1;

                drawHorizontalHall(lHallStart.getX(), lHallEnd.getX(), lHallEnd.getY(), world, terrain);
                break;
            }
        }
    }

   private void drawSmallerL(Room previousRoom, Room currentRoom, int hallType, TETile[][] world, TETile terrain) {
        int xDif = previousRoom.midPoint().getX() - currentRoom.midPoint().getX();
        int yDif = previousRoom.midPoint().getY() - currentRoom.midPoint().getY();

        Pos lHallStart = new Pos(previousRoom.midPoint());
        Pos lHallEnd = new Pos(currentRoom.midPoint());

        if ( xDif == 0) {
            hallType = 2;
        }
        else if (yDif == 0)  {
            hallType = 3;
        }

        switch(hallType) {
            case 0: {
                if (xDif < 0) {
                    if (yDif < 0) {
                        lHallStart.y = lHallStart.y + 1;
                        lHallEnd.x = lHallEnd.x - 1;
                    }
                    else {
                        lHallStart.y = lHallStart.y - 1;
                        lHallEnd.x = lHallEnd.x - 1;
                    }
                }
                else {
                    if (yDif < 0) {
                        lHallStart.y = lHallStart.y + 1;
                        lHallEnd.x = lHallEnd.x + 1;
                    }
                    else {
                        lHallStart.y = lHallStart.y - 1;
                        lHallEnd.x = lHallEnd.x + 1;
                    }
                }

                drawHorizontalHall(lHallStart.getX(), lHallEnd.getX(), lHallStart.getY(), world, terrain);
                drawVerticlalHall(lHallStart.getY(), lHallEnd.getY(), lHallEnd.getX(), world, terrain);
                break;
            }

            case 1: {
                if (xDif < 0) { // if prev room is to the left of current room
                    if (yDif < 0) { // if prev room below current room
                        lHallStart.x = lHallStart.x + 1;
                        lHallEnd.y = lHallEnd.y - 1;
                    }
                    else {
                        lHallStart.x = lHallStart.x + 1;
                        lHallEnd.y = lHallEnd.y + 1;
                    }
                }
                else if (xDif > 0) {
                    if (yDif > 0) {
                        lHallStart.x = lHallStart.x - 1;
                        lHallEnd.y = lHallEnd. y + 1;
                    }
                    else {
                        lHallStart.x = lHallStart.x - 1;
                        lHallEnd.y = lHallEnd.y - 1;
                    }
                }
                drawVerticlalHall(lHallStart.getY(), lHallEnd.getY(), lHallStart.getX(), world, terrain);
                drawHorizontalHall(lHallStart.getX(), lHallEnd.getX(), lHallEnd.getY(), world, terrain);
                break;
            }
            // if hall midpoints are horizontal to one another, smallerL will below
            case 2: {
                lHallStart.x = lHallStart.x - 1;
                lHallEnd.x = lHallEnd.x - 1;
                drawVerticlalHall(lHallStart.getY(), lHallEnd.getY(), lHallStart.getX(), world, terrain);
            }
            // if hall midpoints are completely vertical to one another, to the left
            case 3: {
                lHallStart.y = lHallStart.y - 1;
                lHallEnd.y = lHallEnd.y - 1;
                drawHorizontalHall(lHallStart.getX(), lHallEnd.getX(), lHallEnd.getY(), world, terrain);
                break;
            }
        }
    }

    private void drawMiddleL(Room previousRoom, Room currentRoom, int hallType, TETile[][] world, TETile terrain) {

        if(hallType == 0) {
            // start previous room with a horizontal connecting hallway based on random number hallType
            drawHorizontalHall(previousRoom.midPoint().getX(), currentRoom.midPoint().getX(), previousRoom.midPoint().getY(), world, Tileset.FLOOR);
            drawVerticlalHall(previousRoom.midPoint().getY(), currentRoom.midPoint().getY(), currentRoom.midPoint().getX(), world, Tileset.FLOOR);
        }
        else {
            // start previous room with a vertical connecting hallway
            drawVerticlalHall(previousRoom.midPoint().getY(), currentRoom.midPoint().getY(), previousRoom.midPoint().getX(),  world, Tileset.FLOOR); // draws vertical row starting at previous room
            drawHorizontalHall(previousRoom.midPoint().getX(), currentRoom.midPoint().getX(), currentRoom.midPoint().getY(), world, Tileset.FLOOR);
        }
    }

    // draw horizontal  shaped hallway from the previous room to current room
    private void drawHorizontalHall(int previousRoomCenterX, int currentRoomCenterX, int yPos, TETile[][] world, TETile terrain) {
        int hallStart = Math.min(previousRoomCenterX, currentRoomCenterX);
        int hallEnd = Math.max(previousRoomCenterX, currentRoomCenterX);

        if (hallStart == hallEnd) { return; }
        int hallDistance = hallEnd - hallStart + 1;
        int xPos = hallStart;

        // starting from old room center,  horizontally add one tile at a time until the currentCenter
        for (int i = 0; i < hallDistance; i++) {
            if (world[xPos + i][yPos] != Tileset.FLOOR) {
                world[xPos + i][yPos] = terrain;
            }
        }
    }

    /* draws a vertical hallway of type terrain from previous midpoint to current midpoint*/
    private void drawVerticlalHall(int previousRoomCenterY, int currentRoomCenterY, int xPos, TETile[][] world, TETile terrain) {
        int hallStart = Math.min(previousRoomCenterY, currentRoomCenterY);
        int hallEnd = Math.max(previousRoomCenterY, currentRoomCenterY);

        if (hallStart == hallEnd) { return; }
        int hallHeight = hallEnd - hallStart + 1;
        int yPos = Math.min(previousRoomCenterY, currentRoomCenterY);

        for (int i = 0; i < hallHeight; i++) {
            if (world[xPos][yPos + i] != Tileset.FLOOR) {
                world[xPos][yPos + i] = terrain;
            }
        }
    }


    //* draws a hallway from midpoint of previous room to current room
    private void drawLHallway(Room previousRoom, Room currentRoom, TETile[][] world) {
        int hallPath = RANDOM.nextInt(2); // random int representing the inital direction of L hallway. 0 = horizontal L drawn, 1 = vertical L drawn
        drawSmallerL(previousRoom, currentRoom, hallPath, world, Tileset.WALL);
        drawMiddleL(previousRoom, currentRoom, hallPath, world, Tileset.FLOOR);
        drawLargerL(previousRoom, currentRoom, hallPath, world, Tileset.WALL);
    }

    /* return false if new room parameters are out of bounds */
    private boolean outOfBounds(Pos newRoom, int roomWidth, int roomHeight) {
        if (newRoom.x + roomWidth > gameWIDTH - 1 || newRoom.y + roomHeight > gameHEIGHT - 1) { return true; }
        return false;
    }

    /* returns true if room is in bounds of window dimensions*/
    private boolean isRoomValid(Pos newRoom, int roomWidth, int roomHeight) {
        if (rooms == null) { return true; }

        // return false if room goes out of bounds
        if (outOfBounds(newRoom, roomWidth, roomHeight)) { return false; }

        // Return false if tempRoom overlapped with previous room
        Room tempRoom = new Room(newRoom, roomWidth, roomHeight);

        for (int i = 0; i < rooms.size(); i++) {
            Room previousRoom = rooms.get(i);
            if (!tempRoom.overlap(previousRoom)) { return false; }
        }

        return true;
    }

    private void drawRoom(Room currentRoom, TETile[][] world) {
        addRow(currentRoom.getWidth(), Tileset.WALL , world, currentRoom.getPosition());
        addColumn(currentRoom.getHeight(), Tileset.WALL, world, currentRoom.getPosition());
        addRow(currentRoom.getWidth(), Tileset.WALL , world, currentRoom.topLeft());
        addColumn(currentRoom.getHeight(), Tileset.WALL, world, currentRoom.bottomRight());
    }

    /* adds random sized room to rooms list and calls draw methods to world*/
    private  void addRoom(TETile[][] world) {
        int length, height;
        Pos p;

        do {
            length = RANDOM.nextInt(7) + 3; // + 3 sets lower bound  above 0 since nextInt only takes upper bound
            height = RANDOM.nextInt(7) + 3;
            p = randomPosition();
        }
        while(!isRoomValid(p, length, height));

        //once room is valid add room to list of rooms
        Room newRoom = new Room(p, length, height);
        rooms.add(newRoom);

        drawRoom(newRoom, world);
        fillFloors(newRoom, world);
    }

    // fills room with floor tiles in rows from the leftmost position
    private void fillFloors(Room newRoom, TETile[][] world) {
        int length, height;
        length = newRoom.getWidth();
        height = newRoom.getHeight();

        // Position calculating the leftmost point of a floors row
        Pos floorPos = new Pos(newRoom.getPosition());
        floorPos.x = floorPos.x + 1;
        floorPos.y = floorPos.y + 1;

        for (int i = 0; i < height - 2; i++) { // iterates height - 2 to account for the 2 tiles of surrounding walls
            addRow(length - 2, Tileset.FLOOR, world, floorPos);
            floorPos.y = floorPos.y + 1;
        }
    }

    /* adds row of tiles to world from the leftmost position to right */
    private  void addRow(int width, TETile terrain, TETile[][] world, Pos p) {
        for (int i = 0; i < width; i++) { world[p.x + i][p.y] = terrain; }
    }

    /* adds vertical tiles to world from the top position downwards */
    private void addColumn(int height, TETile terrain, TETile[][] world, Pos p ) {
           for (int i = 0; i < height; i++) { world[p.x][p.y + i] = terrain; }
    }

     public void generate( TETile[][] world) {
        int worldSize = RANDOM.nextInt(20) + 20; // randomly sets number of rooms between 20 - 40

         // add new rooms to world
         for (int i = 0; i < worldSize; i++) {
             addRoom(world);
         }
         Collections.sort(rooms); // sorts rooms by x Pos left to right

         // connect adjacent rooms with hallways
         for (int i = 0; i < worldSize - 1; i++) {
             drawLHallway(rooms.get(i), rooms.get(i + 1), world);
         }
     }
     public Room getRandomRoom() {
         int i = RANDOM.nextInt(rooms.size());
        return rooms.get(i);
     }

}