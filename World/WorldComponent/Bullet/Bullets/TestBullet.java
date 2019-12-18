package WorldComponent.Bullet.Bullets;

import java.awt.Color;
import java.awt.Shape;

import AStuff.Message;
import Edit.EditShape;
import Function.Effect.Blink;
import Function.Effect.ScaleEffect;
import Function.Modify.Grow;
import Function.Modify.Rotate;
import Shape.PredefinedShape.SSCircularSaw;
import WorldComponent.Bullet.AStuff.Bullet;

public class TestBullet extends Bullet{
	private static final long serialVersionUID = 542566198724981834L;

	// Functions
	Blink blink;
	Grow grow;
	ScaleEffect scaleEffect;
	Rotate rotate;
	
	public TestBullet() {
		super(getBulletShape());
		rate = 5;
		damage = 10;
		speed = 5; 
		range = 1000;
		addFunctions();
	}

	private static Shape getBulletShape() {
		Shape result;
		result = EditShape.getScaledShape(SSCircularSaw.getShape(), 3, 3);
		return result;
	}
	
	@Override
	public void additionalProcessMessage(Message msg) {
		this.sucideWhenGetMoveTaskEndMSG(msg);
		this.sucideWhenGetResistIntersectMSG(msg);
	}
	
	@Override
	public void addFunctions() {
		super.addFunctions();
		
		this.drawWorldComponentShape.setFillColor(new Color(255, 57, 112));
		
//		this.blink = new Blink(this);
//		this.blink.setEffectScale(1.3, 1.3);
//		this.blink.setInterval(100, 50);
//		this.add(0, blink);
		
//		this.grow = new Grow(this);
//		this.grow.setGrowScale(1.01, 1.01);
//		this.add(grow);
		
//		this.scaleEffect = new ScaleEffect(this);
//		this.scaleEffect.setEffectFactors(1.3, 0.1, 0.96);
//		this.add(scaleEffect);
		
//		this.resistIntersect = new ResistIntersect(this);
//		this.add(resistIntersect);
//		
//		this.blockedByResistIntersect = new BlockedByResistIntersect(this);
//		this.add(blockedByResistIntersect);
		
		this.rotate = new Rotate(this);
		this.rotate.setDiffAngle(3);
		this.add(rotate);
	}
}
