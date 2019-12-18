package SomeHowStuff;

import java.awt.Color;
import java.awt.Shape;

import AStuff.Message;
import Function.Draw.DrawWCShapeBy3D;
import Function.force.FollowMouseByForce;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.AStuff.WorldComponentState;

public class SHBall extends WorldComponent{
	private static final long serialVersionUID = 712603492980048372L;
	
	// Functions
	private DrawWCShapeBy3D drawWCShapeBy3D;
	private FollowMouseByForce followMouseByForce;
	
	// Value
	private int mouseFollowSpeed = 75;
	private int zHeight = 10;
	
	private Color topColor = new Color(217, 193, 253);
	private Color sideColor = topColor.darker();
	
	// Constructor
	public SHBall(Shape s) {
		super(s);
		addFunctions();
//		this.getForce().setResistFactor(1);
	}

	@Override
	public void addFunctions() {
		this.followMouseByForce = new FollowMouseByForce(this);
		this.followMouseByForce.setChaseSpeed(mouseFollowSpeed);
		this.followMouseByForce.setDrawConnection(true);
		this.followMouseByForce.setActivateByPress(true);
		this.add(followMouseByForce);
		
		this.drawWCShapeBy3D = new DrawWCShapeBy3D(this);
		this.drawWCShapeBy3D.setHeight(zHeight);
		this.drawWCShapeBy3D.setTopColor(topColor);
		this.drawWCShapeBy3D.setSideColor(sideColor);
		this.add(drawWCShapeBy3D);
	}
	
	// Getter & Setter
	public void setMouseFollowSpeed(int speed) {this.mouseFollowSpeed = speed;}
	public void setZHeight(int zHeight) {this.zHeight = zHeight; this.drawWCShapeBy3D.setHeight(zHeight);}
	public void setSideColor(Color sideColor) {this.sideColor = sideColor; this.drawWCShapeBy3D.setSideColor(sideColor);}
	public void setTopColor(Color topColor) {this.topColor = topColor; this.drawWCShapeBy3D.setTopColor(topColor);}
	public void setLoveMouse(boolean b) {followMouseByForce.setLoveMouse(b);}
	public void setDrawConnection(boolean b) {followMouseByForce.setDrawConnection(b);}
	
	// No Use
	@Override public boolean areYou(WorldComponentState state) {return false;}
	@Override public void additionalProcessMessage(Message msg) {}
}
