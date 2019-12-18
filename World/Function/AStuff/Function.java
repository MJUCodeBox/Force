package Function.AStuff;

import java.awt.Graphics2D;
import java.io.Serializable;

import AStuff.Message;
import WorldComponent.AStuff.WorldComponent;

public abstract class Function implements Serializable{
	private static final long serialVersionUID = 3587698675579651496L;
	
	// Constructor
	protected WorldComponent master;
	public Function(WorldComponent master) {this.master=master;}//Set Master
		
	// Task
	public abstract void additionalDraw(Graphics2D g);//Only Draw
	public abstract void additionalUpdate();//Only Update
	public abstract void additionalSound();//Only Sound
		
	// Function Message
	public abstract void processMessage(Message msg);
}
