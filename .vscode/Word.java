public class Word {
    private String toki;
    private String english;
    private boolean known = false;
    private boolean seen = false;

    public Word (String tokiPonaWord) {
        toki = tokiPonaWord;
    }

    public void setEnglish(String g) {
        english = g;
     }

    public String getToki() {
        return toki;
    }

    public String getEnglish() {
        return english;
    }

    public boolean seeWord() {
        if (seen == true) {
            return false;
        }
        seen = true;
        return true;
    }

    // returns true if the user is learning the word for the first time
    // returns false if the user already knew it
    public boolean learnWord() {
        if (known == true) {
            return false;
        }
        known = true;
        return true;
    }
}
