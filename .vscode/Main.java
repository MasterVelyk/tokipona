import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Main extends Canvas implements KeyListener {
    private static int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 750;
    private static boolean notebookOpen = false;

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_X) {
            notebookOpen = true;
        }
    }

    public void keyTyped(KeyEvent key) {

    }

    public void keyReleased(KeyEvent key) {

    }

    public Main() {
        JFrame frame = new JFrame("ma pona");
        frame.add(this);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
    }

    public void paint(Graphics g) {
        if (notebookOpen == true) {
            g.setColor(new Color(207, 187, 118));
            g.fillRect(SCREEN_WIDTH / 2 - SCREEN_WIDTH / 20, SCREEN_HEIGHT / 2 - SCREEN_HEIGHT / 20, SCREEN_WIDTH / 10,
                    SCREEN_HEIGHT / 10);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("fuck");
        new Main();
    }
}