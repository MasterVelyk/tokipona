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
        if (event == 0) { // toki a
            dialogue.add(119); //toki
            dialogue.add(0); // a
            pageAns.add(Masterlist.masterlist[119]);
            pageAns.add(Masterlist.masterlist[0]);
            myNotebook.addPage(new NotebookPage(new Sentence(pageAns), "toki"));
        } else if (event == 1) { // kasi li suli
            dialogue.add(23); // kasi
            dialogue.add(44); // li
            dialogue.add(110); // suli

            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[23]), "kasi"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[110]), "suli"));

        } else if (event == 2) { // kasi li mute
            dialogue.add(23); // kasi
            dialogue.add(44); // li
            dialogue.add(68); // mute

            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[23]), "kasi"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[68]), "mute"));
        } else if (event == 3) { // kasi li lili
            dialogue.add(23); // kasi
            dialogue.add(44); // li
            dialogue.add(45); // lili

            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[23]), "kasi"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[45]), "lili"));
        } else if (event == 4) { // soweli
            dialogue.add(109); // soweli
            pageAns.add(Masterlist.masterlist[109]);
            myNotebook.addPage(new NotebookPage(new Sentence(pageAns), "soweli"));
        } else if (event == 5) { // ala soweli
            dialogue.add(2); // ala
            dialogue.add(109); // soweli

            pageAns.add(Masterlist.masterlist[109]);
            myNotebook.addPage(new NotebookPage(new Sentence(pageAns), "soweli"));
        } else if (event == 6) { // jan li moli
            dialogue.add(17); //jan
            dialogue.add(44); // li
            dialogue.add(62); // moli

            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[17]), "jan"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[62]), "moli"));
        } else if (event == 7) { // kasi li moli
            dialogue.add(23); // kasi
            dialogue.add(44); // li
            dialogue.add(62); // moli

            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[23]), "kasi"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[62]), "moli"));
        } else if (event == 8) { // soweli li suli
            dialogue.add(109); // soweli
            dialogue.add(44); // li
            dialogue.add(110); // suli

            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[109]), "soweli"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[110]), "suli"));
        }  else if (event == 9) { // soweli suli li moku
            dialogue.add(109); // soweli
            dialogue.add(110); // suli
            dialogue.add(44); // li
            dialogue.add(61); // moku

            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[109]), "soweli"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[110]), "suli"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[61]), "moku"));
        } else if (event == 10) { // jan lili li mute
            dialogue.add(17); // jan
            dialogue.add(45); // lili
            dialogue.add(44); // li
            dialogue.add(68); //mute

            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[17]), "jan"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[45]), "lili"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[68]), "mute"));
        } else if (event == 11) { // jan li moku suli
            dialogue.add(17); // jan 
            dialogue.add(44); //li
            dialogue.add(61); // moku
            dialogue.add(110); //suli

            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[17]), "jan"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[61]), "moku"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[110]), "suli"));
        } else if (event == 12) { // moku li lili
            dialogue.add(61); // moku
            dialogue.add(44); // li
            dialogue.add(45); // lili

            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[61]), "moku"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[45]), "lili"));
        } else if (event == 13) { // ala jan
            dialogue.add(2); // ala
            dialogue.add(17); // jan

            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[17]), "jan"));
        } else if (event == 14) { // jan moli
            dialogue.add(17);
            dialogue.add(62);

            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[17]), "jan"));
            myNotebook.addPage(new NotebookPage(new Sentence(Masterlist.masterlist[62]), "moli"));
        }

        for (int i = 0; i < dialogue.size(); i++) {
            myList.seeWord(dialogue.get(i).intValue());
        }
        return dialogue;
    }
}
