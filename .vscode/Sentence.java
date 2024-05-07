import java.util.ArrayList;

public class Sentence {
    private ArrayList<Word> words;

    public Sentence(ArrayList<Word> w) {
        words = w;
    }

    public ArrayList<Word> getSentence() {
        return words;
    }

    public Word getWord(int index){
        return words.get(index);
    }

    public void addWord(Word w) {
        words.add(w);
    }
}