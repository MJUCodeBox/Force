package whiteVirusStuff.defenders.defender;

import java.awt.Color;
import java.awt.Shape;

import AStuff.Message;
import AStuff.MessageType;
import whiteVirusStuff.defenders.aStuff.BasicDefender;

public class StopDefender extends BasicDefender{
	private static final long serialVersionUID = 712603492980048372L;
	
	// Constructor
	public StopDefender(Shape s) {
		super(s);
		this.setHealth(1000);
		this.setPlayerChaseSpeed(10);
		this.setHealthColor(Color.white);
	}
	
	@Override 
	public void additionalProcessMessage(Message msg) {
		super.additionalProcessMessage(msg);
		if (msg.getMsgType() == MessageType.HealthUnderZero) {
			this.force.setNoEffect(true);
		}
	}
}
