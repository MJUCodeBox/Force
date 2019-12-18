package Shape;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class SSRombus {

	public static Shape getShape(Point2D.Double p1, Point2D.Double p2) {
		return makeShape(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p1.x-p2.x), Math.abs(p1.y-p2.y));
	}
	
	public static Shape getShape(double x, double y, double w, double h) {
		return makeShape(x, y, w, h);
	}
	
	public static Shape makeShape(double x, double y, double w, double h) {
		GeneralPath  path = new GeneralPath();
		path.moveTo(x+w/2, y);
		path.lineTo(x+w, y+h/2);
		path.lineTo(x+w/2, y+h);
		path.lineTo(x, y+h/2);
		path.closePath();
		return path;
	}
	
	public static Shape getShape(double x, double y, double w, double h, double hF) {
		return makeShape(x, y, w, h, hF);
	}
	
	private static Shape makeShape(double x, double y, double w, double h, double hF) {
		GeneralPath  path = new GeneralPath();
		path.moveTo(x+w/2, y);
		path.lineTo(x+w, y+h/2+hF);
		path.lineTo(x+w/2, y+h);
		path.lineTo(x, y+h/2+hF);
		path.closePath();
		return path;
	}
}
