import java.util.ArrayList;

public class EventHandler {
    // holds all the events
    private Masterlist myList;
    private Notebook myNotebook;

    public EventHandler(Masterlist list, Notebook book) {
        myList = list;
        myNotebook = book;
    }

    public ArrayList<Integer> runEvent(int event) {
      ArrayList<Integer> dialogue = new ArrayList<Integer>();
      ArrayList<Word> pageAns = new ArrayList<Word>();
        // old man dialogue
        if (event == 0) {
                dialogue.add(119);
                dialogue.add(0);
                myList.seeWord(119);
                myList.seeWord(0);
                pageAns.add(Masterlist.masterlist[119]);
                pageAns.add(Masterlist.masterlist[119]);
                myNotebook.addPage(new NotebookPage(new Sentence(pageAns), "toki"));
        }
        return dialogue;
    }
}