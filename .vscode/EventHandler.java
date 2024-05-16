import java.util.ArrayList;

public class EventHandler {
    // holds all the events
    private Masterlist myList;

    public EventHandler(Masterlist list) {
        myList = list;
    }

    public ArrayList<Integer> runEvent(int event) {
      ArrayList<Integer> dialogue = new ArrayList<Integer>();
        // old man dialogue
        if (event == 0) {
                dialogue.add(119);
                dialogue.add(1);
                dialogue.add(119);
                dialogue.add(1);
                dialogue.add(119);
                dialogue.add(1);
                dialogue.add(119);
                dialogue.add(1);
        }
        return dialogue;
    }
}