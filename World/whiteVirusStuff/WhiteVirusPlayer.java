package whiteVirusStuff;

import java.awt.Color;
import java.awt.Shape;

import AStuff.Message;
import AStuff.MessageType;
import AStuff.TheWorld;
import Function.Draw.DrawWorldComponentShape;
import Function.Shoot.AStuff.Shoot;
import Function.Shoot.Shoots.BasicShoot;
import Function.forPlayer.move.MoveByKeyBoardAndForce;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.AStuff.WorldComponentState;
import WorldComponent.Bullet.Bullets.WhiteVirusBullet;
import whiteVirusStuff.defenders.aStuff.BasicDefender;

public class WhiteVirusPlayer extends WorldComponent{
	private static final long serialVersionUID = 712603492980048372L;
	
	// Functions
	public DrawWorldComponentShape drawWorldComponentShape;
	private MoveByKeyBoardAndForce moveByKeyBoardAndForce;
	public Shoot shoot; // White Virus World 2의 기능 제거를 할라꼬 퍼블릭으로 함. 좋은 방법 찾기.
	
	// User Value
	private int speedWhileNothing = 60, speedWhileShoot = 20;
	private Color playerColor = Color.CYAN;
	private Color shootingColor = Color.CYAN.darker();
	private boolean imortal = false;
	
	// Constructor
	public WhiteVirusPlayer(Shape s) {
		super(s);
		addFunctions();
	}

	@Override
	public void update() {
		super.update();
		speedSet();
	}
	
	private void speedSet() {
		if(this.shoot.triggered()) {this.setColor(shootingColor);this.moveByKeyBoardAndForce.setSpeed(speedWhileShoot);}
		else {this.setColor(playerColor);this.moveByKeyBoardAndForce.setSpeed(speedWhileNothing);}
	}

	@Override
	public void addFunctions() {
		this.moveByKeyBoardAndForce = new MoveByKeyBoardAndForce(this);
		this.moveByKeyBoardAndForce.setSpeed(speedWhileNothing);
		this.add(moveByKeyBoardAndForce);
		
		this.drawWorldComponentShape = new DrawWorldComponentShape(this);
		this.drawWorldComponentShape.setFillColor(playerColor);
		this.add(drawWorldComponentShape);
		
		this.shoot = new BasicShoot(this);
		this.shoot.setBullet(new WhiteVirusBullet());
		this.add(shoot);
	}
	
	@Override 
	public void additionalProcessMessage(Message msg) {
		if(msg.getMsgType() == MessageType.ResistIntersect && msg.getSource() instanceof BasicDefender) {
			if(!imortal) {
				TheWorld.removeReservation(this);
				TheWorld.setPlayer(null);
			}
		}
	}
	
	// Getter & Setter
	public void setImortal(boolean boo) {this.imortal = boo;}
	public void setColor(Color c) {this.drawWorldComponentShape.setFillColor(c);}
	
	// No Use
	@Override public boolean areYou(WorldComponentState state) {return false;}
}
