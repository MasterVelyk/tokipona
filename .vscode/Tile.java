public class Tile {
    public int xPos, yPos;
    List<Object> entities = new ArrayList<>(); //Replace "Object" with class Entity after Entity has been created
    public Tile(int xPosition, int yPosition) {
        xPos = xPosition;
        yPos = yPosition;
    }
    
}
