public class World {
    private int roomNumber = 0; //Stores which room is currently open
    public World() {
        changeRoom(0);
        drawRoom();
    }

    public boolean changeRoom(int number) { //Changes the current room number
        if (number < 0) {
            return false;
        }
        roomNumber = number;
        return true;
    }
    
    public void drawRoom() {
        clearRoom();
        if (roomNumber == 0) { //Draws room 0
        }
        else if (roomNumber == 1) { //Draws room 1
        }
    }

    public void clearRoom() {
        //clears the map
    }
}
