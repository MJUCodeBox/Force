package Function.forPlayer.move;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;

import AStuff.Message;
import Edit.EditShape;
import Edit.EditShape.Direction;
import Function.AStuff.Function;
import Global_KeyListener.KeyBoard;
import WorldComponent.AStuff.WorldComponent;

public class MoveByKeyBoard extends Function {
	private static final long serialVersionUID = -635162001232883202L;
	
	//Values
	int movePerUpdate = 5;
	public int getMovePerUpdate() {return movePerUpdate;}
	public void setMovePerUpdate(int movePerUpdate) {this.movePerUpdate = movePerUpdate;}

	int upKey = KeyEvent.VK_W,
		downKey = KeyEvent.VK_S,
		leftKey = KeyEvent.VK_A,
		rightKey = KeyEvent.VK_D;
	public void setMoveKey(int upKey, int downKey, int leftKey, int rightKey) {
		this.upKey = upKey; 
		this.downKey = downKey; 
		this.leftKey = leftKey; 
		this.rightKey = rightKey; 
	}
	
	//Constructor
	public MoveByKeyBoard(WorldComponent master) {super(master);}
	
	@Override
	public void additionalUpdate() {
		move();
	}
	
	private void move() {
		Direction direction = null;
		if (KeyBoard.isKeyDown(upKey) && KeyBoard.isKeyDown(leftKey)) {direction = Direction.LeftUp;}
		else if (KeyBoard.isKeyDown(upKey) && KeyBoard.isKeyDown(rightKey)) {direction = Direction.RightUp;} 
		else if (KeyBoard.isKeyDown(downKey) && KeyBoard.isKeyDown(leftKey)) {direction = Direction.LeftDown;}
		else if (KeyBoard.isKeyDown(downKey) && KeyBoard.isKeyDown(rightKey)) {direction = Direction.RightDown;}
		else if (KeyBoard.isKeyDown(upKey) && KeyBoard.isKeyDown(downKey)) {direction = null;}
		else if (KeyBoard.isKeyDown(leftKey) && KeyBoard.isKeyDown(rightKey)) {direction = null;}//Don't Move
		else if (KeyBoard.isKeyDown(upKey)) {direction = Direction.Up;}
		else if (KeyBoard.isKeyDown(downKey)) {direction = Direction.Down;}
		else if (KeyBoard.isKeyDown(leftKey)) {direction = Direction.Left;}
		else if (KeyBoard.isKeyDown(rightKey)) {direction = Direction.Right;}
		if (direction != null) {master.setShape(EditShape.getMovedShape(master.getShape(), direction, movePerUpdate));}
		
		if (direction != null) {
			if(Math.abs(nowAngle)>=maxABSAngle) {
				diffAngle*=-1;
			}
			nowAngle+=diffAngle;
			Shape result;
			result = master.getShape();
			result = EditShape.getRotatedShape(result, diffAngle);
			master.setShape(result);
		}else {
			Shape result;
			result = master.getShape();
			result = EditShape.getRotatedShape(result, -nowAngle);
			master.setShape(result);
			nowAngle = 0;
		}
	}
	double nowAngle = 0;
	double maxABSAngle = 10;
	double diffAngle = 1;
	
	@Override public void processMessage(Message msg) {}
	@Override public void additionalDraw(Graphics2D g) {}
	@Override public void additionalSound() {}
}
