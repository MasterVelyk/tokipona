import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Main extends JPanel implements Runnable {
   protected static final int SCREEN_WIDTH = 520;
   protected static final int SCREEN_HEIGHT = 620;
   private int playerX = 240;
   private int playerY = 300;
   private int playerSpeed = 10;
   private int room = 0;
   private Notebook myNotebook = new Notebook();
   private Masterlist myMasterlist = new Masterlist();
   private EventHandler eventHandler = new EventHandler(myMasterlist, myNotebook);
   private boolean interactable = false;
   private int currentEvent;
   private DialoguePanel dialoguePanel = new DialoguePanel();

   private int offset = 75;

   private Thread gameThread;
   private KeyHandler keyHandler = new KeyHandler();

   public Main() {
      startGameThread();
      JFrame frame = new JFrame("ma pona");
      frame.add(this, BorderLayout.CENTER);
      frame.add(dialoguePanel, BorderLayout.SOUTH);
      this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT-100));
      this.setBackground(new Color(115, 115, 115));
      frame.pack();
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

   // Creates 2 arrow-shaped Shapes, and returns them
   public static Shape createArrowShape(Point fromPt, Point toPt) {
      Polygon arrowPolygon = new Polygon();
      arrowPolygon.addPoint(-6, 1);
      arrowPolygon.addPoint(3, 1);
      arrowPolygon.addPoint(3, 3);
      arrowPolygon.addPoint(6, 0);
      arrowPolygon.addPoint(3, -3);
      arrowPolygon.addPoint(3, -1);
      arrowPolygon.addPoint(-6, -1);

      Point midPoint = midpoint(fromPt, toPt);

      double rotate = Math.atan2(toPt.y - fromPt.y, toPt.x - fromPt.x);

      AffineTransform transform = new AffineTransform();
      transform.translate(midPoint.x, midPoint.y);
      double ptDistance = fromPt.distance(toPt);
      double scale = ptDistance / 12.0; // 12 because it's the length of the arrow polygon.
      transform.scale(scale, scale);
      transform.rotate(rotate);

      return transform.createTransformedShape(arrowPolygon);
   }

   public static Shape createGuyShape(int midX, int midY) {
      //head
      Polygon guyPolygon = new Polygon();
      guyPolygon.addPoint(midX-1, midY-5);
      guyPolygon.addPoint(midX-5, midY-5);
      guyPolygon.addPoint(midX-8, midY-15);
      
      guyPolygon.addPoint(midX, midY-20);
      
      guyPolygon.addPoint(midX+8, midY-15);
      guyPolygon.addPoint(midX+5, midY-5);
      guyPolygon.addPoint(midX+1, midY-5);
      
      //left arm
      guyPolygon.addPoint(midX+1, midY-1);
      guyPolygon.addPoint(midX+10, midY+5);
      guyPolygon.addPoint(midX+1, midY+1);
      
      //left leg
      guyPolygon.addPoint(midX+1, midY+9);
      guyPolygon.addPoint(midX+10, midY+20);
      guyPolygon.addPoint(midX, midY+11);
      //right leg
      guyPolygon.addPoint(midX-10, midY+20);
      guyPolygon.addPoint(midX-1, midY+9);
      
      //right arm
      guyPolygon.addPoint(midX-1, midY-1);
      guyPolygon.addPoint(midX-10, midY+5);
      guyPolygon.addPoint(midX-1, midY+1);
    
      return guyPolygon;
   }

   private static Point midpoint(Point p1, Point p2) {
      return new Point((int) ((p1.x + p2.x) / 2.0),
            (int) ((p1.y + p2.y) / 2.0));
   }

   public void paint(Graphics g) {
      super.paint(g);
      dialoguePanel.repaint();

      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.WHITE);
      g2.fillOval(playerX, playerY, 40, 40);

      if (keyHandler.notebookOpen == true) {
         Font f = new Font("Comic Sans MS", Font.BOLD, 200);
         g2.setFont(f);
         
         // draws the notebook
         g2.setColor(new Color(223, 189, 159));
         g2.fillRect(20, 120 - offset, 460, 440);
         g2.fillRect(20, 340 - offset, 460, 60);
         g2.setColor(new Color(255, 239, 222));
         g2.drawRect(20, 120 - offset, 460, 440);
         g2.drawRect(20, 340 - offset, 460, 60);
         g2.drawImage(myNotebook.getPage(myNotebook.openPage).getImage(), 30, 130 - offset, 440, 200, null);

         // draws the arrows
         g2.setColor(new Color(178, 128, 79));
         g2.fill(createArrowShape(new Point(435, 325 - offset), new Point(475, 325 - offset)));
         g2.fill(createArrowShape(new Point(65, 325 - offset), new Point(25, 325 - offset)));
         g2.fill(createArrowShape(new Point(435, 545 - offset), new Point(475, 545 - offset)));
         g2.fill(createArrowShape(new Point(65, 545 - offset), new Point(25, 545 - offset)));
         g2.setColor(new Color(255, 239, 222));

         // draws a circle for each word in the answer
         if (myNotebook.getPage(myNotebook.openPage).completed == false) {
            for (int i = 0; i < myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size(); i++) {
               if (i < keyHandler.guessList.size()) {
                  g2.fillOval(255 - (myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size() * 30)
                        + ((i) * 60), 345 - offset, 50, 50);
                  g2.drawImage(myMasterlist.seenlist.get(keyHandler.guessList.get(i).intValue()).sitelen, 255
                        - (myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size() * 30) + ((i) * 60),
                        345 - offset, 50, 50, null);
               } else {
                  g2.drawOval(255 - (myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size() * 30)
                        + ((i) * 60), 345 - offset, 50, 50);
               }
            }
         } else {
            for (int i = 0; i < myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size(); i++) {
               g2.fillOval(
                     255 - (myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size() * 30) + ((i) * 60),
                     345 - offset, 50, 50);
               g2.drawImage(myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().get(i).sitelen,
                     255 - (myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size() * 30) + ((i) * 60),
                     345 - offset, 50, 50, null);
            }
         }
         // draws a lil node for each word that is seen
         for (int i = keyHandler.nodePage * 14; i < keyHandler.nodePage * 14 + 14; i++) {
            if (i < myMasterlist.seenlist.size()) {
               if (i % 14 < 7) {
                  g2.fillOval(45 + 60 * (i % 14), 415 - offset, 50, 50);
                  g2.drawImage(myMasterlist.seenlist.get(i).sitelen, 45 + 60 * (i % 14), 415 - offset, 50, 50, null);
               } else {
                  g2.fillOval(45 + 60 * ((i % 14) - 7), 475 - offset, 50, 50);
                  g2.drawImage(myMasterlist.seenlist.get(i).sitelen, 45 + 60 * ((i % 14) - 7), 475 - offset, 50, 50,
                        null);
               }
            }
         }
      } else {
         if (room == 0) {
            // old guy who says "Toki!"
            g2.fillOval(240, 200, 40, 40);
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

   public void update() {
      if (keyHandler.notebookOpen == false) {
         if (keyHandler.upPressed == true) {
            playerY -= playerSpeed;
            if (playerY <= 0) {
               playerY = 0;
            }
         }
         if (keyHandler.downPressed == true) {
            playerY += playerSpeed;
            if (playerY + 135 >= SCREEN_HEIGHT) {
               playerY = SCREEN_HEIGHT - 135;
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
            if (playerX + 41 >= SCREEN_WIDTH) {
               playerX = SCREEN_WIDTH - 41;
            }
         }
         if (room == 0) {
            if (playerX > 170 && playerX < 310 && playerY > 130 && playerY < 270) { // old guy interact zone
               interactable = true;
               currentEvent = 0;
            }
            else {
               interactable = false;
            }
         }
         
         //get the right dialogue
         if (interactable == true) {
            if (dialoguePanel.displayDialogue == false) {
               dialoguePanel.dialogueSentence = eventHandler.runEvent(currentEvent);
               dialoguePanel.displayDialogue = true;
            }
         }
         else {
            dialoguePanel.displayDialogue = false;
         }

         // push out of obstcles
         if (room == 0) {
            if (playerX > 200 && playerX < 280 && playerY > 160 && playerY < 240) { // old guy hitbox
               if (keyHandler.upPressed) {
                  playerY += playerSpeed;
               }
               if (keyHandler.downPressed) {
                  playerY -= playerSpeed;
               }
               if (keyHandler.leftPressed) {
                  playerX += playerSpeed;
               }
               if (keyHandler.rightPressed) {
                  playerX -= playerSpeed;
               }
            }
         }
      } else {
         if (keyHandler.checkValid == true) {
            for (int i = 0; i < keyHandler.guessList.size(); i++) {
               if (myMasterlist.seenlist.size() < keyHandler.guessList.get(i).intValue()) {
                  keyHandler.guessList.remove(i);
                  i-=1;
               }
            }
            keyHandler.checkValid = false;
         }
         if (keyHandler.checkGuess == true) {
            ArrayList<Word> tempGuessList = new ArrayList<Word>();
            for (int i = 0; i < keyHandler.guessList.size(); i++) {
               tempGuessList.add(myMasterlist.seenlist.get(keyHandler.guessList.get(i).intValue()));
            }
            myNotebook.getPage(myNotebook.openPage).writeGuess(tempGuessList);
            myNotebook.getPage(myNotebook.openPage).checkGuess();
            keyHandler.checkGuess = false;
            keyHandler.clearGuessList();
         }
         if (keyHandler.changePage == 1) {
            if (myNotebook.openPage + 1 < myNotebook.pageList.size()) {
               myNotebook.openPage += 1;
               keyHandler.clearGuessList();
            }
            keyHandler.changePage = 0;
         } else if (keyHandler.changePage == -1) {
            if (myNotebook.openPage > 0) {
               myNotebook.openPage -= 1;
               keyHandler.clearGuessList();
            }
            keyHandler.changePage = 0;
         }
         if (keyHandler.changeNodePage == 1) {
            if ((keyHandler.nodePage + 1) * 14 < myMasterlist.seenlist.size()) {
               keyHandler.nodePage += 1;
            }
            keyHandler.changeNodePage = 0;
         } else if (keyHandler.changeNodePage == -1) {
            if (keyHandler.nodePage > 0) {
               keyHandler.nodePage -= 1;
            }
            keyHandler.changeNodePage = 0;
         }
      }
   }
}
