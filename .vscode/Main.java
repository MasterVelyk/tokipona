import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Main extends JPanel implements Runnable {
   private static final int SCREEN_WIDTH = 520;
   private static final int SCREEN_HEIGHT = 620;
   private int playerX = 100;
   private int playerY = 100;
   private int playerSpeed = 10;
   private Notebook myNotebook = new Notebook();
   protected Masterlist myMasterlist = new Masterlist();

   Thread gameThread;
   KeyHandler keyHandler = new KeyHandler();

   public Main() {
      startGameThread();
      JFrame frame = new JFrame("ma pona");
      frame.add(this);
      this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
      frame.pack();
      frame.getContentPane().setBackground(Color.DARK_GRAY);
      frame.setVisible(true);
      frame.setResizable(false);
      frame.addKeyListener(keyHandler);
      frame.addMouseListener(keyHandler);
      frame.addMouseMotionListener(keyHandler);
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
      g2.setColor(Color.WHITE);
      g2.fillOval(playerX, playerY, 40, 40);
      g2.fillRect(10, 10, 80, 80);
      g2.drawLine(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

      if (keyHandler.notebookOpen == true) {
         g2.setColor(Color.PINK);
         g2.fillRect(20, 120, 460, 440);
         g2.fillRect(20, 340, 460, 60);
         g2.setColor(Color.WHITE);
         g2.drawRect(20, 120, 460, 440);
         g2.drawRect(20, 340, 460, 60);
         g2.drawImage(myNotebook.getPage(0).getImage(), 30, 130, 440, 200, null);
         // draws a circle for each word in the answer
         if (myNotebook.getPage(0).completed == false) {
            for (int i = 0; i < myNotebook.getPage(0).getAnswer().getSentence().size(); i++) {
               if (i < keyHandler.guessList.size()) {
                  g2.fillOval(255 - (myNotebook.getPage(0).getAnswer().getSentence().size() * 30) + ((i) * 60), 345, 50,
                        50);
                  g2.drawImage(myMasterlist.seenlist.get(keyHandler.guessList.get(i).intValue()).sitelen,
                        255 - (myNotebook.getPage(0).getAnswer().getSentence().size() * 30) + ((i) * 60), 345, 50, 50,
                        null);
               } else {
                  g2.drawOval(255 - (myNotebook.getPage(0).getAnswer().getSentence().size() * 30) + ((i) * 60), 345, 50,
                        50);
               }
            }
         } else {
         }
         // draws a lil node for each word that is seen
         for (int i = 0; i < myMasterlist.seenlist.size(); i++) {
            if (i == keyHandler.grabbedWord) {
               g2.fillOval(keyHandler.mouseX - 35, keyHandler.mouseY - 35, 50, 50);
               g2.drawImage(myMasterlist.seenlist.get(i).sitelen, keyHandler.mouseX - 35, keyHandler.mouseY - 35, 50,
                     50,
                     null);
            } else {
               g2.fillOval(25 + 60 * i, 405, 50, 50);
               g2.drawImage(myMasterlist.seenlist.get(i).sitelen, 25 + 60 * i, 405, 50, 50, null);
            }
         }
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
         double drawInterval = 1000000000 / 24; // FPS
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

   public void update() { // Handles user movement
      if (mouseHandler.notebookOpen == false) {
         if (keyHandler.notebookOpen == false) {
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
}