import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Word {
    protected BufferedImage sitelen = null;
    private String toki;
    private String english;
    private boolean known = false;

    public Word(String tokiPonaWord) {
        toki = tokiPonaWord;
        try {
            sitelen = ImageIO.read(new File(".vscode/images/" + tokiPonaWord + ".jpg"));
        } catch (IOException e) {
            System.out.println(".vscode/images/" + tokiPonaWord + ".jpg");
        }
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

    // returns true if the user is learning the word for the first time
    // returns false if the user already knew it
    public boolean learnWord() {
        if (known == true) {
            return false;
        }
        known = true;
        return true;
    }

    public int compareTo(Word a) {
        return this.getToki().compareTo(a.getToki());
    }
}
