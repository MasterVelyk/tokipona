import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Word {
    protected BufferedImage sitelen = null;
    private String toki;
    private String english;
    private boolean known = false;
    private boolean seen = false;

    public Word(String tokiPonaWord) {
      toki = tokiPonaWord;
      try {
            sitelen = ImageIO.read(new File(tokiPonaWord+".jpg"));
        } catch (IOException e) {
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
