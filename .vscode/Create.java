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

      Point midPoint = new Point((int) ((fromPt.x + toPt.x) / 2.0), (int) ((fromPt.y + toPt.y) / 2.0));

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
      return guyShape(point.x, point.y, middle, 0.5);
   }

   public static Shape guyShape(int x, int y, boolean middle) {
      return guyShape(x, y, true, 1);
   }

   public static Shape childShape(int x, int y, boolean middle) {
      return guyShape(x, y, true, 0.5);
   }

   public static Shape guyShape(int x, int y, boolean middle, double scale) {
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
      guyPolygon.addPoint((int) (midX - scale), (int) (midY - 5 * scale));
      guyPolygon.addPoint((int) (midX - 5 * scale), (int) (midY - 5 * scale));
      guyPolygon.addPoint((int) (midX - 8 * scale), (int) (midY - 15 * scale));

      guyPolygon.addPoint(midX, (int) (midY - 20 * scale));

      guyPolygon.addPoint((int) (midX + 8 * scale), (int) (midY - 15 * scale));
      guyPolygon.addPoint((int) (midX + 5 * scale), (int) (midY - 5 * scale));
      guyPolygon.addPoint((int) (midX + scale), (int) (midY - 5 * scale));

      // left arm
      guyPolygon.addPoint((int) (midX + scale), (int) (midY - scale));
      guyPolygon.addPoint((int) (midX + 10 * scale), (int) (midY + 5 * scale));
      guyPolygon.addPoint((int) (midX + scale), (int) (midY + scale));

      // left leg
      guyPolygon.addPoint((int) (midX + scale), (int) (midY + 9 * scale));
      guyPolygon.addPoint((int) (midX + 10 * scale), (int) (midY + 20 * scale));
      guyPolygon.addPoint(midX, (int) (midY + 11 * scale));
      // right leg
      guyPolygon.addPoint((int) (midX - 10 * scale), (int) (midY + 20 * scale));
      guyPolygon.addPoint((int) (midX - scale), (int) (midY + 9 * scale));

      // right arm
      guyPolygon.addPoint((int) (midX - scale), (int) (midY - scale));
      guyPolygon.addPoint((int) (midX - 10 * scale), (int) (midY + 5 * scale));
      guyPolygon.addPoint((int) (midX - scale), (int) (midY + scale));

      return guyPolygon;
   }

   public static Shape deadGuyShape(int x, int y, boolean middle) {
      return guyShape(x, y, middle);
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
         midX = x + 20 * size;
         midY = y + 20 * size;
      }

      Polygon tree = new Polygon();
      tree.addPoint(midX, midY - 20 * size);
      tree.addPoint(midX - 6 * size, midY - 18 * size);
      tree.addPoint(midX - 8 * size, midY - 15 * size);

      tree.addPoint(midX - 13 * size, midY - 18 * size);
      tree.addPoint(midX - 18 * size, midY - 14 * size);
      tree.addPoint(midX - 13 * size, midY - 14 * size);

      tree.addPoint(midX - 20 * size, midY - 7 * size);
      tree.addPoint(midX - 20 * size, midY);
      tree.addPoint(midX - 16 * size, midY - 5 * size);

      tree.addPoint(midX - 14 * size, midY + 4 * size);
      tree.addPoint(midX - 11 * size, midY - 3 * size);
      tree.addPoint(midX - 8 * size, midY - 6 * size);

      tree.addPoint(midX - 4 * size, midY + 4 * size);
      tree.addPoint(midX - 5 * size, midY + 20 * size);
      tree.addPoint(midX + 6 * size, midY + 20 * size);
      tree.addPoint(midX + 8 * size, midY + 5 * size);

      tree.addPoint(midX + 11 * size, midY);
      tree.addPoint(midX + 13 * size, midY + 3 * size);
      tree.addPoint(midX + 14 * size, midY + 7 * size);

      tree.addPoint(midX + 16 * size, midY);
      tree.addPoint(midX + 20 * size, midY + 3 * size);
      tree.addPoint(midX + 20 * size, midY - 1 * size);

      tree.addPoint(midX + 16 * size, midY - 5 * size);
      tree.addPoint(midX + 20 * size, midY - 4 * size);
      tree.addPoint(midX + 16 * size, midY - 10 * size);

      tree.addPoint(midX + 12 * size, midY - 9 * size);
      tree.addPoint(midX + 4 * size, midY - 11 * size);
      tree.addPoint(midX + 8 * size, midY - 5 * size);

      tree.addPoint(midX + 2 * size, midY - 1 * size);

      tree.addPoint(midX + 8 * size, midY - 1 * size);
      tree.addPoint(midX + 4 * size, midY + 7 * size);
      tree.addPoint(midX + 2 * size, midY + 13 * size);
      tree.addPoint(midX - 1 * size, midY + 2 * size);

      tree.addPoint(midX - 6 * size, midY - 8 * size);
      tree.addPoint(midX + 4 * size, midY - 7 * size);
      tree.addPoint(midX, midY - 11 * size);
      tree.addPoint(midX - 4 * size, midY - 14 * size);

      return tree;
   }

   public static Shape deadTreeShape(int x, int y, boolean middle) {
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
      tree.addPoint(midX - 16, midY + 17);
      tree.addPoint(midX - 18, midY + 6);

      tree.addPoint(midX - 14, midY + 17);

      tree.addPoint(midX, midY + 20);
      tree.addPoint(midX - 12, midY + 16);

      tree.addPoint(midX - 6, midY + 8);

      tree.addPoint(midX + 4, midY + 4);
      tree.addPoint(midX + 20, midY + 5);
      tree.addPoint(midX + 20, midY - 6);
      tree.addPoint(midX + 5, midY - 8);

      tree.addPoint(midX, midY - 11);
      tree.addPoint(midX - 5, midY - 14);
      tree.addPoint(midX + 6, midY - 18);

      tree.addPoint(midX - 10, midY - 16);

      tree.addPoint(midX - 14, midY - 4);
      tree.addPoint(midX - 9, midY - 13);

      tree.addPoint(midX - 7, midY - 2);
      tree.addPoint(midX - 6, midY - 13);

      tree.addPoint(midX - 1, midY - 8);
      tree.addPoint(midX + 7, midY - 4);
      tree.addPoint(midX + 13, midY - 2);
      tree.addPoint(midX + 2, midY + 1);

      tree.addPoint(midX - 8, midY + 6);

      return tree;
   }

   public static Shape signShape(int x, int y, boolean middle) {
      int midX;
      int midY;
      if (middle) {
         midX = x;
         midY = y;
      } else {
         midX = x + 20;
         midY = y + 20;
      }

      Polygon sign = new Polygon();
      sign.addPoint(midX - 15, midY - 20);
      sign.addPoint(midX + 15, midY - 20);
      sign.addPoint(midX + 15, midY + 3);
      sign.addPoint(midX + 3, midY + 3);

      sign.addPoint(midX + 3, midY + 20);
      sign.addPoint(midX - 3, midY + 20);

      sign.addPoint(midX - 3, midY + 3);
      sign.addPoint(midX - 15, midY + 3);
      sign.addPoint(midX - 15, midY - 20);

      return sign;
   }

   public static Shape fenceShape(int x, int y, boolean middle) {
      int midX;
      int midY;
      if (middle) {
         midX = x;
         midY = y;
      } else {
         midX = x + 20;
         midY = y + 20;
      }

      Polygon fence = new Polygon();
      fence.addPoint(midX - 12, midY + 17);
      fence.addPoint(midX - 12, midY + 20);
      fence.addPoint(midX - 17, midY + 20);
      fence.addPoint(midX - 17, midY + 17);
      fence.addPoint(midX - 20, midY + 17);
      fence.addPoint(midX - 20, midY + 12);
      fence.addPoint(midX - 17, midY + 12);

      fence.addPoint(midX - 17, midY - 12);
      fence.addPoint(midX - 20, midY - 12);
      fence.addPoint(midX - 20, midY - 17);
      fence.addPoint(midX - 17, midY - 17);
      fence.addPoint(midX - 17, midY - 20);
      fence.addPoint(midX - 12, midY - 20);
      fence.addPoint(midX - 12, midY - 17);

      fence.addPoint(midX + 12, midY - 17);
      fence.addPoint(midX + 12, midY - 20);
      fence.addPoint(midX + 17, midY - 20);
      fence.addPoint(midX + 17, midY - 17);

      fence.addPoint(midX + 20, midY - 17);
      fence.addPoint(midX + 20, midY - 12);
      fence.addPoint(midX - 12, midY - 12);
      fence.addPoint(midX - 12, midY + 12);

      fence.addPoint(midX - 9, midY + 12);
      fence.addPoint(midX - 9, midY - 12);
      fence.addPoint(midX - 5, midY - 12);
      fence.addPoint(midX - 5, midY + 12);

      fence.addPoint(midX - 2, midY + 12);
      fence.addPoint(midX - 2, midY - 12);
      fence.addPoint(midX + 2, midY - 12);
      fence.addPoint(midX + 2, midY + 12);

      fence.addPoint(midX + 5, midY + 12);
      fence.addPoint(midX + 5, midY - 12);
      fence.addPoint(midX + 9, midY - 12);
      fence.addPoint(midX + 9, midY + 12);

      fence.addPoint(midX + 12, midY + 12);
      fence.addPoint(midX + 12, midY - 12);
      fence.addPoint(midX + 17, midY - 12);
      fence.addPoint(midX + 17, midY + 12);

      fence.addPoint(midX + 20, midY + 12);
      fence.addPoint(midX + 20, midY + 17);
      fence.addPoint(midX + 17, midY + 17);
      fence.addPoint(midX + 17, midY + 20);
      fence.addPoint(midX + 12, midY + 20);
      fence.addPoint(midX + 12, midY + 17);

      return fence;
   }

   public static Shape horseShape(int x, int y, boolean middle) {
      int midX;
      int midY;
      int scale = 2;
      if (middle) {
         midX = x;
         midY = y;
      } else {
         midX = x + 20 * scale;
         midY = y + 20 * scale;
      }

      Polygon horse = new Polygon();
      horse.addPoint(midX - 20 * scale, midY - 4 * scale);
      horse.addPoint(midX - 20 * scale, midY + 5 * scale);
      horse.addPoint(midX - 18 * scale, midY + 18 * scale);
      horse.addPoint(midX - 16 * scale, midY + 18 * scale);
      horse.addPoint(midX - 17 * scale, midY + 7 * scale);
      horse.addPoint(midX - 14 * scale, midY + 2 * scale);
      horse.addPoint(midX - 13 * scale, midY + 4 * scale);

      horse.addPoint(midX - 15 * scale, midY + 9 * scale);
      horse.addPoint(midX - 12 * scale, midY + 19 * scale);
      horse.addPoint(midX - 10 * scale, midY + 19 * scale);
      horse.addPoint(midX - 12 * scale, midY + 9 * scale);
      horse.addPoint(midX - 10 * scale, midY + 2 * scale);

      horse.addPoint(midX - 4 * scale, midY + 4 * scale);
      horse.addPoint(midX - scale, midY + 4 * scale);

      horse.addPoint(midX, midY + 13 * scale);
      horse.addPoint(midX - scale, midY + 17 * scale);
      horse.addPoint(midX, midY + 20 * scale);
      horse.addPoint(midX + 2 * scale, midY + 20 * scale);
      horse.addPoint(midX + scale, midY + 18 * scale);
      horse.addPoint(midX + 2 * scale, midY + 6 * scale);

      horse.addPoint(midX + 3 * scale, midY + 17 * scale);
      horse.addPoint(midX + 4 * scale, midY + 20 * scale);
      horse.addPoint(midX + 7 * scale, midY + 20 * scale);
      horse.addPoint(midX + 4 * scale, midY + 16 * scale);
      horse.addPoint(midX + 7 * scale, midY + 3 * scale);

      horse.addPoint(midX + 8 * scale, midY - 5 * scale);
      horse.addPoint(midX + 11 * scale, midY - 10 * scale);
      horse.addPoint(midX + 14 * scale, midY - 9 * scale);
      horse.addPoint(midX + 17 * scale, midY - 6 * scale);
      horse.addPoint(midX + 20 * scale, midY - 7 * scale);
      horse.addPoint(midX + 18 * scale, midY - 12 * scale);
      horse.addPoint(midX + 17 * scale, midY - 17 * scale);
      horse.addPoint(midX + 17 * scale, midY - 20 * scale);
      horse.addPoint(midX + 15 * scale, midY - 17 * scale);
      horse.addPoint(midX + 11 * scale, midY - 17 * scale);
      horse.addPoint(midX + 8 * scale, midY - 15 * scale);

      horse.addPoint(midX - scale, midY - 11 * scale);
      horse.addPoint(midX - 15 * scale, midY - 10 * scale);

      return horse;
   }

   public static Shape eatingHorseShape(int x, int y, boolean middle) {
      int midX;
      int midY;
      int scale = 2;
      if (middle) {
         midX = x;
         midY = y;
      } else {
         midX = x + 20 * scale;
         midY = y + 20 * scale;
      }

      Polygon horse = new Polygon();
      horse.addPoint(midX - 20 * scale, midY - 4 * scale);
      horse.addPoint(midX - 20 * scale, midY + 5 * scale);
      horse.addPoint(midX - 18 * scale, midY + 18 * scale);
      horse.addPoint(midX - 16 * scale, midY + 18 * scale);
      horse.addPoint(midX - 17 * scale, midY + 7 * scale);
      horse.addPoint(midX - 14 * scale, midY + 2 * scale);
      horse.addPoint(midX - 13 * scale, midY + 4 * scale);

      horse.addPoint(midX - 15 * scale, midY + 9 * scale);
      horse.addPoint(midX - 12 * scale, midY + 19 * scale);
      horse.addPoint(midX - 10 * scale, midY + 19 * scale);
      horse.addPoint(midX - 12 * scale, midY + 9 * scale);
      horse.addPoint(midX - 10 * scale, midY + 2 * scale);

      horse.addPoint(midX - 4 * scale, midY + 4 * scale);
      horse.addPoint(midX - scale, midY + 4 * scale);

      horse.addPoint(midX, midY + 13 * scale);
      horse.addPoint(midX - scale, midY + 17 * scale);
      horse.addPoint(midX, midY + 20 * scale);
      horse.addPoint(midX + 2 * scale, midY + 20 * scale);
      horse.addPoint(midX + scale, midY + 18 * scale);
      horse.addPoint(midX + 2 * scale, midY + 6 * scale);

      horse.addPoint(midX + 3 * scale, midY + 17 * scale);
      horse.addPoint(midX + 4 * scale, midY + 20 * scale);
      horse.addPoint(midX + 7 * scale, midY + 20 * scale);
      horse.addPoint(midX + 4 * scale, midY + 16 * scale);
      horse.addPoint(midX + 8 * scale, midY + 3 * scale);

      horse.addPoint(midX + 12 * scale, midY + 7 * scale);
      horse.addPoint(midX + 12 * scale, midY + 13 * scale);
      horse.addPoint(midX + 15 * scale, midY + 18 * scale);
      horse.addPoint(midX + 18 * scale, midY + 19 * scale);
      horse.addPoint(midX + 18 * scale, midY + 13 * scale);
      horse.addPoint(midX + 19 * scale, midY + 12 * scale);
      horse.addPoint(midX + 18 * scale, midY + 10 * scale);
      horse.addPoint(midX + 20 * scale, midY + 7 * scale);
      horse.addPoint(midX + 17 * scale, midY + 6 * scale);
      horse.addPoint(midX + 16 * scale, midY - 1 * scale);
      horse.addPoint(midX + 11 * scale, midY - 8 * scale);
      horse.addPoint(midX + 4 * scale, midY - 12 * scale);

      horse.addPoint(midX - 1 * scale, midY - 10 * scale);
      horse.addPoint(midX - 13 * scale, midY - 11 * scale);
      horse.addPoint(midX - 15 * scale, midY - 10 * scale);

      return horse;
   }

   public static Shape lambShape(int x, int y, boolean middle) {
      return placeholder(x, y, middle);
   }

   public static Shape graveShape(int x, int y, boolean middle) {
      int midX;
      int midY;
      if (middle) {
         midX = x;
         midY = y;
      } else {
         midX = x + 19;
         midY = y + 19;
      }

      Polygon grave = new Polygon();
      grave.addPoint(midX + 20, midY + 20);
      grave.addPoint(midX - 20, midY + 20);
      grave.addPoint(midX - 16, midY + 15);
      grave.addPoint(midX - 16, midY - 9);
      grave.addPoint(midX - 8, midY - 20);

      grave.addPoint(midX + 8, midY - 20);
      grave.addPoint(midX + 16, midY - 9);
      grave.addPoint(midX + 16, midY + 15);

      return grave;
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
      return new Rectangle(midX - 19, midY - 19, 38, 38);
   }
}
