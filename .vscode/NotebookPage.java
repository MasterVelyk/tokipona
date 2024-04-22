import java.util.*;

public class NotebookPage {
    // private String imageLink;
    private String sentenceContext; // context for the situation in the notebook
    private Sentence answer; //answer for the page
    private Sentence guess; //player's guess for the page

    public NotebookPage(Sentence ans, String context) {
        answer = ans;
        sentenceContext = context;
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
        Scanner input = new Scanner(System.in);
        System.out.println("Input a word.");
        

        return null;
    }
}
