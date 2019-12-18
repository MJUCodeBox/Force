package SomeHowStuff;

import java.awt.Color;
import java.awt.Shape;

import AStuff.Message;
import Function.Draw.DrawWCShapeBy3D;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.AStuff.WorldComponentState;

public class SHObstacle extends WorldComponent{
	private static final long serialVersionUID = 712603492980048372L;
	
	// Functions
	private DrawWCShapeBy3D drawWCShapeBy3D;
	
	// Value
	private int zHeight = 10;
	
	private Color topColor = new Color(217, 193, 53);
	private Color sideColor = topColor.darker();
	
	// Constructor
	public SHObstacle(Shape s) {
		super(s);
		addFunctions();
	}

	@Override
	public void addFunctions() {
		this.drawWCShapeBy3D = new DrawWCShapeBy3D(this);
		this.drawWCShapeBy3D.setHeight(zHeight);
		this.drawWCShapeBy3D.setTopColor(topColor);
		this.drawWCShapeBy3D.setSideColor(sideColor);
		this.add(drawWCShapeBy3D);
	}
	
	// Getter & Setter
	public void setZHeight(int zHeight) {this.zHeight = zHeight;}
	public void setSideColor(Color sideColor) {this.sideColor = sideColor;}
	public void setTopColor(Color topColor) {this.topColor = topColor;}
	
	// No Use
	@Override public boolean areYou(WorldComponentState state) {return false;}
	@Override public void additionalProcessMessage(Message msg) {}
}
