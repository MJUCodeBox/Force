package whiteVirusStuff.defenders.defender;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import AStuff.Message;
import AStuff.MessageType;
import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import WorldComponent.AStuff.WorldComponentState;
import whiteVirusStuff.defenders.aStuff.BasicDefender;

public class BabyShootDefender extends BasicDefender{
	private static final long serialVersionUID = 712603492980048372L;
	
	// Value
	private int babyWH = 50;
	public void setBabyWH(int wh) {this.babyWH = wh;}
	
	private int babySpeed = 150;
	private int babyHealth = 500;
	private Color babyColor = Color.WHITE;
	
	// System Value
	boolean deadButBabyAlive = false;
	BasicDefender baby;
	
	// Constructor
	public BabyShootDefender(Shape s) {//or use parameter to set shape
		super(s);
		this.setHealth(1000);
		this.setPlayerChaseSpeed(10);
	}

//	Grow g;
//	@Override
//	public void addFunctions() {
//		super.addFunctions();
//		g = new Grow(this);
//		g.setGrowScale(1.001, 1.001);
//		this.add(g);
//	}
	
	@Override
	public void update() {
		super.update();
		if(deadButBabyAlive && baby != null && baby.areYou(WorldComponentState.Dead)) {
			dead = true;
		}
	}
	
	@Override 
	public void additionalProcessMessage(Message msg) {
		if (msg.getMsgType() == MessageType.HealthUnderZero) {
			babyShoot();
			deadButBabyAlive = true;
			this.remove(this.chasePlayer);
		}
	}
	
	@Override 
	public void draw(Graphics2D g){
		super.draw(g);
		if(!deadButBabyAlive) {
			g.setColor(babyColor);
			g.fill(getBabyShape());
		}
	}

	private Shape getBabyShape() {
		Point2D parentCenter = ShapeTool.getCenterPoint(this.getShape());
		Shape babyShape = new Ellipse2D.Double(0,0,babyWH, babyWH);
		babyShape = EditShape.getMovedShapeCenterTo(babyShape, parentCenter.getX(), parentCenter.getY());
		return babyShape;
	}
	
	private void babyShoot() {
		baby = new BasicDefender(getBabyShape());
		baby.setHealth(babyHealth);
		baby.setPlayerChaseSpeed(babySpeed);
		baby.setHealthColor(babyColor);
		TheWorld.addReservation(baby);
	}
}
