package WorldComponent;

import java.awt.Shape;

import AStuff.Message;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.AStuff.WorldComponentState;

public class AAACopyThisToMakeWC extends WorldComponent{
	private static final long serialVersionUID = 712603492980048372L;
	
	// Functions
	
	// Constructor
	public AAACopyThisToMakeWC() {//or use parameter to set shape
		super(getPlayerShape());
		addFunctions();
	}

	private static Shape getPlayerShape() {
		Shape result = null;
//		result = SSRectangle.getShape(0, 0, 50, 50);
		return result;
	}
	
	@Override
	public void additionalProcessMessage(Message msg) {}
	
	@Override
	public boolean areYou(WorldComponentState state) {
		return false;
	}
	
	@Override
	public void addFunctions() {
	}
}
