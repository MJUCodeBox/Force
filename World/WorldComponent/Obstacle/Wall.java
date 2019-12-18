package WorldComponent.Obstacle;

import java.awt.Color;
import java.awt.Shape;

import AStuff.Message;
import Function.Draw.DrawWorldComponentShape;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.AStuff.WorldComponentState;

public class Wall extends WorldComponent{
	private static final long serialVersionUID = 712603492980048372L;
	
	// Value
	private Color wallColor = new Color(0, 54, 95);
	public void setColor(Color c) {this.drawWorldComponentShape.setFillColor(c);}
	
	// Functions
	public DrawWorldComponentShape drawWorldComponentShape;
	
	// Constructor
	public Wall(Shape s) {
		super(s);
		addFunctions();
		this.force.setNoEffect(true);
	}

	@Override
	public void addFunctions() {
		this.drawWorldComponentShape = new DrawWorldComponentShape(this);
		this.drawWorldComponentShape.setFillColor(wallColor);
		this.add(drawWorldComponentShape);
	}
	
	@Override public boolean areYou(WorldComponentState state) {return false;}
	@Override public void additionalProcessMessage(Message msg) {}
}
