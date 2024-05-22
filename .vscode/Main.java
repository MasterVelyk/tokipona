import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
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
   private int roomX = 0;
   private int roomY = 0;
   private Notebook myNotebook = new Notebook();
   private Masterlist myMasterlist = new Masterlist();
   private EventHandler eventHandler = new EventHandler(myMasterlist, myNotebook);
   private boolean interactable = false;
   private int currentEvent;
   private DialoguePanel dialoguePanel = new DialoguePanel();
   private ArrayList<Rectangle2D> objectHitboxes = new ArrayList<Rectangle2D>();

   private int offset = 75;

   private Thread gameThread;
   private KeyHandler keyHandler = new KeyHandler();

   public Main() {
      startGameThread();
      JFrame frame = new JFrame("ma pona");
      frame.add(this, BorderLayout.CENTER);
      frame.add(dialoguePanel, BorderLayout.SOUTH);
      this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT - 100));
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

   public void paint(Graphics g) {
      super.paint(g);
      dialoguePanel.repaint();

      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.YELLOW);
      g2.fill(Create.guyShape(playerX, playerY, true));
      g2.setColor(Color.WHITE);

      if (keyHandler.notebookOpen == true) {

         // draws the notebook
         g2.setColor(new Color(223, 189, 159));
         g2.fillRect(30, 120 - offset, 460, 440);
         g2.fillRect(30, 340 - offset, 460, 60);
         g2.setColor(new Color(255, 239, 222));
         g2.drawRect(30, 120 - offset, 460, 440);
         g2.drawRect(30, 340 - offset, 460, 60);
         g2.drawImage(myNotebook.getPage(myNotebook.openPage).getImage(), 40, 130 - offset, 440, 200, null);

         // draws the arrows
         g2.setColor(new Color(178, 128, 79));
         if (myNotebook.pageList.size() > 1) {
            g2.fill(Create.arrowShape(new Point(445, 325 - offset), new Point(485, 325 - offset)));
            g2.fill(Create.arrowShape(new Point(75, 325 - offset), new Point(35, 325 - offset)));
         }
         if (myMasterlist.seenlist.size() > 10) {
            g2.fill(Create.arrowShape(new Point(445, 545 - offset), new Point(485, 545 - offset)));
            g2.fill(Create.arrowShape(new Point(75, 545 - offset), new Point(35, 545 - offset)));
         }
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
         // stores whether the wall should be whole
         boolean left = (roomX == -1) ? true : false;
         boolean right = (roomX == 1) ? true : false;
         boolean up = (roomY == -1) ? true : false;
         boolean down = (roomY == 1) ? true : false;
         
         int x = 0;
         while (x < SCREEN_WIDTH) {
            if (up || x < 200 || x > 300) {
               Shape rock = Create.rockShape(x, 0, false);
               objectHitboxes.add(rock.getBounds2D());
               g2.fill(rock);
            }
            if (down || x < 200 || x > 300) {
               Shape rock = Create.rockShape(x, SCREEN_HEIGHT-40-100, false);
               objectHitboxes.add(rock.getBounds2D());
               g2.fill(rock);
            }
            x += 40;
         }

         int y = 40;
         while (y < SCREEN_HEIGHT-100-50) {
            if (left || y < 200 || y > 300) {
               Shape rock = Create.rockShape(0, y, false);
               objectHitboxes.add(rock.getBounds2D());
               g2.fill(rock);
            }
            if (right || y < 200 || y > 300) {
               Shape rock = Create.rockShape(SCREEN_WIDTH-40, y, false);
               objectHitboxes.add(rock.getBounds2D());
               g2.fill(rock);
            }

            y += 40;
         }

         if (roomX == 0 && roomY == 0) {  // welcome room
            // old guy who says "Toki a!"
            Shape guyVar = Create.guyShape(240, 200, true);
            objectHitboxes.add(guyVar.getBounds2D());
            g2.fill(guyVar);
         } else if (roomX == 0 && roomY == -1) { // big tree room
            // big tree
            Shape bigTree = Create.treeShape(260, 180, true, 4);
            objectHitboxes.add(bigTree.getBounds2D());
            g2.fill(bigTree);
            
            // big tree sign
            Shape guyVar = Create.guyShape(300, 285, true);
            objectHitboxes.add(guyVar.getBounds2D());
            g2.fill(guyVar);
            
            // small tree
            Shape smallTree = Create.treeShape(100, 410, true);
            objectHitboxes.add(smallTree.getBounds2D());
            g2.fill(smallTree);
            
            // small tree guy
            guyVar = Create.guyShape(140, 390, true);
            objectHitboxes.add(guyVar.getBounds2D());
            g2.fill(guyVar);
         } else if (roomX == 0 && roomY == 1) { // many tree room

            // trees but many
            Shape tree1 = Create.treeShape(110, 100, true);
            objectHitboxes.add(tree1.getBounds2D());
            g2.fill(tree1);
            Shape tree2 = Create.treeShape(301, 154, true);
            objectHitboxes.add(tree2.getBounds2D());
            g2.fill(tree2);
            Shape tree3 = Create.treeShape(407, 401, true);
            objectHitboxes.add(tree3.getBounds2D());
            g2.fill(tree3);
            Shape tree4 = Create.treeShape(173, 413, true);
            objectHitboxes.add(tree4.getBounds2D());
            g2.fill(tree4);
            Shape tree5 = Create.treeShape(419, 104, true);
            objectHitboxes.add(tree5.getBounds2D());
            g2.fill(tree5);
            Shape tree6 = Create.treeShape(358, 245, true);
            objectHitboxes.add(tree6.getBounds2D());
            g2.fill(tree6);
            Shape tree7 = Create.treeShape(248, 401, true);
            objectHitboxes.add(tree7.getBounds2D());
            g2.fill(tree7);
            Shape tree8 = Create.treeShape(270, 281, true);
            objectHitboxes.add(tree8.getBounds2D());
            g2.fill(tree8);
            
            // tree guy
            Shape guyVar = Create.guyShape(120, 300, true);
            objectHitboxes.add(guyVar.getBounds2D());
            g2.fill(guyVar);
         } else if (roomX == -1 && roomY == -1) { // zoo room
            // horizontal row
            for (int i = 0; i < 6; i++) {
               Shape fence = Create.fenceShape(50+(40*i), 170, false);
               objectHitboxes.add(fence.getBounds2D());
               g2.fill(fence);
            }
            // upper vertical
            for (int i = 0; i < 4; i++) {
               Shape fence = Create.fenceShape(290, 50+40*i, false);
               objectHitboxes.add(fence.getBounds2D());
               g2.fill(fence);
            }
            // lower vertical
            for (int i = 0; i < 7; i++) {
               Shape fence = Create.fenceShape(170, 210+40*i, false);
               objectHitboxes.add(fence.getBounds2D());
               g2.fill(fence);
            }
            // sheep
            for (int i = 0; i < 3; i++) {
               Shape sheep = Create.lambShape(60+60*i, 100, false);
               objectHitboxes.add(sheep.getBounds2D());
               g2.fill(sheep); 
            }
            // sign 1
            Shape sign = Create.signShape(355, 190, true);
            objectHitboxes.add(sign.getBounds2D());
            g2.fill(sign);
            // sign 2
            sign = Create.signShape(235, 360, true);
            objectHitboxes.add(sign.getBounds2D());
            g2.fill(sign);
         } else if (roomX == -1 && roomY == 0) { // horse room
            // big horce
            Shape horce = Create.horseShape(120, 220, true);
            objectHitboxes.add(horce.getBounds2D());
            g2.fill(horce);
         } else if (roomX == -1 && roomY == 1) { // graveyard
         } else if (roomX == 1 && roomY == -1) { // kids room
         } else if (roomX == 1 && roomY == 0) { // food room
         } else if (roomX == 1 && roomY == 1) { // no man room
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
               playerY = SCREEN_HEIGHT - 135;
               roomY -= 1;
               objectHitboxes.clear();
            }
         }
         if (keyHandler.downPressed == true) {
            playerY += playerSpeed;
            if (playerY + 135 >= SCREEN_HEIGHT) {
               playerY = 0;
               roomY += 1;
               objectHitboxes.clear();
            }
         }
         if (keyHandler.leftPressed == true) {
            playerX -= playerSpeed;
            if (playerX <= 0) {
               playerX = SCREEN_WIDTH;
               roomX -= 1;
               objectHitboxes.clear();
            }
         }
         if (keyHandler.rightPressed == true) {
            playerX += playerSpeed;
            if (playerX >= SCREEN_WIDTH) {
               playerX = 0;
               roomX += 1;
               objectHitboxes.clear();
            }
         }
         if (roomX == 0 && roomY == 0) { // entrance room
            if (playerX > 200 && playerX < 280 && playerY > 140 && playerY < 260) { // old guy interact zone
               interactable = true;
               currentEvent = 0;
            } else {
               interactable = false;
            }
         }
         else if (roomX == 0 && roomY == -1) { // big tree room
            if (playerX > 260 && playerX < 360 && playerY > 225 && playerY < 365) { // big tree guy interact zone
               interactable = true;
               currentEvent = 1;
            } else if (playerX > 100 && playerX < 190 && playerY > 330 && playerY < 450) { // small tree guy interact zone
               interactable = true;
               currentEvent = 3;
            } else {
               interactable = false;
            }
         }
         else if (roomX == 0 && roomY == 1) { // many tree room
            if (playerX > 80 && playerX < 160 && playerY > 240 && playerY < 360) { // many tree guy interact zone
               interactable = true;
               currentEvent = 2;
            } else {
               interactable = false;
            }
         }
         else if (roomX == -1 && roomY == 0) { //horse room

         }
         else if (roomX == -1 && roomY == -1) { // zoo room
            if (playerX > 305 && playerX < 405 && playerY > 130 && playerY < 250) { // soweli interact zone
               interactable = true;
               currentEvent = 4;
            } else if (playerX > 185 && playerX < 285 && playerY > 300 && playerY < 420) { // ala soweli sign interact zone
               interactable = true;
               currentEvent = 5;
            } else {
               interactable = false;
            }
         }
         else if (roomX == -1 && roomY == 1) {
            
         }
         // get the right dialogue
         if (interactable == true) {
            if (dialoguePanel.displayDialogue == false) {
               dialoguePanel.dialogueSentence = eventHandler.runEvent(currentEvent);
               dialoguePanel.displayDialogue = true;
            }
         } else {
            dialoguePanel.displayDialogue = false;
         }

         // push out of obstcles
         boolean inShape = false;
         Rectangle2D playerBox = Create.guyShape(playerX, playerY, true).getBounds2D();
         for (int i = 0; i < objectHitboxes.size(); i++) {
            if (playerBox.intersects(objectHitboxes.get(i))) {
               inShape = true;
            }
         }
         if (inShape) {
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
      } else {
         if (keyHandler.checkValid == true) {
            for (int i = 0; i < keyHandler.guessList.size(); i++) {
               if (myMasterlist.seenlist.size() < keyHandler.guessList.get(i).intValue()) {
                  keyHandler.guessList.remove(i);
                  i -= 1;
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
