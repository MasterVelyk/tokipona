import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class NotebookPage {
    private BufferedImage img = null;
    private String sentenceContext; // context for the situation in the notebook
<<<<<<< HEAD
    private Sentence answer = new Sentence(); // answer for the page
    private Sentence guess = new Sentence(); //guess for the page
=======
    private Sentence answer; // answer for the page
    private Sentence guess; //guess for the page
>>>>>>> origin/main
    protected boolean completed = false;

    public NotebookPage(Sentence ans, String context) {
        try {
            img = ImageIO.read(new File(".vscode/images/strawberry.jpg"));
        } catch (IOException e) {
            System.out.println(e);
        }

        answer = ans;
        sentenceContext = context;
    }

    public NotebookPage() {
        try {
<<<<<<< HEAD
            img = ImageIO.read(getClass().getResource("/images/soweli"+".png"));
=======
            img = ImageIO.read(getClass().getResource("/images/strawberry"+".jpg"));
>>>>>>> origin/main
        } catch (IOException e) {
            System.out.println(e);
        }
        ArrayList<Word> answerArray = new ArrayList<Word>();
        answerArray.add(new Word("soweli"));
        answerArray.add(new Word("soweli"));
        answerArray.add(new Word("soweli"));
        answer = new Sentence(answerArray);
    }

    public BufferedImage getImage() {
        return img;
    }

    public String getContext() {
        return sentenceContext;
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