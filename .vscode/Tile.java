import java.util.*;

public class Tile {
    public int xPos, yPos;
    public String type;
    List<Object> entities = new ArrayList<>(); // Replace "Object" with class Entity after Entity has been created
    List<Object> trees = new ArrayList<>(); // Replace "Object" with class Tree after Tree has been created
    List<Object> houses = new ArrayList<>(); // Replace "Object" with class House after House has been created

    public Tile(int xPosition, int yPosition, String tileType) {
        xPos = xPosition;
        yPos = yPosition;
        type = tileType; // Should be grass, water, or sand
    }

    public String getType() {
        return type;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}