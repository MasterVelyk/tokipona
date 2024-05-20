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

public class DialoguePanel extends JPanel {
   public boolean displayDialogue = false;
   public ArrayList<Integer> dialogueSentence = new ArrayList<Integer>();
   
   public DialoguePanel() {
      this.setPreferredSize(new Dimension(Main.SCREEN_WIDTH, 100));
      this.setBackground(new Color(115, 115, 115));
   }
   
   
   public void paint(Graphics g) {
      super.paint(g);
      Graphics2D g2 = (Graphics2D) g;
      
      if (displayDialogue) {
         for (int i = 0; i < dialogueSentence.size(); i++) {
            g2.drawImage(Masterlist.masterlist[dialogueSentence.get(i).intValue()].sitelen, 100+50*i, 25, 50, 50, null);
         }
      }
      
      g2.setStroke(new BasicStroke(5));
      g2.draw(new Rectangle(10, 10, 80, 80));
      g2.fillOval(25, 45, 10, 10);
      g2.fillOval(45, 45, 10, 10);
      g2.fillOval(65, 45, 10, 10);
   }
}