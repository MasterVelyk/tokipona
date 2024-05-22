import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class Create {
    // Creates 2 arrow-shaped Shapes, and returns them
   public static Shape arrowShape(Point fromPt, Point toPt) {
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

    public static Shape guyShape(Point point, boolean middle) {
      return guyShape(point.x, point.y, middle);
   }

   public static Shape guyShape(int x, int y, boolean middle) {
      int midX, midY;
      if (middle) {
         midX = x;
         midY = y;
      } else {
         midX = x + 20;
         midY = y + 20;
      }

      // head
      Polygon guyPolygon = new Polygon();
      guyPolygon.addPoint(midX - 1, midY - 5);
      guyPolygon.addPoint(midX - 5, midY - 5);
      guyPolygon.addPoint(midX - 8, midY - 15);

      guyPolygon.addPoint(midX, midY - 20);

      guyPolygon.addPoint(midX + 8, midY - 15);
      guyPolygon.addPoint(midX + 5, midY - 5);
      guyPolygon.addPoint(midX + 1, midY - 5);

      // left arm
      guyPolygon.addPoint(midX + 1, midY - 1);
      guyPolygon.addPoint(midX + 10, midY + 5);
      guyPolygon.addPoint(midX + 1, midY + 1);

      // left leg
      guyPolygon.addPoint(midX + 1, midY + 9);
      guyPolygon.addPoint(midX + 10, midY + 20);
      guyPolygon.addPoint(midX, midY + 11);
      // right leg
      guyPolygon.addPoint(midX - 10, midY + 20);
      guyPolygon.addPoint(midX - 1, midY + 9);

      // right arm
      guyPolygon.addPoint(midX - 1, midY - 1);
      guyPolygon.addPoint(midX - 10, midY + 5);
      guyPolygon.addPoint(midX - 1, midY + 1);

      return guyPolygon;
   }


   public static Shape rockShape(int x, int y, boolean middle) {
      int midX;
      int midY;
      if (middle) {
         midX = x;
         midY = y;
      } else {
         midX = x + 20;
         midY = y + 20;
      }

      Polygon rock = new Polygon();

      rock.addPoint(midX - 20, midY + 20);
      rock.addPoint(midX - 17, midY + 6);
      rock.addPoint(midX - 10, midY - 3);
      rock.addPoint(midX - 7, midY - 15);
      rock.addPoint(midX, midY - 20); // middle
      rock.addPoint(midX + 6, midY - 8);
      rock.addPoint(midX + 15, midY);
      rock.addPoint(midX + 20, midY + 20);

      return rock;
   }

   public static Shape treeShape(int x, int y, boolean middle) {
      return treeShape(x, y, middle, 1);
   }
   
   public static Shape treeShape(int x, int y, boolean middle, int scale) {
      int midX;
      int midY;
      int size = scale;
      if (middle) {
         midX = x;
         midY = y;
      } else {
         midX = x + 20*size;
         midY = y + 20*size;
      }
      
      Polygon tree = new Polygon();

      tree.addPoint(midX, midY-20*size);
      tree.addPoint(midX-6*size, midY-18*size);
      tree.addPoint(midX-8*size, midY-15*size);

      tree.addPoint(midX-13*size, midY-18*size);
      tree.addPoint(midX-18*size, midY-14*size);
      tree.addPoint(midX-13*size, midY-14*size);

      tree.addPoint(midX-20*size, midY-7*size);
      tree.addPoint(midX-20*size, midY);
      tree.addPoint(midX-16*size, midY-5*size);

      tree.addPoint(midX-14*size, midY+4*size);
      tree.addPoint(midX-11*size, midY-3*size);
      tree.addPoint(midX-8*size, midY-6*size);

      tree.addPoint(midX-4*size, midY+4*size);
      tree.addPoint(midX-5*size, midY+20*size);
      tree.addPoint(midX+6*size, midY+20*size);
      tree.addPoint(midX+8*size, midY+5*size);

      tree.addPoint(midX+11*size, midY);
      tree.addPoint(midX+13*size, midY+3*size);
      tree.addPoint(midX+14*size, midY+7*size);

      tree.addPoint(midX+16*size, midY);
      tree.addPoint(midX+20*size, midY+3*size);
      tree.addPoint(midX+20*size, midY-1*size);

      tree.addPoint(midX+16*size, midY-5*size);
      tree.addPoint(midX+20*size, midY-4*size);
      tree.addPoint(midX+16*size, midY-10*size);

      tree.addPoint(midX+12*size, midY-9*size);
      tree.addPoint(midX+4*size, midY-11*size);
      tree.addPoint(midX+8*size, midY-5*size);

      tree.addPoint(midX+2*size, midY-1*size);

      tree.addPoint(midX+8*size, midY-1*size);
      tree.addPoint(midX+4*size, midY+7*size);
      tree.addPoint(midX+2*size, midY+13*size);
      tree.addPoint(midX-1*size, midY+2*size);

      tree.addPoint(midX-6*size, midY-8*size);
      tree.addPoint(midX+4*size, midY-7*size);
      tree.addPoint(midX, midY-11*size);
      tree.addPoint(midX-4*size, midY-14*size);

      return tree;
   }

   private static Point midpoint(Point p1, Point p2) {
      return new Point((int) ((p1.x + p2.x) / 2.0),
            (int) ((p1.y + p2.y) / 2.0));
   }
   
   public static Shape signShape(int x, int y, boolean middle) {
      return placeholder(x, y, middle);
   }
   
   public static Shape fenceShape(int x, int y, boolean middle) {
      return placeholder(x, y, middle);
   }
   
   public static Shape horseShape(int x, int y, boolean middle) {
      return placeholder(x, y, middle);
   }
   
   public static Shape bunnyShape(int x, int y, boolean middle) {
      int midX;
      int midY;
      if (middle) {
         midX = x;
         midY = y;
      } else {
         midX = x + 20;
         midY = y + 20;
      }
      return new Rectangle(midX-10, midY-10, 20, 20);
   }
   
   public static Shape placeholder(int x, int y, boolean middle) {
      int midX;
      int midY;
      if (middle) {
         midX = x;
         midY = y;
      } else {
         midX = x + 19;
         midY = y + 19;
      }
      return new Rectangle(midX-19, midY-19, 38, 38);
   }
}
