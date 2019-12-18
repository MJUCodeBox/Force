package Function.Modify;

import java.awt.Graphics2D;

import AStuff.Message;
import Edit.EditShape;
import Function.AStuff.Function;
import WorldComponent.AStuff.WorldComponent;

public class Rotate extends Function{
	private static final long serialVersionUID = 671541002741107277L;
	
	// Values
	double angleFactor;
	public void setDiffAngle(double angleFactor) {
		this.angleFactor=angleFactor;
	}
	
	// Constructor
	public Rotate(WorldComponent master) {super(master);}

	@Override 
	public void additionalUpdate() {
		master.setShape(EditShape.getRotatedShape(master.getShape(), angleFactor));
	}
	
	@Override public void processMessage(Message msg) {}
	@Override public void additionalDraw(Graphics2D g) {}
	@Override public void additionalSound() {}
}
