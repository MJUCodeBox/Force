package WorldComponent.Bullet.Bullets;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import AStuff.Message;
import AStuff.MessageType;
import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.Effect.Blink;
import Function.Modify.Rotate;
import Shape.SSNStar;
import WorldComponent.Bullet.AStuff.Bullet;

public class ComeBackBullet extends Bullet{
	private static final long serialVersionUID = 542566198724981834L;

	// System Values
	boolean comeBack = false;
	
	// Functions
	Blink blink;
	Rotate rotate;
	
	public ComeBackBullet() {
		super(getBulletShape());
		rate = 3;
		damage = 10;
		speed = 20; 
		range = 1000;
		addFunctions();
	}

	static int holeWidth = 10;
	private static Shape getBulletShape() {
		Shape result;
		SSNStar.setNSpike(4);
		SSNStar.setSpikiness(0.4);
		result = SSNStar.getShape(0,0,50,50);
		result = EditShape.getMovedShapeCenterTo(result, holeWidth/2, holeWidth/2);
		result = ShapeTool.getSubtract(result, new Shape[] {new Ellipse2D.Double(0,0,holeWidth,holeWidth)});
		
		return result;
	}
	
	@Override
	public void additionalProcessMessage(Message msg) {
		this.comeBackOnWhenGetMoveTaskEndMSG(msg);
//		this.sucideWhenGetResistIntersectMSG(msg);
	}
	
	public void comeBackOnWhenGetMoveTaskEndMSG(Message msg) {
		if(msg.getMsgType() == MessageType.MoveTaskEnd) {comeBack = true;}
	}
	
	@Override
	public void update() {
		super.update();
		if(comeBack) {
			this.moveToPoint.setAimPoint(ShapeTool.getCenterPoint(this.shooter.getShape()));
			if(this.getShape().intersects(this.shooter.getShape().getBounds())) {TheWorld.removeReservation(this);}
		}
	}
	
	@Override
	public void addFunctions() {
		super.addFunctions();
		
		this.drawWorldComponentShape.setFillColor(Color.yellow);
		
		this.blink = new Blink(this);
		this.blink.setEffectScale(1.3, 1.3);
		this.blink.setInterval(100, 50);
		this.add(0, blink);
		
		this.rotate = new Rotate(this);
		this.rotate.setDiffAngle(5);
		this.add(rotate);
	}
	
}
