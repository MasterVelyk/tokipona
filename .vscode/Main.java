import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Main extends Canvas implements Runnable {
   private static final int SCREEN_WIDTH = 800;
   private static final int SCREEN_HEIGHT = 800;
   private static boolean notebookOpen = false;
   private int playerX = 100;
   private int playerY = 100;
   private int playerSpeed = 10;

   Thread gameThread;
   KeyHandler keyHandler = new KeyHandler();

   public void mousePressed(MouseEvent e) {

   }

   public void mouseReleased(MouseEvent e) {

   }

   public void mouseExited(MouseEvent e) {

   }

   public void mouseEntered(MouseEvent e) {

   }

   public void mouseClicked(MouseEvent e) {

   }

   public void mouseDragged(MouseEvent e) {

   }

   public void mouseMoved(MouseEvent e) {

   }

   public Main() {
      startGameThread();
      JFrame frame = new JFrame("ma pona");
      frame.add(this);
      frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
      frame.getContentPane().setBackground(Color.DARK_GRAY);
      frame.setVisible(true);
      frame.setResizable(false);
      frame.addKeyListener(keyHandler);

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
      g2.drawOval(playerX, playerY, 40, 40);
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
      if (keyHandler.upPressed == true) {
         playerY -= playerSpeed;
         if (playerY <= 0) {
            playerY = 0;
         }
      }
      if (keyHandler.downPressed == true) {
         playerY += playerSpeed;
         if (playerY + 40 >= SCREEN_HEIGHT) {
            playerY = SCREEN_HEIGHT - 40;
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
         if (playerX + 40 >= SCREEN_WIDTH) {
            playerX = SCREEN_WIDTH - 40;
         }
      }
   }
}