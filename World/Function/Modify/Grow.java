package Function.Modify;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;

import AStuff.Message;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.AStuff.Function;
import WorldComponent.AStuff.WorldComponent;

public class Grow extends Function{
	private static final long serialVersionUID = -3314818742633174730L;
	
	// Value
	double scaleX, scaleY;
	public void setGrowScale(double scaleX, double scaleY) {
		this.scaleX=scaleX;
		this.scaleY=scaleY;
	}
	
	// Constructor
	public Grow(WorldComponent master) {super(master);} 

	@Override 
	public void additionalUpdate() {
		Shape scaledShape = EditShape.getScaledShape(master.getShape(), scaleX, scaleY);
		Point2D center = ShapeTool.getCenterPoint(master.getShape());
		master.setShape(EditShape.getMovedShapeCenterTo(scaledShape, center.getX(), center.getY()));
	}
	
	@Override public void processMessage(Message msg) {}
	@Override public void additionalDraw(Graphics2D g) {}
	@Override public void additionalSound() {}
}
