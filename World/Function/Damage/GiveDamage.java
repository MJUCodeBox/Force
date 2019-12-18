package Function.Damage;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import AStuff.Message;
import AStuff.MessageType;
import AStuff.TheWorld;
import Function.AStuff.Function;
import WorldComponent.AStuff.WorldComponent;

public class GiveDamage extends Function{
	private static final long serialVersionUID = 7099061937314386932L;
	
	// Value
	int damage = 0;
	public void setDamage(int damage) {
		this.damage=damage;
		updateMessage();
	}
	
	Message msg;
	private void updateMessage() {
		msg = new Message(MessageType.Damage, master, this, new Object[] {damage});
	}
		
	// Constructor
	public GiveDamage(WorldComponent master) {super(master); updateMessage();}

	@Override public void additionalUpdate() {sendMSG();}
	private void sendMSG() {
		Rectangle masterShape = master.getShape().getBounds();
		for(WorldComponent WC : TheWorld.getWorldComponents()) {
			if(WC.getShape().intersects(masterShape)) {WC.giveMessage(msg);}
		}
	}
	
	@Override public void processMessage(Message msg) {}
	@Override public void additionalDraw(Graphics2D g) {}
	@Override public void additionalSound() {}
}
