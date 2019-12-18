package Edit;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

public class ShapeTool {

//	 Area shape = new Area(new Rectangle(1, 1, 1, 1));
//	    shape.add(new Area(new Rectangle(1, 1, 1, 1)));
//	    shape.subtract(new Area(new Rectangle(1, 1, 1, 1)));
//	    shape.intersect(new Area(new Rectangle(1, 1, 1, 1)));
//	    shape.exclusiveOr(new Area(new Rectangle(1, 1, 1, 1)));
//	    System.out.println(shape.intersects(new Rectangle(1, 1, 1, 1)));
	    
	public static Point2D getCenterPoint(Shape s) {
		Rectangle2D bound = s.getBounds2D();
		double centerX = bound.getCenterX();
		double centerY = bound.getCenterY();
		return new Point2D.Double(centerX, centerY);
	}

	public static Shape getCompound(Shape[] shapes) {
		Path2D path = new Path2D.Float();
		for(int i=0; i<shapes.length; i++) {
			path.append(shapes[i], false);
		}
		return path;
	}
	
	public static Shape getCompound(Vector<Shape> shapes) {
		Path2D path = new Path2D.Float();
		for(Shape s : shapes) {
			path.append(s, false);
		}
		return path;
	}
	
	public static Shape getSubtract(Shape target, Shape[] shapes) {
		Area a = new Area(target);
		for(int i=0; i<shapes.length; i++) {
			a.subtract(new Area(shapes[i]));
		}
		return AffineTransform.getTranslateInstance(0,0).createTransformedShape(a);
	}
	
	public static Shape getSubtract(Shape target, Vector<Rectangle> shapes) {
		Area a = new Area(target);
		for(Rectangle s : shapes) {
			a.subtract(new Area(s));
		}
		return AffineTransform.getTranslateInstance(0,0).createTransformedShape(a);
	}
	
	public static Shape getIntersect(Shape s1, Shape s2) {
		Area a = new Area(s1);
		a.intersect(new Area(s2));
		return AffineTransform.getTranslateInstance(0,0).createTransformedShape(a);
	}
	
	public static boolean isIntersect(Shape s1, Shape s2) {
		Area a = new Area(s1);
		a.intersect(new Area(s2));
		return !a.isEmpty();
	}
	
//	public static boolean isInclude(Shape s1, Shape s2) {
//		Path2D path = new Path2D.Float();
//		path.append(s1, false);
//		path.append(s2, false);
//		Area compound = new Area(path);
//		Area original = new Area(s1);
//		return compound.equals(original);
//	}
}
