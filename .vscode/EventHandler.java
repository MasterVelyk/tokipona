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
         // toki a
            dialogue.add(119); //toki
            dialogue.add(0); // a
            pageAns.add(Masterlist.masterlist[119]);
            pageAns.add(Masterlist.masterlist[0]);
            myNotebook.addPage(new NotebookPage(new Sentence(pageAns), "toki"));
        } else if (event == 1) {
         // kasi suli li pona
         dialogue.add(23); // kasi
         pageAns.add(Masterlist.masterlist[23]);
         dialogue.add(44); // li
         pageAns.add(Masterlist.masterlist[44]);
         dialogue.add(110); // suli
         pageAns.add(Masterlist.masterlist[110]);
         myNotebook.addPage(new NotebookPage(new Sentence(pageAns), "kasi"));
        } else if (event == 2) {
        // kasi mute a
         dialogue.add(23); // kasi
         pageAns.add(Masterlist.masterlist[23]);
         dialogue.add(44); // li
         pageAns.add(Masterlist.masterlist[44]);
         dialogue.add(68); // mute
         pageAns.add(Masterlist.masterlist[68]);
         myNotebook.addPage(new NotebookPage(new Sentence(pageAns), "mute"));
        } else if (event == 3) {
         // kasi li lili
         dialogue.add(23); // kasi
         pageAns.add(Masterlist.masterlist[23]);
         dialogue.add(44); // li
         pageAns.add(Masterlist.masterlist[44]);
         dialogue.add(45); // lili
         pageAns.add(Masterlist.masterlist[45]);
         myNotebook.addPage(new NotebookPage(new Sentence(pageAns), "lili"));
        } else if (event == 4) {
            // soweli
            dialogue.add(109); // soweli
            pageAns.add(Masterlist.masterlist[109]);
            myNotebook.addPage(new NotebookPage(new Sentence(pageAns), "soweli"));
        } else if (event == 5) {
            // ala soweli
            dialogue.add(2); // ala
            dialogue.add(109); // soweli
        } else if (event == 6) {
            // jan li moli
            dialogue.add(17); //jan
            dialogue.add(44); // li
            dialogue.add(62); // moli
        } else if (event == 7) {
            // kasi li moli
            dialogue.add(23); // kasi
            dialogue.add(44); // li
            dialogue.add(62); // moli
        } else if (event == 8) {
            // soweli li suli
            dialogue.add(109); // soweli
            dialogue.add(44); // li
            dialogue.add(110); // suli
        }  else if (event == 9) {
            // soweli suli li moku
            dialogue.add(109); // soweli
            dialogue.add(110); // suli
            dialogue.add(44); // li
            dialogue.add(61); // moku
        }

        for (int i = 0; i < dialogue.size(); i++) {
         myList.seeWord(dialogue.get(i).intValue());
        }
        return dialogue;
    }
}
