package AStuff;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import Global_KeyListener.Mouse;

public class Camera {

	// User Value
	private static int debugEllipseWH = 10;
	private static Color debugColor = Color.GREEN;
	
	// System Value
	private static Graphics2D nowGraphic;

	// Initialize
	public static void setNowGraphic(Graphics2D nowGraphic) {Camera.nowGraphic = nowGraphic;}
	
	// Get Value Methods
	public static Point2D getRelativeMousePoint() {
		if(Camera.nowGraphic == null) {return null;}
		Point2D absoluteMousePoint =  Mouse.getNowPoint();
		Point2D relativeMousePoint = new Point2D.Double();
		try {Camera.nowGraphic.getTransform().createInverse().transform(absoluteMousePoint, relativeMousePoint);}
		catch (NoninvertibleTransformException e) {e.printStackTrace();}
		return relativeMousePoint;
	}
	
	public static Point2D getRelativePoint(Point2D absolutePoint) {
		Point2D relativePoint = new Point2D.Double();
		try {Camera.nowGraphic.getTransform().createInverse().transform(absolutePoint, relativePoint);}
		catch (NoninvertibleTransformException e) {e.printStackTrace();}
		return relativePoint;
	}
	
	// Debug
	public static void debug(Graphics2D g) {
		Point2D relativeMousePoint = Camera.getRelativeMousePoint();
		g.setColor(Camera.debugColor);
		g.fillOval((int)relativeMousePoint.getX() - debugEllipseWH/2, (int)relativeMousePoint.getY() - debugEllipseWH/2, debugEllipseWH, debugEllipseWH);
	}
}
