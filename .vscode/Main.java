import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.util.ArrayList;
public class Main extends Canvas implements KeyListener  {
    public void keyPressed(KeyEvent e) {

     }
     public void keyTyped(KeyEvent key) {

     }
     public void keyReleased(KeyEvent key) {
     
     }
    public Main() {
        JFrame frame = new JFrame("mah pona");
        frame.add(this);
        frame.setSize(1000,750);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addWindowListener(
           new WindowAdapter() { 
              public void windowClosing(WindowEvent e) {System.exit(0);} 
           });
    }
    public static void main(String[] args) throws Exception {
        new Main();
    }
}