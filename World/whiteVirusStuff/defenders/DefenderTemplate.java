package whiteVirusStuff.defenders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import AStuff.Message;
import AStuff.MessageType;
import whiteVirusStuff.defenders.aStuff.BasicDefender;

public class DefenderTemplate extends BasicDefender{
	private static final long serialVersionUID = 712603492980048372L;
	
	// Constructor
	public DefenderTemplate(Shape s) {//or use parameter to set shape
		super(s);
		this.setHealth(1000);
		this.setPlayerChaseSpeed(10);
		this.setHealthColor(Color.white);
	}
	
	@Override
	public void addFunctions() {
		super.addFunctions();
		
	}
	
	@Override 
	public void additionalProcessMessage(Message msg) {
		super.additionalProcessMessage(msg);
		if (msg.getMsgType() == MessageType.HealthUnderZero) {
		}
	}
	
	@Override 
	public void draw(Graphics2D g){
		super.draw(g);
	}
}
