package calculation;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class AffineMath {

	public static Point2D transformPoint(AffineTransform at, Point2D point)  {
		Point2D.Double resultPoint = new Point2D.Double();
		try {at.transform(point, resultPoint);}catch (Exception e) {e.printStackTrace();}
		return resultPoint;
	}

	public static Point2D rotatePoint(Point2D target, Point2D center, double angle)  {
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.toRadians(angle), center.getX(), center.getY());
		Point2D.Double resultPoint = new Point2D.Double();
		try {at.transform(target, resultPoint);}catch (Exception e) {e.printStackTrace();}
		return resultPoint;
	}
	
}

