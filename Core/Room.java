package byog.Core;

import byog.Core.MapGen.Pos;
import java.io.Serializable;

public class Room implements  Comparable<Room> {
    private int width;
    private int height;
    private Pos position;


    public Room (Pos inputPosition, int inputWidth, int inputHeight ) {
        position = inputPosition;
        width = inputWidth;
        height = inputHeight;
    }

    public Pos getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return  height;
    }

    @Override
    // Used to order list of rooms by x coordinate. returns negative: left, returns position: right,
    public int compareTo(Room other) {
        int compare = this.midPoint().getX() - other.midPoint().getX();
        if (compare == 0) { // if rooms are at the same xPos, judge based on yPos
            compare = this.midPoint().getY() - other.midPoint().getY();
        }

        return compare;
    }

    //returns the top left position of the current room
    public Pos topLeft() {
        Pos newPos = new Pos();
        newPos.setY(position.getY() + height - 1);
        newPos.setX(position.getX());

        return newPos;
    }
    // returns the bottom right coordinates of the current room
    public Pos bottomRight() {
        Pos newPos = new Pos();
        newPos.setX(position.getX() + width - 1);
        newPos.setY(position.getY());

        return newPos;
    }

    // returns the center position of the room
    public Pos midPoint() {
        Pos middle = new Pos(this.position);
        middle.setX(width / 2 + this.position.getX());
        middle.setY(height / 2 + this.position.getY());

        return middle;
    }

    // returns the center of the bottom position
    public Pos bottomMiddle() {
        Pos newPos = new Pos();
        newPos.setX(width / 2 + this.position.getX());
        newPos.setY(position.getY());

        return newPos;
    }

    // compares this room with other room to see if there is any overlap
    public boolean overlap(Room otherRoom) {

        if (       this.getPosition().getX() <= otherRoom.bottomRight().getX()
                && this.bottomRight().getX() >= otherRoom.getPosition().getX()
                && this.getPosition().getY() <= otherRoom.topLeft().getY()
                && this.topLeft().getY() >= otherRoom.getPosition().getY())
        { return  false; }
        return true;
    }


}