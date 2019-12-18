package Function.Damage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;

import AStuff.Message;
import AStuff.MessageType;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.AStuff.Function;
import Shape.PredefinedShape.SSSun;
import WorldComponent.AStuff.WorldComponent;

public class GetDamage extends Function{
	private static final long serialVersionUID = 7099061937314386932L;
	
	// Value
	private double nowHealth=0, fullhealth=0;
	public void setHealth(int health) {
		if(health == -1) {getDamage = false;}
		else{getDamage = true;}
		this.fullhealth=health;
		this.nowHealth=health;
	}
	
	private Color helathColor = Color.white;
	public void setHealthColor(Color c) {this.helathColor = c;}
	
	// System Value
	boolean getDamage = true;
	boolean msgSend = false;
	private Message healthUnderZeroMSG;
	private void updateMessage() {
		healthUnderZeroMSG = new Message(MessageType.HealthUnderZero, master, this, new Object[] {});
	}
	
	// Constructor
	public GetDamage(WorldComponent master) {super(master); updateMessage();}
	
	@Override
	public void processMessage(Message msg) {
		if(msg.getMsgType()==MessageType.Damage) {
			if(getDamage) {nowHealth-=(int)msg.getArguments()[0];}
			if(nowHealth<0 && !msgSend){master.giveMessage(healthUnderZeroMSG); msgSend = true;}
		}
	}
	
	@Override 
	public void additionalDraw(Graphics2D g) {
		// Show Health By Text
//		g.setFont(new Font(null, Font.BOLD, 20));
//		Point2D masterCenter = ShapeTool.getCenterPoint(master.getShape());
//		Shape textShape = EditShape.getMovedShapeCenterTo(TextShape.getTextShape(g, health+""), masterCenter.getX(), masterCenter.getY());
//		g.setColor(Color.red);
//		g.fill(textShape);
		
		// Show Health By Bar
//		Rectangle masterBound = master.getShape().getBounds();
//		double healthPersent = nowHealth/fullhealth;
//		Rectangle2D healthBar = new Rectangle2D.Double(masterBound.getX(), masterBound.getY() - 30, masterBound.getWidth()*healthPersent, 10);
//		g.setColor(Color.green);
//		g.fill(healthBar);
		
		// Show Health By Self
		if(nowHealth>0) {
			Shape masterShape = master.getShape();
			Point2D masterCenter = ShapeTool.getCenterPoint(masterShape);
			double healthPersent = nowHealth/fullhealth;
			if(!getDamage) {healthPersent = 1;}
			Shape healthShape = EditShape.getScaledShape(masterShape, healthPersent, healthPersent);
			healthShape = EditShape.getMovedShapeCenterTo(healthShape, masterCenter.getX(), masterCenter.getY());
			g.setColor(helathColor);
			g.fill(healthShape);
		}
//		//Test
//		if(nowHealth>0) {
//			Shape masterShape = master.getShape();
//			Point2D masterCenter = ShapeTool.getCenterPoint(masterShape);
//			Rectangle masterBound = masterShape.getBounds();
//			
//			double healthPersent = nowHealth/fullhealth;
//			if(!getDamage) {healthPersent = 1;}
//			
//			Shape healthShape = SSSun.getShape();
//			Rectangle healthBound = healthShape.getBounds();
//			double wRadion = masterBound.getWidth() / healthBound.getWidth();
//			double hRadion = masterBound.getHeight() / healthBound.getHeight();
//			healthShape = EditShape.getScaledShape(healthShape, wRadion, hRadion);
//			healthShape = EditShape.getScaledShape(healthShape, healthPersent, healthPersent);
//			healthShape = EditShape.getRotatedShape(healthShape, master.getForce().angle);
//			healthShape = EditShape.getMovedShapeCenterTo(healthShape, masterCenter.getX(), masterCenter.getY());
//			g.setColor(helathColor);
//			g.fill(healthShape);
//		}
		
	}
	
	@Override public void additionalUpdate() {}
	@Override public void additionalSound() {}
}
