package Function.forPlayer.tool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import AStuff.Camera;
import AStuff.Message;
import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.AStuff.Function;
import Function.force.Force;
import Function.force.ForceVector;
import Global_KeyListener.Mouse;
import WorldComponent.AStuff.WorldComponent;

public class GravityGun extends Function{
	private static final long serialVersionUID = 1L;
	
	// Value
	private double effectScale = 1.05;
	
	// System Value
	private WorldComponent target = null;
	private ForceVector lastGravity;
	private double centerDX, centerDY;
	
	// Constructor
	public GravityGun(WorldComponent master) {super(master);}

	@Override 
	public void additionalUpdate() {
		if(this.target == null) {
			if(Mouse.mouseLeftPressed) {findTarget();}
			else if(Mouse.mouseRightPressed) {findTarget(); fixTarget(); releaseTarget();}
		}else {
			if(!Mouse.mouseLeftPressed) {releaseTarget();}
			else {moveTarget();}
		}
	}
	
	private void findTarget() {
		Point2D tP = Camera.getRelativePoint(Mouse.lastPressPoint);
		Point2D nowPoint = new Point2D.Double(tP.getX(), tP.getY());
		for(WorldComponent wc : TheWorld.getWorldComponents()) {
			if(wc != master && wc.getShape().contains(nowPoint)) {
				this.target = wc;
				Point2D targetCenter = ShapeTool.getCenterPoint(target.getShape());
				this.centerDX = nowPoint.getX() - targetCenter.getX();
				this.centerDY = nowPoint.getY() - targetCenter.getY();
				break;
			}
		}
	}

	private void fixTarget() {
		if(this.target != null) {
			Force targetForce = this.target.getForce();
			targetForce.setNoEffect(!targetForce.getNoEffect());
		}
	}
	
	private void releaseTarget() {
		if(this.target != null && lastGravity!=null) {target.getForce().velocity = lastGravity;} 
		this.target = null;
	}
	
	private void moveTarget() {
		Point2D tP = Camera.getRelativePoint(Mouse.nowPoint);
		Point2D nowPoint = new Point2D.Double(tP.getX(), tP.getY());
		Point2D targetCenter = ShapeTool.getCenterPoint(target.getShape());
		lastGravity = new ForceVector((nowPoint.getX() - targetCenter.getX() - this.centerDX)/2, (nowPoint.getY() - targetCenter.getY() - this.centerDY)/2);
		target.getForce().velocity = lastGravity; 
	}
	
	@Override 
	public void additionalDraw(Graphics2D g) {
		if(target!=null) {
			g.setColor(Color.WHITE);
			Point2D targetCenter = ShapeTool.getCenterPoint(target.getShape());
			Point2D masterCenter = ShapeTool.getCenterPoint(master.getShape());
			Shape result = this.target.getShape();
			result = EditShape.getScaledShape(result, effectScale, effectScale);
			result = EditShape.getMovedShapeCenterTo(result, targetCenter.getX(), targetCenter.getY());
			g.setStroke(new BasicStroke(3));
			g.draw(result);
			g.draw(new Line2D.Double(masterCenter.getX(), masterCenter.getY(), targetCenter.getX(), targetCenter.getY()));
		}
	}
	
	@Override public void processMessage(Message msg) {}
	@Override public void additionalSound() {}
}
