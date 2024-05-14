import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("removal")
public class KeyHandler implements KeyListener, MouseListener, MouseMotionListener {
    protected boolean upPressed = false;
    protected boolean downPressed = false;
    protected boolean rightPressed = false;
    protected boolean leftPressed = false;
    protected boolean notebookOpen = false;
    public boolean interact = false;
    public boolean checkGuess = false;
    public int changePage = 0;
    public int changeNodePage = 0;
    public int nodePage = 0;
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
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
               interact = true;
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

        if ((e.getX() <= 100) && (e.getY()-20 <= 100)) {
            notebookOpen = !(notebookOpen);
            guessList.clear();
        }
    }

    public void mousePressed(MouseEvent e) {
        if (notebookOpen == true) {
            if (e.getY()-20 > 405 && e.getY()-20 < 455) {
                guessList.add(new Integer((e.getX()-45)/60+(nodePage*14)));
            }
            if (e.getY()-20 > 465 && e.getY()-20 < 515) {
               guessList.add(new Integer(7+(e.getX()-45)/60+(nodePage*14)));
            }
            else if (e.getX() > 30 && e.getX() < 75 && e.getY()-20 > 325 && e.getY()-20 < 350) {
               changePage = -1;
            }
            else if (e.getX() > 440 && e.getX() < 485 && e.getY()-20 > 325 && e.getY()-20 < 350) {
               changePage = 1;
            }
            else if (e.getX() > 30 && e.getX() < 75 && e.getY()-20 > 545 && e.getY()-20 < 570) {
               changeNodePage = -1;
            }
            else if (e.getX() > 440 && e.getX() < 485 && e.getY()-20 > 545 && e.getY()-20 < 570) {
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