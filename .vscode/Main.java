import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Main extends Canvas implements KeyListener, Runnable {
    private static int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 750;
    private static boolean notebookOpen = false;

    Thread gameThread;

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
        startGameThread();
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
        super.paint(g);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("fuck");
        new Main();
    }
    
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        while (gameThread != null) {
            update();
            repaint();
        }
    }

    public void update() {

    }
}