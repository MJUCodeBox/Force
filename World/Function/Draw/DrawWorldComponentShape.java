package Function.Draw;

import java.awt.Color;
import java.awt.Graphics2D;

import AStuff.Message;
import Function.AStuff.Function;
import WorldComponent.AStuff.WorldComponent;

public class DrawWorldComponentShape extends Function {
	private static final long serialVersionUID = -3310306648643546896L;
	
	// Values
	private Color fillColor = Color.WHITE;
	public void setFillColor(Color fillColor) {this.fillColor = fillColor;}

	// Constructor
	public DrawWorldComponentShape(WorldComponent master) {super(master);}

	@Override
	public void additionalDraw(Graphics2D g) {
		g.setColor(fillColor);
		g.fill(master.getShape());
	}
	
	@Override public void additionalUpdate() {}
	@Override public void additionalSound() {}
	@Override public void processMessage(Message msg) {}
}
