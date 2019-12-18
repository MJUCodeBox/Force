package Function;

import java.awt.Graphics2D;

import AStuff.Message;
import Function.AStuff.Function;
import WorldComponent.AStuff.WorldComponent;

public class AAACopyThisToMakeFunction extends Function{
	private static final long serialVersionUID = 1L;
	
	// Constructor
	public AAACopyThisToMakeFunction(WorldComponent master) {super(master);}

	@Override public void processMessage(Message msg) {}
	@Override public void additionalDraw(Graphics2D g) {}
	@Override public void additionalUpdate() {}
	@Override public void additionalSound() {}
}
