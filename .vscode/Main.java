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
    private int rectX = 0;

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

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.CYAN);
        g2.fillRect(rectX, 10, 30, 30);
    }

    public static void main(String[] args) throws Exception {
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
        rectX+=1;
    }
}