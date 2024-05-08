import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class NotebookPage {
    private BufferedImage img = null;
    private String sentenceContext; // context for the situation in the notebook
    private Sentence answer; // answer for the page
    private Sentence guess; //guess for the page
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
            img = ImageIO.read(getClass().getResource("/images/strawberry"+".jpg"));
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
            return true;
        }
        return false;
    }

    public Sentence writeGuess() {
        // Scanner input = new Scanner(System.in);
        System.out.println("Input a word.");

        return null;
    }
}