import java.util.ArrayList;

public class EventHandler {
    // holds all the events
    private Masterlist myList;
    protected boolean hasNextLine = false;
    protected int myLine = -1;

    public EventHandler(Masterlist list) {
        myList = list;
    }

    public ArrayList<Integer> runEvent(int event) {
      if (hasNextLine = false) {
         myLine = -1;
        }
      myLine+=1;
      ArrayList<Integer> dialogue = new ArrayList<Integer>();
        // old man dialogue
        if (event == 0) {
            if (myLine == 0) {
                dialogue.add(119);
                dialogue.add(0);
                hasNextLine = false;
            }
        }
        return dialogue;
    }
}