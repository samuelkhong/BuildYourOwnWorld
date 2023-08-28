package byog.Core;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.Core.MapGen.Pos;
import java.io.Serializable;


public class Player implements Serializable{
    public TETile playerIcon = Tileset.PLAYER;
    public Pos playerPos;

    private static final long serialVersionUID = 45498234744734234L;

    // initalize starting position of player
    Player(Room startRoom) {
        playerPos = new Pos(startRoom.midPoint());
    }


    // Updates player position. checks if input char is valid direction and then calls check for is valid tile. If so, updates player position to new pos
    public void updatePlayerPosition(char movementInput, TETile[][] world) {
        switch (movementInput) {
            case 'w': {
                // creates temp Pos of exepected updated position
                Pos tempPos = new Pos(playerPos);
                moveUp(tempPos);
                // if the temp up position is valid, reset current player pos to floor and update player position to new pos
                if (isValidPos(tempPos, world)) {
                    WorldUpdater.resetOldPosition(playerPos, world); // clears old player position
                    moveUp(playerPos);
                }
                break;
            }
            case 's': {
                Pos tempPos = new Pos(playerPos);
                moveDown(tempPos);

                if (isValidPos(tempPos, world)) {
                    WorldUpdater.resetOldPosition(playerPos, world);
                    moveDown(playerPos);
                }
                break;
            }
            case 'a': {
                Pos tempPos = new Pos(playerPos);
                moveLeft(tempPos);

                if (isValidPos(tempPos, world)) {
                    WorldUpdater.resetOldPosition(playerPos, world);
                    moveLeft(playerPos);
                }
                break;
            }
            case 'd': {
                Pos tempPos = new Pos(playerPos);
                moveRight(tempPos);

                if (isValidPos(tempPos, world)) {
                    WorldUpdater.resetOldPosition(playerPos, world);
                    moveRight(playerPos);
                }
                break;
            }
        }
    }

    // takes current position
    public boolean isValidPos(Pos newPos, TETile[][] world) {

        return world[newPos.getX()][newPos.getY()].isWalkable();
    }

    // changes the y Pos by + 1 and x pos
    private void moveUp(Pos p) {
        p.setY(p.getY() + 1);
    }

    private void moveDown(Pos p) {
        p.setY(p.getY() - 1);
    }

    private void moveLeft(Pos p) {
        p.setX(p.getX() - 1);
    }

    private void moveRight(Pos p) {
        p.setX(p.getX() + 1);
    }



}