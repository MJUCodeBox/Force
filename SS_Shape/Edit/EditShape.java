package Edit;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import calculation.Calculator;

public class EditShape {

	// Dictionary
	// getMovedShapeCenterTo(Shape s, double x, double y)
	// getMovedShape(Shape s, Direction direction, double delta)
	// getRotatedShapeHeadTo(Shape s, Point2D rotatePoint, Point2D headPoint)
	// getScaledShape(Shape s, double scaleX, double scaleY)
	// getUpDownMirrorShape(Shape s)
	// getLeftRightMirrorShape(Shape s)
	
	public static Shape getMovedShapeCenterTo(Shape s, double x, double y) {
		Rectangle2D bound = s.getBounds2D();
		AffineTransform at = new AffineTransform();
		at.translate(x - bound.getCenterX(), y - bound.getCenterY());
		return at.createTransformedShape(s);
	}

	public enum Direction{
		Up(0, -1, 1), Down(0, 1, 1), Left(-1, 0, 1), Right(1, 0, 1), 
		LeftUp(-1, -1, Math.sqrt(2)), LeftDown(-1, 1, Math.sqrt(2)), RightUp(1, -1, Math.sqrt(2)), RightDown(1, 1, Math.sqrt(2));
		
		int xF, yF; double slashF;
		private Direction(int xF, int yF, double slashF) {this.xF=xF; this.yF=yF; this.slashF=slashF;}
		public int getXFactor() {return this.xF;}
		public int getYFactor() {return this.yF;}
		public double getSlashFactor() {return this.slashF;}
	};
	
	public static Shape getMovedShape(Shape s, Direction direction, double delta) {
		AffineTransform at = new AffineTransform();
		delta /= direction.getSlashFactor();
		at.translate(delta*direction.getXFactor(), delta*direction.getYFactor());
		return at.createTransformedShape(s);
	}
	
	public static Shape getMovedShape(Shape s, double dx, double dy) {
		AffineTransform at = new AffineTransform();
		at.translate(dx, dy);
		return at.createTransformedShape(s);
	}
	
	public static Shape getRotatedShapeHeadTo(Shape s, Point2D headPoint) {
		Rectangle2D bound = s.getBounds2D();
		Point2D rotateCenterPoint = ShapeTool.getCenterPoint(s);
		Point2D shapeHeadPoint = new Point2D.Double(bound.getX()+bound.getWidth()/2, bound.getY());
		double angle = Calculator.computeRotationAngle(rotateCenterPoint, shapeHeadPoint, headPoint);
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.toRadians(angle), rotateCenterPoint.getX(), rotateCenterPoint.getY());
		return at.createTransformedShape(s);
	}
	
	public static Shape getScaledShape(Shape s, double scaleX, double scaleY) {
		AffineTransform at = new AffineTransform();
		at.scale(scaleX, scaleY);
		return at.createTransformedShape(s);
	}
	
	public static Shape getRotatedShape(Shape s, double angle) {
		Point2D center = ShapeTool.getCenterPoint(s);
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.toRadians(angle), center.getX(), center.getY());
		return at.createTransformedShape(s);
	}

	public static Shape getRotatedShape(Shape s, double centerX, double centerY, double angle) {
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.toRadians(angle), centerX, centerY);
		return at.createTransformedShape(s);
	}
	
	public static Shape getUpDownMirrorShape(Shape s) {
		AffineTransform at = new AffineTransform();
		at.scale(1, -1);
		return at.createTransformedShape(s);
	}
	
	public static Shape getLeftRightMirrorShape(Shape s) {
		AffineTransform at = new AffineTransform();
		at.scale(-1, 1);
		return at.createTransformedShape(s);
	}
	
}
