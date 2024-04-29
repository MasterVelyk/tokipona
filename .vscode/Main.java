import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Main extends Canvas implements Runnable {
   private static final int SCREEN_WIDTH = 520;
   private static final int SCREEN_HEIGHT = 720;
   private int playerX = 100;
   private int playerY = 100;
   private int playerSpeed = 10;
   private Notebook myNotebook = new Notebook();

   Thread gameThread;
   KeyHandler keyHandler = new KeyHandler();
   MouseHandler mouseHandler = new MouseHandler();

   public Main() {
      startGameThread();
      JFrame frame = new JFrame("ma pona");
      frame.add(this);
      frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
      frame.getContentPane().setBackground(Color.DARK_GRAY);
      frame.setVisible(true);
      frame.setResizable(false);
      super.addKeyListener(keyHandler);
      super.addMouseListener(mouseHandler);
      frame.setFocusable(true);
      frame.addWindowListener(
            new WindowAdapter() {
               public void windowClosing(WindowEvent e) {
                  System.exit(0);
               }
            });
   }

   public void paint(Graphics g) {
      super.paint(g);

      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.CYAN);
      g2.fillOval(playerX, playerY, 40, 40);
      g2.fillRect(10, 10, 80, 80);

      if (mouseHandler.notebookOpen == true) {
         g2.drawRect(100, 100, 100, 100);
         g2.drawImage(myNotebook.getPage(0).getImage(), 100, 100, null);
      }
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
         double drawInterval = 1000000000 / 24;
         double nextDrawInterval = System.nanoTime() + drawInterval;

         update();
         repaint();

         try {
            double remainingTime = nextDrawInterval - System.nanoTime();
            remainingTime = remainingTime / 1000000;
            if (remainingTime < 0) {
               remainingTime = 0;
            }
            Thread.sleep((long) remainingTime);
         } catch (Exception e) {
         }
      }
   }

   public void update() {
      if (mouseHandler.notebookOpen == false) {
         if (keyHandler.upPressed == true) {
            playerY -= playerSpeed;
            if (playerY <= 100) {
               playerY = 100;
            }
         }
         if (keyHandler.downPressed == true) {
            playerY += playerSpeed;
            if (playerY + 80 >= SCREEN_HEIGHT) {
               playerY = SCREEN_HEIGHT - 80;
            }
         }
         if (keyHandler.leftPressed == true) {
            playerX -= playerSpeed;
            if (playerX <= 0) {
               playerX = 0;
            }
         }
         if (keyHandler.rightPressed == true) {
            playerX += playerSpeed;
            if (playerX + 60 >= SCREEN_WIDTH) {
               playerX = SCREEN_WIDTH - 60;
            }
         }
      }
   }
}