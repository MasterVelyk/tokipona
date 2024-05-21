import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
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
      int midX;
      int midY;
      if (middle) {
         midX = x;
         midY = y;
      } else {
         midX = x + 20;
         midY = y + 20;
      }
      
      Polygon tree = new Polygon();

      tree.addPoint(midX, midY-20);
      tree.addPoint(midX-6, midY-18);
      tree.addPoint(midX-8, midY-15);

      tree.addPoint(midX-13, midY-18);
      tree.addPoint(midX-18, midY-14);
      tree.addPoint(midX-13, midY-14);

      tree.addPoint(midX-20, midY-7);
      tree.addPoint(midX-20, midY);
      tree.addPoint(midX-16, midY-5);

      tree.addPoint(midX-14, midY+4);
      tree.addPoint(midX-11, midY-3);
      tree.addPoint(midX-8, midY-6);

      tree.addPoint(midX-4, midY+4);
      tree.addPoint(midX-5, midY+20);
      tree.addPoint(midX+6, midY+20);
      tree.addPoint(midX+8, midY+5);

      tree.addPoint(midX+11, midY);
      tree.addPoint(midX+13, midY+3);
      tree.addPoint(midX+14, midY+7);

      tree.addPoint(midX+16, midY);
      tree.addPoint(midX+20, midY+3);
      tree.addPoint(midX+20, midY-1);

      tree.addPoint(midX+16, midY-5);
      tree.addPoint(midX+20, midY-4);
      tree.addPoint(midX+16, midY-10);

      tree.addPoint(midX+12, midY-9);
      tree.addPoint(midX+4, midY-11);
      tree.addPoint(midX+8, midY-5);

      tree.addPoint(midX+2, midY-1);

      tree.addPoint(midX+8, midY-1);
      tree.addPoint(midX+4, midY+7);
      tree.addPoint(midX+2, midY+13);
      tree.addPoint(midX-1, midY+2);

      tree.addPoint(midX-6, midY-8);
      tree.addPoint(midX+4, midY-7);
      tree.addPoint(midX, midY-11);
      tree.addPoint(midX-4, midY-14);

      return tree;
   }

   private static Point midpoint(Point p1, Point p2) {
      return new Point((int) ((p1.x + p2.x) / 2.0),
            (int) ((p1.y + p2.y) / 2.0));
   }
}
