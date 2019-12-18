package Shape;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class SSEllipse {

	public static Shape getShape(Point2D.Double p1, Point2D.Double p2) {
		return new Ellipse2D.Double(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p1.x-p2.x), Math.abs(p1.y-p2.y));
	}
	
	public static Shape getShape(double x, double y, double w, double h) {
		return new Ellipse2D.Double(x, y, w, h);
	}
	
}
