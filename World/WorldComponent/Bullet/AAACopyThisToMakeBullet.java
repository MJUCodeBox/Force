package WorldComponent.Bullet;

import java.awt.Shape;

import AStuff.Message;
import WorldComponent.Bullet.AStuff.Bullet;

public class AAACopyThisToMakeBullet extends Bullet{
	private static final long serialVersionUID = 542566198724981834L;

	// Functions
		
	public AAACopyThisToMakeBullet() {
		super(getBulletShape());
		rate = 0;
		damage = 0;
		speed = 0; 
		range = 0;
		addFunctions();
	}

	private static Shape getBulletShape() {
		Shape result = null;
//		result = EditShape.getScaledShape(SSCircularSaw1.getShape(), 3, 3);
		return result;
	}
	
	@Override
	public void additionalProcessMessage(Message msg) {
		super.additionalProcessMessage(msg);
		
	}
	
	@Override
	public void addFunctions() {
		super.addFunctions();
		
	}
}
