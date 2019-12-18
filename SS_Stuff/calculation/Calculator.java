package calculation;

import java.awt.geom.Point2D;

public class Calculator {
	
	// Dictionary
	// distanceLineNPoint(Point2D lineP1, Point2D lineP2, Point2D p)
	// computeRotationAngle(Point2D center, Point2D previous, Point2D current)
	// getAngle(Point p1, Point p2)
	
	public static double distanceLineNPoint(Point2D lineP1, Point2D lineP2, Point2D p) {
		double xDelta = lineP2.getX() - lineP1.getX();
		double yDelta = lineP2.getY() - lineP1.getY();
		double u = ((p.getX() - lineP1.getX()) * xDelta + (p.getY() - lineP1.getY()) * yDelta) / (xDelta * xDelta + yDelta * yDelta);
		Point2D.Double closestPoint;
		if (u < 0) {closestPoint = new Point2D.Double(lineP1.getX(), lineP1.getY());}
		else if (u > 1) {closestPoint = new Point2D.Double(lineP2.getX(), lineP2.getY());}
		else {closestPoint = new Point2D.Double(Math.round(lineP1.getX() + u * xDelta), Math.round(lineP1.getY() + u * yDelta));}
		return Point2D.distance(closestPoint.x, closestPoint.y, p.getX(), p.getY());
	}
	
	public static double computeRotationAngle(Point2D center, Point2D previous, Point2D current) {
		double startAngle = Math.toDegrees(Math.atan2(center.getX()-previous.getX(), center.getY()-previous.getY()));
		double endAngle = Math.toDegrees(Math.atan2(center.getX()-current.getX(), center.getY()-current.getY()));
		double rotationAngle = startAngle-endAngle;
		if (rotationAngle < 0) {rotationAngle += 360;}
		return rotationAngle;
	}
	
	public static double getAngle(Point2D p1, Point2D p2) {
		double angle = Math.toDegrees(Math.atan2(p1.getY() - p2.getY(), p1.getX() - p2.getX()));
	    if(angle < 0){angle += 360;}
	    return angle;
	}
}
