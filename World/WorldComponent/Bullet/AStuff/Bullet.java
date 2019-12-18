package WorldComponent.Bullet.AStuff;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;

import AStuff.Message;
import AStuff.MessageType;
import AStuff.TheWorld;
import Function.Damage.GiveDamage;
import Function.Draw.DrawWCShapeBy3D;
import Function.Draw.DrawWorldComponentShape;
import Function.Modify.MoveToPoint;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.AStuff.WorldComponentState;

public abstract class Bullet extends WorldComponent {//Add Shoot Sound
	private static final long serialVersionUID = -228366651090444149L;
	
	// Values
	public int rate = 0;
	public int damage = 0;
	public int speed = 0; 
	public int range = 0;
	public Point2D aimPoint;
	
	// Functions
	public DrawWorldComponentShape drawWorldComponentShape;
	public MoveToPoint moveToPoint;
	public GiveDamage giveDamage;
		
	// Constructor
	public Bullet(Shape s) {super(s); this.forceOff();}
	
	protected  WorldComponent shooter;
	public void setShooter(WorldComponent shooter) {this.shooter=shooter;}
	
	public void setAimPoint(Point2D aimPoint) {this.aimPoint=aimPoint; this.moveToPoint.setAimPoint(aimPoint);}
	public Point2D getAimPoint() {return aimPoint;}
	
	@Override
	public void additionalProcessMessage(Message msg) {
		this.sucideWhenGetMoveTaskEndMSG(msg);
		this.sucideWhenGetResistIntersectMSG(msg);
	}
	
	public void sucideWhenGetMoveTaskEndMSG(Message msg) {
		if(msg.getMsgType() == MessageType.MoveTaskEnd) {
			TheWorld.removeReservation(this);
		}
	}
	
	public void sucideWhenGetResistIntersectMSG(Message msg) {
		if(msg.getSource()!=shooter && msg.getMsgType() == MessageType.ResistIntersect) {
			TheWorld.removeReservation(this);
		}
	}
	
	@Override
	public boolean areYou(WorldComponentState state) {
		if(state == WorldComponentState.NoEffectToResistIntersect) {return true;}
		return false;
	}
	
	DrawWCShapeBy3D drawWCShapeBy3D;
	
	private int zHeight = 10;
	private Color topColor = Color.white;
	private Color sideColor = topColor.darker();
	
	@Override
	public void addFunctions() {
		this.drawWorldComponentShape = new DrawWorldComponentShape(this);
		this.add(drawWorldComponentShape);
		
		this.moveToPoint = new MoveToPoint(this);
		this.moveToPoint.setSpeed(speed);
		this.moveToPoint.setRange(range);
		this.add(moveToPoint);
		
//		this.drawWCShapeBy3D = new DrawWCShapeBy3D(this);
//		this.drawWCShapeBy3D.setHeight(zHeight);
//		this.drawWCShapeBy3D.setTopColor(topColor);
//		this.drawWCShapeBy3D.setSideColor(sideColor);
////		this.drawWCShapeBy3D.setGapFromBottom(5);
//		drawWCShapeBy3D.setLineDraw(true);
//		this.add(drawWCShapeBy3D);
		
		this.giveDamage = new GiveDamage(this);
		this.giveDamage.setDamage(damage);
		this.add(giveDamage);
	}
}
