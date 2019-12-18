package WorldComponent;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import AStuff.Message;
import Function.Draw.DrawWCShapeBy3D;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.AStuff.WorldComponentState;

public class D3Ball extends WorldComponent{
	private static final long serialVersionUID = 712603492980048372L;

	// Functions
	private DrawWCShapeBy3D drawWCShapeBy3D;
	
	// User Value
	private int zHeight = 50;
	private Color topColor = new Color(17, 193, 253);
	private Color sideColor = new Color(20, 159, 236);
	
	// Constructor
	public D3Ball(Shape s) {
		super(s);
		addFunctions();
		this.force.setResistFactor(1);
		this.force.setInertia(10000);
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
	public void setzHeight(int zHeight) {this.zHeight = zHeight;}
	public void setTopColor(Color topColor) {this.topColor = topColor; this.drawWCShapeBy3D.setTopColor(topColor);}
	public void setSideColor(Color sideColor) {this.sideColor = sideColor; this.drawWCShapeBy3D.setSideColor(sideColor);}
	
	// No Use
	@Override public void draw(Graphics2D g){super.draw(g);}
	@Override public void additionalProcessMessage(Message msg) {}
	@Override public boolean areYou(WorldComponentState state) {return false;}
}
