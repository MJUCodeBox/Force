package Shape;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class SSRectangle {

	public static Shape getShape(Point2D masterCenter, Point2D p2) {
		return new Rectangle2D.Double(Math.min(masterCenter.getX(), p2.getX()), Math.min(masterCenter.getY(), p2.getY()),
				Math.abs(masterCenter.getX()-p2.getX()), Math.abs(masterCenter.getY()-p2.getY()));
	}
	
	public static Shape getShape(double x, double y, double w, double h) {
		return new Rectangle2D.Double(x, y, w, h);
	}
	
}
