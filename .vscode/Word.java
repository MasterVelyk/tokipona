import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Word {
    protected BufferedImage sitelen = null;
    private String toki;
    private String guess;

    public Word(String tokiPonaWord) {
        toki = tokiPonaWord;
        guess = "";
        try {
            sitelen = ImageIO.read(getClass().getResource("/images/"+tokiPonaWord+".png"));
        } catch (IOException e) {
        }
    }

    public void setGuess(String g) {
        guess = g;
    }

    public String getToki() {
        return toki;
    }

    public String getGuess() {
        return guess;
    }
    
    public int compareTo(Word a) {
      return this.getToki().compareTo(a.getToki());
    }
}
