import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class NotebookPage {
    private BufferedImage img = null;
    private Sentence answer = new Sentence(); // answer for the page
    private Sentence guess = new Sentence(); // guess for the page
    protected boolean completed = false;

    public NotebookPage(Sentence ans, String image) {
        try {
            img = ImageIO.read(getClass().getResource("/images/" + image + ".png"));
        } catch (IOException e) {
            System.out.println(e);
        }

        answer = ans;
    }

    public NotebookPage() {
        try {
            img = ImageIO.read(getClass().getResource("/images/sitelen" + ".png"));
        } catch (IOException e) {
        }
        ArrayList<Word> answerArray = new ArrayList<Word>();
        //answerArray.add(new Word("sitelen"));
        answer = new Sentence(answerArray);
    }

    public BufferedImage getImage() {
        return img;
    }

    public Sentence getAnswer() {
        return answer;
    }

    public Sentence getGuess() {
        return guess;
    }

    public boolean checkGuess() {
        if (answer.equals(guess)) {
            completed = true;
            return true;
        }
        return false;
    }

    public void writeGuess(ArrayList<Word> g) {
        guess.clear();
        for (int i = 0; i < answer.getSentence().size(); i++) {
            if (i < g.size()) {
                guess.add(g.get(i));
            }
        }
    }
}