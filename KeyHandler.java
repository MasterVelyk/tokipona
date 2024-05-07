import java.awt.event.*;
import java.util.ArrayList;

public class KeyHandler implements KeyListener, MouseListener, MouseMotionListener {
    protected boolean upPressed = false;
    protected boolean downPressed = false;
    protected boolean rightPressed = false;
    protected boolean leftPressed = false;
    protected boolean notebookOpen = false;
    protected int grabbedWord = -1;
    protected int mouseX, mouseY;
    protected ArrayList<Integer> guessList = new ArrayList<Integer>();

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
         }
         else {
           if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
           guessList.clear();
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
        int x = e.getX();
        int y = e.getY()-20;

        if ((x <= 100) && (y <= 100)) {
            notebookOpen = !(notebookOpen);
        }
    }

    public void mousePressed(MouseEvent e) {
      System.out.println("press");
        if (notebookOpen == true) {
         if (mouseX > 25 && mouseX < 75 && mouseY > 405 && mouseY < 455) {
            grabbedWord = 0;
         }  
        }
    }

    @SuppressWarnings("deprecation")
    public void mouseReleased(MouseEvent e) {
      System.out.println("release");
        if (notebookOpen == true) {
         if (grabbedWord != -1) {
            guessList.add((new Integer(grabbedWord)));
         }
         grabbedWord = -1;
        }
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
        mouseX = e.getX();
        mouseY = e.getY()-20;
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY()-20;
    }
}