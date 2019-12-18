package Function.forPlayer.tool;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import AStuff.Camera;
import AStuff.Message;
import AStuff.TheWorld;
import Edit.EditShape;
import Function.AStuff.Function;
import Global_KeyListener.KeyBoard;
import Global_KeyListener.Mouse;
import WorldComponent.D3Ball;
import WorldComponent.AStuff.WorldComponent;

public class SummonBall extends Function{
	private static final long serialVersionUID = 1L;
	
	// Value
	int ballWH = 100;
	int activateKey = KeyEvent.VK_1;
	
	// System Value
	boolean ready = true;
	
	// Constructor
	public SummonBall(WorldComponent master) {super(master);}

	@Override 
	public void additionalUpdate() {
		if(ready && KeyBoard.isKeyDown(activateKey)) {summonBall(); ready = false;}
		if(!KeyBoard.isKeyDown(activateKey)) {ready = true;}
	}
	
	private void summonBall() {
		Point2D nowPoint = Camera.getRelativePoint(Mouse.nowPoint);
		Shape ballShape = new Rectangle2D.Double(0, 0, ballWH, ballWH);
		ballShape = EditShape.getMovedShapeCenterTo(ballShape, nowPoint.getX(), nowPoint.getY());
		D3Ball ball = new D3Ball(ballShape);
		ball.setTopColor(ballColor);
		ball.setSideColor(ballColor.darker());
		TheWorld.addReservation(ball);
	}

	@Override public void processMessage(Message msg) {}
	@Override public void additionalDraw(Graphics2D g) {}
	@Override public void additionalSound() {}

	Color ballColor = Color.CYAN;
	public void setBallColor(Color ballColor) {this.ballColor = ballColor;}
}
