package Function.forPlayer.move;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;

import AStuff.Message;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.AStuff.Function;
import Function.force.Force;
import Function.force.ForceVector;
import Global_KeyListener.KeyBoard;
import WorldComponent.AStuff.WorldComponent;

public class Flash extends Function {
	private static final long serialVersionUID = -635162001232883202L;
	
	// User Values
	private double flashDistance = 300;// should be smaller than force effect area
	private int activateKey = KeyEvent.VK_SPACE;
	private int coolTime = 0;
	private int effectShowTime = 300;
	
	// System Value
	private boolean ready = true;
	private int effectStartTime;
	
	//Constructor
	public Flash(WorldComponent master) {super(master);}
	
	@Override
	public void additionalUpdate() {if(ready() && activate()) {flash();}}
	
	private boolean ready() {
		if(!KeyBoard.isKeyDown(activateKey)) {ready = true;}
		return ready;
	}
	
	private boolean activate() {
		return KeyBoard.isKeyDown(activateKey);
	}

	private void flash() {
		Force masterForce = this.master.getForce();
		ForceVector velocity = masterForce.getVelocity();
		double absVelocityX = Math.abs(velocity.x);
		double absVelocityY = Math.abs(velocity.y);
		double xVelocityRadio = absVelocityX / (absVelocityX + absVelocityY);
		double yVelocityRadio = absVelocityY / (absVelocityX + absVelocityY);
		int xSign = (velocity.x > 0) ? 1 : -1;
		int ySign = (velocity.y > 0) ? 1 : -1;
		
		if(velocity.x != 0 || velocity.y != 0) {
			Shape masterShape = this.master.getShape();
			this.master.setShape(EditShape.getMovedShape(masterShape, flashDistance * xVelocityRadio * xSign, flashDistance * yVelocityRadio * ySign));
		}
		this.ready = false;
	}
	
	// Getter & Setter
	public void setFlashDistance(int distance) {this.flashDistance = distance;}
	public void setActivateKey(int key) {this.activateKey = key;}
	
	// No Use
	@Override public void processMessage(Message msg) {}
	@Override public void additionalDraw(Graphics2D g) {}
	@Override public void additionalSound() {}
}
