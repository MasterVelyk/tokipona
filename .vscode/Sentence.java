import java.util.ArrayList;

public class Sentence {
    private ArrayList<Word> words = new ArrayList<Word>();

    public Sentence(ArrayList<Word> w) {
        words = w;
    }
    
    public Sentence() {
    }

    public Sentence(Word w) {
        words.add(w);
    }

    public ArrayList<Word> getSentence() {
        return words;
    }

    public Word get(int index) {
        return words.get(index);
    }

    public void add(Word w) {
        words.add(w);
    }
    
    public void clear() {
      words.clear();
    }
    
    public boolean equals(Sentence other) {
      for (int i = 0; i < this.getSentence().size(); i++) {
         if (i < this.getSentence().size() && i < other.getSentence().size()) { 
            if (!(this.getSentence().get(i).getToki().equals(other.getSentence().get(i).getToki()))) {
               return false;
            }
         }
         else {
            return false;
         }
      }
      return true;
    }
}
