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
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addWindowListener(
            new WindowAdapter() {
               public void windowClosing(WindowEvent e) {
                  System.exit(0);
               }
            });
   }

   public void paint(Graphics g) {
      super.paint(g);
      //dialoguePanel.repaint();
   
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(new Color(255, 230, 217));
      g2.fill(Create.guyShape(playerX, playerY, true));
      g2.setColor(Color.WHITE);
   
      if (keyHandler.notebookOpen == true) {
         // draws the notebook
         g2.setColor(new Color(223, 189, 159));
         g2.fillRect(30, 120 - 75, 460, 440);
         g2.fillRect(30, 340 - 75, 460, 60);
         g2.setColor(new Color(255, 239, 222));
         g2.drawRect(30, 120 - 75, 460, 440);
         g2.drawRect(30, 340 - 75, 460, 60);
         g2.drawImage(myNotebook.getPage(myNotebook.openPage).getImage(), 40, 130 - 75, 440, 200, null);
      
         // draws the arrows
         g2.setColor(new Color(178, 128, 79));
         if (myNotebook.openPage > 0) {
            g2.fill(Create.arrowShape(new Point(75, 325 - 80), new Point(35, 325 - 80)));
         }
         if (myNotebook.openPage < myNotebook.pageList.size() - 1) {
            g2.fill(Create.arrowShape(new Point(445, 325 - 80), new Point(485, 325 - 80)));
         }
      
         if (keyHandler.nodePage > 0) {
            g2.fill(Create.arrowShape(new Point(75, 545 - 80), new Point(35, 545 - 80)));
         }
         if (keyHandler.nodePage * 14 + 14 < myMasterlist.seenlist.size()) {
            g2.fill(Create.arrowShape(new Point(445, 545 - 80), new Point(485, 545 - 80)));
         }
      
         g2.setColor(new Color(255, 239, 222));
      
         // draws a circle for each word in the answer
         if (myNotebook.getPage(myNotebook.openPage).completed == false) {
            for (int i = 0; i < myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size(); i++) {
               if (i < keyHandler.guessList.size()) {
                  g2.fillOval(255 - (myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size() * 30)
                        + ((i) * 60), 345 - 75, 50, 50);
                  g2.drawImage(myMasterlist.seenlist.get(keyHandler.guessList.get(i).intValue()).sitelen, 255
                        - (myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size() * 30) + ((i) * 60),
                        345 - 75, 50, 50, null);
               } else {
                  g2.drawOval(255 - (myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size() * 30)
                        + ((i) * 60), 345 - 75, 50, 50);
               }
            }
         } else {
            Color temp = g2.getColor();
            g2.setColor(Color.WHITE);
            for (int i = 0; i < myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size(); i++) {
               g2.fillOval(
                     255 - (myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size() * 30) + ((i) * 60),
                     345 - 75, 50, 50);
               g2.drawImage(myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().get(i).sitelen,
                     255 - (myNotebook.getPage(myNotebook.openPage).getAnswer().getSentence().size() * 30) + ((i) * 60),
                     345 - 75, 50, 50, null);
            }
            g2.setColor(temp);
         }
         // draws a lil node for each word that is seen
         for (int i = keyHandler.nodePage * 14; i < keyHandler.nodePage * 14 + 14; i++) {
            if (i < myMasterlist.seenlist.size()) {
               if (i % 14 < 7) {
                  g2.fillOval(45 + 60 * (i % 14), 415 - 75, 50, 50);
                  g2.drawImage(myMasterlist.seenlist.get(i).sitelen, 45 + 60 * (i % 14), 415 - 75, 50, 50, null);
               } else {
                  g2.fillOval(45 + 60 * ((i % 14) - 7), 475 - 75, 50, 50);
                  g2.drawImage(myMasterlist.seenlist.get(i).sitelen, 45 + 60 * ((i % 14) - 7), 475 - 75, 50, 50,
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
      
         g2.setColor(Color.DARK_GRAY);
      
         int x = 0;
         while (x < SCREEN_WIDTH) {
            if (up || x < 200 || x > 300) {
               Shape rock = Create.rockShape(x, 0, false);
               objectHitboxes.add(rock.getBounds2D());
               g2.fill(rock);
            }
            if (down || x < 200 || x > 300) {
               Shape rock = Create.rockShape(x, SCREEN_HEIGHT - 40 - 100, false);
               objectHitboxes.add(rock.getBounds2D());
               g2.fill(rock);
            }
            x += 40;
         }
      
         int y = 40;
         while (y < SCREEN_HEIGHT - 100 - 50) {
            if (left || y < 200 || y > 300) {
               Shape rock = Create.rockShape(0, y, false);
               objectHitboxes.add(rock.getBounds2D());
               g2.fill(rock);
            }
            if (right || y < 200 || y > 300) {
               Shape rock = Create.rockShape(SCREEN_WIDTH - 40, y, false);
               objectHitboxes.add(rock.getBounds2D());
               g2.fill(rock);
            }
         
            y += 40;
         }
      
         g2.setColor(Color.WHITE);
      
         ArrayList<Shape> shape = new ArrayList<Shape>();
         if (roomX == 0 && roomY == 0) { // welcome room
            // old guy who says "Toki a!"
            shape.add(Create.guyShape(240, 200, true));
            g2.fill(shape.get(shape.size() - 1));
         } else if (roomX == 0 && roomY == -1) { // big tree room
            // big tree
            shape.add(Create.treeShape(260, 180, true, 4));
            g2.fill(shape.get(shape.size() - 1));
         
            // big tree sign
            shape.add(Create.guyShape(300, 285, true));
            g2.fill(shape.get(shape.size() - 1));
         
            // small tree
            shape.add(Create.treeShape(100, 410, true));
            g2.fill(shape.get(shape.size() - 1));
         
            // small tree guy
            shape.add(Create.guyShape(140, 390, true));
            g2.fill(shape.get(shape.size() - 1));
         
            // background small trees
            int[][] xyTree = { { 383, 445 }, { 448, 400 }, { 115, 121 }, { 405, 210 } };
            g2.setColor(Color.LIGHT_GRAY);
            for (int[] xy : xyTree) {
               shape.add(Create.treeShape(xy[0], xy[1], true));
               g2.fill(shape.get(shape.size() - 1));
            }
            g2.setColor(Color.WHITE);
         } else if (roomX == 0 && roomY == 1) { // many tree room
            // trees but many
            int[][] xyTree = { { 98, 153 }, { 140, 85 }, { 301, 154 }, { 407, 401 }, { 173, 433 }, { 419, 104 },
                  { 358, 245 }, { 248, 401 }, { 270, 281 }, { 95, 377 }, { 362, 451 }, { 334, 354 } };
            g2.setColor(Color.LIGHT_GRAY);
            for (int[] xy : xyTree) {
               shape.add(Create.treeShape(xy[0], xy[1], true));
               g2.fill(shape.get(shape.size() - 1));
            }
            g2.setColor(Color.WHITE);
         
            // tree guy
            shape.add(Create.guyShape(120, 300, true));
            g2.fill(shape.get(shape.size() - 1));
         } else if (roomX == -1 && roomY == -1) { // zoo room
            // horizontal row
            g2.setColor(Color.LIGHT_GRAY);
            for (int i = 0; i < 6; i++) {
               shape.add(Create.fenceShape(50 + (40 * i), 170, false));
               g2.fill(shape.get(shape.size() - 1));
            }
            // upper vertical
            for (int i = 0; i < 4; i++) {
               shape.add(Create.fenceShape(290, 50 + 40 * i, false));
               g2.fill(shape.get(shape.size() - 1));
            }
            // lower vertical
            for (int i = 0; i < 7; i++) {
               shape.add(Create.fenceShape(170, 210 + 40 * i, false));
               g2.fill(shape.get(shape.size() - 1));
            }
            g2.setColor(Color.WHITE);
         
            // sheep
            int[][] xySheep = { { 70, 100 }, { 140, 90 }, { 195, 110 }, { 106, 59 }, { 246, 76 } };
            for (int[] xy : xySheep) {
               shape.add(Create.lambShape(xy[0], xy[1], false));
               g2.fill(shape.get(shape.size() - 1));
            }
         
            // sign 1
            shape.add(Create.signShape(355, 190, true));
            g2.fill(shape.get(shape.size() - 1));
            // sign 2
            shape.add(Create.signShape(235, 360, true));
            g2.fill(shape.get(shape.size() - 1));
         } else if (roomX == -1 && roomY == 0) { // horse room
            // eating horce
            shape.add(Create.eatingHorseShape(160, 265, true));
            g2.fill(shape.get(shape.size() - 1));
         
            shape.add(Create.horseShape(125, 180, true));
            g2.fill(shape.get(shape.size() - 1));
         
            // smaller horce
            int[][] xyHorse = { { 95, 100 }, { 177, 444 }, { 410, 406 }, { 404, 93 }, { 445, 151 } };
            g2.setColor(Color.LIGHT_GRAY);
            for (int[] xy : xyHorse) {
               shape.add(Create.horseShape(xy[0], xy[1], true, 1));
               g2.fill(shape.get(shape.size() - 1));
            }
            g2.setColor(Color.WHITE);
         
            // guy talking about big horse
            shape.add(Create.guyShape(220, 180, true));
            g2.fill(shape.get(shape.size() - 1));
         
            // guy talking about big horse's eating habits
            shape.add(Create.guyShape(220, 320, true));
            g2.fill(shape.get(shape.size() - 1));
         } else if (roomX == -1 && roomY == 1) { // graveyard
            // graves
            g2.setColor(Color.LIGHT_GRAY);
            int[][] xyGrave = { { 394, 385 }, { 374, 211 }, { 122, 215 }, { 177, 300 }, { 460, 80 } };
            for (int[] xy : xyGrave) {
               shape.add(Create.graveShape(xy[0], xy[1], true));
               g2.fill(shape.get(shape.size() - 1));
            }
            g2.setColor(Color.WHITE);
         
            // dead guy guy
            shape.add(Create.guyShape(215, 360, true));
            g2.fill(shape.get(shape.size() - 1));
         
            // dead guy
            shape.add(Create.deadGuyShape(235, 400, true));
            g2.fill(shape.get(shape.size() - 1));
         
            // dead tree guy
            shape.add(Create.guyShape(186, 122, true));
            g2.fill(shape.get(shape.size() - 1));
         
            // dead tree
            shape.add(Create.deadTreeShape(226, 162, true));
            g2.fill(shape.get(shape.size() - 1));
         
            // live tree
            int[][] xyTree = { { 80, 80 }, { 75, 395 }, { 128, 455 }, { 445, 455 } };
            g2.setColor(Color.LIGHT_GRAY);
            for (int[] xy : xyTree) {
               shape.add(Create.treeShape(xy[0], xy[1], true));
               g2.fill(shape.get(shape.size() - 1));
            }
            g2.setColor(Color.WHITE);
         } else if (roomX == 1 && roomY == -1) { // kids room
            // adult you talk to
            shape.add(Create.guyShape(373, 300, true));
            g2.fill(shape.get(shape.size() - 1));
         
            // kids
            int[][] xyKids = { { 97, 435 }, { 195, 386 }, { 113, 237 }, { 358, 445 }, { 367, 382 }, { 458, 452 },
                  { 437, 396 }, { 463, 275 }, { 469, 185 }, { 415, 219 }, { 363, 136 }, { 430, 96 }, { 301, 82 },
                  { 301, 142 }, { 192, 194 }, { 205, 133 }, { 158, 84 }, { 116, 146 }, { 112, 238 }, { 125, 358 },
                  { 84, 188 }, { 90, 73 }, { 435, 341 }, { 174, 465 }, { 427, 162 }, { 393, 66 }, { 251, 103 },
                  { 250, 224 }, { 315, 268 }, { 340, 208 } };
            g2.setColor(Color.LIGHT_GRAY);
            for (int[] xy : xyKids) {
               shape.add(Create.childShape(xy[0], xy[1], true));
               g2.fill(shape.get(shape.size() - 1));
            }
            g2.setColor(Color.WHITE);
         } else if (roomX == 1 && roomY == 0) { // food room
            // big food guy
            shape.add(Create.eatGuyShape(332, 177, true, 2));
            g2.fill(shape.get(shape.size() - 1));
         
            // guy talking about big food guy
            shape.add(Create.guyShape(382, 206, true));
            g2.fill(shape.get(shape.size() - 1));
         
            // small food guy
            shape.add(Create.eatGuyShape(310, 331, true));
            g2.fill(shape.get(shape.size() - 1));
         } else if (roomX == 1 && roomY == 1) { // no man room
            // fences
            g2.setColor(Color.LIGHT_GRAY);
            for (int i = 0; i < 8; i++) {
               shape.add(Create.fenceShape(40 + (40 * i), 320, false));
               g2.fill(shape.get(shape.size() - 1));
            }
            for (int i = 0; i < 7; i++) {
               shape.add(Create.fenceShape(320, 40 + (40 * i), false));
               g2.fill(shape.get(shape.size() - 1));
            }
            g2.setColor(Color.WHITE);
         
            // signs
            shape.add(Create.signShape(235, 290, true));
            g2.fill(shape.get(shape.size() - 1));
            shape.add(Create.signShape(290, 235, true));
            g2.fill(shape.get(shape.size() - 1));
         
            // laba
            g2.setColor(Color.RED);
            g2.fill(Create.lavaShape());
         
            g2.setColor(Color.LIGHT_GRAY);
            shape.add(Create.deadGuyShape(95, 150, true));
            g2.fill(shape.get(shape.size() - 1));
            g2.setColor(Color.WHITE);
         }
      
         for (Shape s : shape) {
            objectHitboxes.add(s.getBounds2D());
         }
         shape.clear();
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
         } else if (roomX == 0 && roomY == -1) { // big tree room
            if (playerX > 260 && playerX < 360 && playerY > 225 && playerY < 365) { // big tree guy interact zone
               interactable = true;
               currentEvent = 1;
            } else if (playerX > 100 && playerX < 190 && playerY > 330 && playerY < 450) { // small tree guy interact
                                                                                           // zone
               interactable = true;
               currentEvent = 3;
            } else {
               interactable = false;
            }
         } else if (roomX == 0 && roomY == 1) { // many tree room
            if (playerX > 80 && playerX < 160 && playerY > 240 && playerY < 360) { // many tree guy interact zone
               interactable = true;
               currentEvent = 2;
            } else {
               interactable = false;
            }
         } else if (roomX == -1 && roomY == 0) { // horse room
            if (playerX > 170 && playerX < 270 && playerY > 120 && playerY < 240) { // big horse
               interactable = true;
               currentEvent = 8;
            } else if (playerX > 170 && playerX < 270 && playerY > 260 && playerY < 380) { // eating horse
               interactable = true;
               currentEvent = 9;
            } else {
               interactable = false;
            }
         } else if (roomX == -1 && roomY == -1) { // zoo room
            if (playerX > 305 && playerX < 405 && playerY > 130 && playerY < 250) { // soweli interact zone
               interactable = true;
               currentEvent = 4;
            } else if (playerX > 185 && playerX < 285 && playerY > 300 && playerY < 420) { // ala soweli sign interact
                                                                                           // zone
               interactable = true;
               currentEvent = 5;
            } else {
               interactable = false;
            }
         } else if (roomX == -1 && roomY == 1) { // graveyard
            if (playerX > 165 && playerX < 265 && playerY > 300 && playerY < 420) { // dead guy guy
               interactable = true;
               currentEvent = 6;
            } else if (playerX > 136 && playerX < 236 && playerY > 62 && playerY < 182) { // dead tree guy
               interactable = true;
               currentEvent = 7;
            } else {
               interactable = false;
            }
         } else if (roomX == 1 && roomY == -1) { // children
            if (playerX > 323 && playerX < 423 && playerY > 250 && playerY < 350) {
               interactable = true;
               currentEvent = 10;
            } else {
               interactable = false;
            }
         } else if (roomX == 1 && roomY == 0) { // food
            if (playerX > 332 && playerX < 432 && playerY > 156 && playerY < 256) { // guy talking about big food guy
               interactable = true;
               currentEvent = 11;
            } else if (playerX > 260 && playerX < 360 && playerY > 281 && playerY < 381) { // small food guy
               interactable = true;
               currentEvent = 12;
            } else {
               interactable = false;
            }
         } else if (roomX == 1 && roomY == 1) { // no go place
            if (playerX > 185 && playerX < 285 && playerY > 240 && playerY < 340) { // sign 1
               interactable = true;
               currentEvent = 13;
            } else if (playerX > 240 && playerX < 340 && playerY > 185 && playerY < 285) { // sign 2
               interactable = true;
               currentEvent = 14;
            } else {
               interactable = false;
            }
         }
      
         // get the right dialogue
         if (interactable == true) {
            if (dialoguePanel.displayDialogue == false) {
               dialoguePanel.dialogueSentence = eventHandler.runEvent(currentEvent);
               dialoguePanel.displayDialogue = true;
               dialoguePanel.repaint();
            }
         } else {
            if (dialoguePanel.displayDialogue == true) {
               dialoguePanel.repaint();
            }
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
