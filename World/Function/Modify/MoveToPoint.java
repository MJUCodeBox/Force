package Function.Modify;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import AStuff.Message;
import AStuff.MessageType;
import Function.AStuff.Function;
import WorldComponent.AStuff.WorldComponent;

public class MoveToPoint extends Function{
	private static final long serialVersionUID = -7869309081206589200L;

	// Value
	int speed = 0, leftRange =0, setRange = -1;
	Point2D aimPoint = new Point2D.Double(0, 0);
	
	// System Value
	double moveX, moveY;
	
	Message endMSG;
	private void updateMSG() {
		endMSG = new Message(MessageType.MoveTaskEnd, master, this, new Object[] {});
	}
	
	// Constructor
	public MoveToPoint(WorldComponent master) {super(master); updateMSG();} 
	
	public void setRange(int range) {this.setRange = range; this.leftRange = range;}
	public void setSpeed(int speed) {this.speed=speed; update();}
	public void setAimPoint(Point2D aimPoint) {this.aimPoint=aimPoint; update(); if(setRange!=-1) {this.leftRange = setRange;}}
	
	private void update() {
		Rectangle2D masterBound = master.getShape().getBounds2D();
		Point2D masterCenter = new Point2D.Double(masterBound.getCenterX(), masterBound.getCenterY());
		this.leftRange = (int) masterCenter.distance(aimPoint);
		double dx = aimPoint.getX() - masterCenter.getX();
		double dy = aimPoint.getY() - masterCenter.getY();
		moveX = speed * dx / Math.sqrt(dx * dx + dy * dy);
		moveY = speed * dy / Math.sqrt(dx * dx + dy * dy);
	}
	
	@Override
	public void additionalUpdate() {
		if(leftRange>0) {
			AffineTransform at = new AffineTransform();
			at.translate(moveX, moveY);
			master.setShape(at.createTransformedShape(master.getShape()));
			leftRange -= speed;
		}else {
			master.giveMessage(endMSG);
		}
	}
		
	@Override public void additionalDraw(Graphics2D g) {}
	@Override public void additionalSound() {}
	@Override public void processMessage(Message msg) {
//		if(msg.getMsgType()==MessageType.ResistIntersect) {
//			if(lastCrash != msg.getSource()) {
//				reflect(msg);
//			}else {
//				lastCrash = null;
//			}
//			lastCrash = msg.getSource();
//			reflect(msg);
//		}
	}

//	WorldComponent lastCrash;
//	private void reflect(Message msg) {
//		Shape masterShape = master.getShape();
//		Rectangle masterBound = masterShape.getBounds();
//		
//		Shape sourceShape = msg.getSource().getShape();
//		Rectangle sourceBound = sourceShape.getBounds();
//		
//		Rectangle intersectBound = masterBound.intersection(sourceBound);
//		double dx = intersectBound.getWidth();
//		double dy = intersectBound.getHeight();
//		
//		if(dx>dy) {
//			//À§, ¾Æ·¡ ºÎ‹JÈû.
//			moveY = -moveY;
//		}else {
//			//¿À, ¿Þ ºÎ‹JÈû.
//			moveX = -moveX;
//		}
//	}

}
