package Function.forPlayer.move;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import AStuff.Message;
import Animation.Time;
import Function.AStuff.Function;
import Function.force.Force;
import Global_KeyListener.KeyBoard;
import WorldComponent.AStuff.WorldComponent;

public class MoveByKeyBoardAndForce extends Function {
	private static final long serialVersionUID = -635162001232883202L;
	
	//Values
	private double movePerSecond = 30;
	public void setSpeed(int speed) {this.movePerSecond = speed;}
	
	private int upKey = KeyEvent.VK_W, downKey = KeyEvent.VK_S, leftKey = KeyEvent.VK_A, rightKey = KeyEvent.VK_D;
	public void setMoveKey(int upKey, int downKey, int leftKey, int rightKey) {
		this.upKey = upKey; 
		this.downKey = downKey; 
		this.leftKey = leftKey; 
		this.rightKey = rightKey; 
	}
	
	//Constructor
	public MoveByKeyBoardAndForce(WorldComponent master) {super(master);}
	
	@Override
	public void additionalUpdate() {move();}
	
	private void move() {
		int fps = (Time.fps == 0 ) ? 100 : Time.fps;
		double move = movePerSecond / fps;
		double diagonalMove = move / Math.sqrt(2);
		
		Force masterForce = this.master.getForce();
		if (KeyBoard.isKeyDown(upKey) && KeyBoard.isKeyDown(leftKey)) {masterForce.applyForce(-diagonalMove, -diagonalMove);}
		else if (KeyBoard.isKeyDown(upKey) && KeyBoard.isKeyDown(rightKey)) {masterForce.applyForce(diagonalMove, -diagonalMove);} 
		else if (KeyBoard.isKeyDown(downKey) && KeyBoard.isKeyDown(leftKey)) {masterForce.applyForce(-diagonalMove, diagonalMove);}
		else if (KeyBoard.isKeyDown(downKey) && KeyBoard.isKeyDown(rightKey)) {masterForce.applyForce(diagonalMove, diagonalMove);}
		else if (KeyBoard.isKeyDown(upKey) && KeyBoard.isKeyDown(downKey)) {masterForce.applyForce(0, 0);}
		else if (KeyBoard.isKeyDown(leftKey) && KeyBoard.isKeyDown(rightKey)) {masterForce.applyForce(0, 0);}
		else if (KeyBoard.isKeyDown(upKey)) {masterForce.applyForce(0, -move);}
		else if (KeyBoard.isKeyDown(downKey)) {masterForce.applyForce(0, move);}
		else if (KeyBoard.isKeyDown(leftKey)) {masterForce.applyForce(-move, 0);}
		else if (KeyBoard.isKeyDown(rightKey)) {masterForce.applyForce(move, 0);}
	}
	
	@Override public void processMessage(Message msg) {}
	@Override public void additionalDraw(Graphics2D g) {}
	@Override public void additionalSound() {}
}
