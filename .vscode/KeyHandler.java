import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("removal")
public class KeyHandler implements KeyListener, MouseListener, MouseMotionListener {
   protected boolean upPressed = false;
   protected boolean downPressed = false;
   protected boolean rightPressed = false;
   protected boolean leftPressed = false;
   protected boolean notebookOpen = false;
   public boolean checkGuess = false;
   public boolean checkValid = false;
   public int changePage = 0;
   public int changeNodePage = 0;
   public int nodePage = 0;
   protected ArrayList<Integer> guessList = new ArrayList<Integer>();

   private int offset = 60;

   public void keyPressed(KeyEvent e) {
      if (notebookOpen == false) {
         if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            upPressed = true;
         }
         if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            downPressed = true;
         }
         if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = true;
         }
         if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = true;
         }
      } else {
         if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            checkGuess = true;
         }
         if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            changePage = -1;
         }
         if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            changePage = 1;
         }
      }
   }

   public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
         upPressed = false;
      }
      if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
         downPressed = false;
      }
      if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
         leftPressed = false;
      }
      if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
         rightPressed = false;
      }
   }

   public void keyTyped(KeyEvent e) {
   
   }

   public void mouseClicked(MouseEvent e) {
      if ((e.getX() <= 100) && (e.getY() >= Main.SCREEN_HEIGHT - offset)) {
         if (notebookOpen) {
            checkGuess = true;
         }
         notebookOpen = !(notebookOpen);
      }
   }

   public void mousePressed(MouseEvent e) {
      if (notebookOpen == true) {
         if (e.getY() - 20 > 350 && e.getY() - 20 < 400) {
            guessList.add(new Integer((e.getX() - 45) / 60 + (nodePage * 14)));
            checkValid = true; 
         }
         if (e.getY() - 20 > 415 && e.getY() - 20 < 465) {
            guessList.add(new Integer(7 + (e.getX() - 45) / 60 + (nodePage * 14)));
            checkValid = true; 
         } else if (e.getX() > 30 && e.getX() < 75 && e.getY() - 20 > 60 && e.getY() - 20 < 275) {
            changePage = -1;
            checkGuess = true;
         } else if (e.getX() > 440 && e.getX() < 485 && e.getY() - 20 > 60 && e.getY() - 20 < 275) {
            changePage = 1;
            checkGuess = true;
         } else if (e.getX() > 30 && e.getX() < 75 && e.getY() - 20 > 335 && e.getY() - 20 < 495) {
            changeNodePage = -1;
         } else if (e.getX() > 440 && e.getX() < 485 && e.getY() - 20 > 335 && e.getY() - 20 < 495) {
            changeNodePage = 1;
         }
      }
   }

   public void mouseReleased(MouseEvent e) {
   }

   public void mouseEntered(MouseEvent e) {
   
      // throw new UnsupportedOperationException("Unimplemented method
      // 'mouseEntered'");
   }

   public void mouseExited(MouseEvent e) {
   
      // throw new UnsupportedOperationException("Unimplemented method
      // 'mouseExited'");
   }

   public void mouseDragged(MouseEvent e) {
   }

   public void mouseMoved(MouseEvent e) {
   }

   public void clearGuessList() {
      guessList.clear();
   }
}