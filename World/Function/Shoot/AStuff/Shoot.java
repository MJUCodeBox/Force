package Function.Shoot.AStuff;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;

import AStuff.Camera;
import AStuff.Message;
import AStuff.TheWorld;
import DeepCopy.DeepCopy;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.AStuff.Function;
import Global_KeyListener.Mouse;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.Bullet.AStuff.Bullet;
import WorldComponent.Bullet.Bullets.TestBullet;

public class Shoot extends Function{
	private static final long serialVersionUID = 5010238940329001164L;
	
	// Values
	Bullet bullet;
	public void setBullet(Bullet bullet) {this.bullet=bullet;}
	
	// System Values
	long beforeShootTime = 0;
	
	// Constructor
	public Shoot(WorldComponent master) {super(master);}
	
	@Override
	public void additionalUpdate() {
		if(triggered()&&ready()) {shoot(); beforeShootTime = System.currentTimeMillis();}
	}
	
	public boolean triggered() {return Mouse.mouseLeftPressed;}
	private boolean ready() {return System.currentTimeMillis() - beforeShootTime > 1000/bullet.rate;}
	
	public void shoot() {
//		Bullet newBullet = getBullet(Mouse.getNowPoint());
		Bullet newBullet = getBullet(Camera.getRelativeMousePoint());
		TheWorld.addReservation(newBullet);
	}

	public Bullet getBullet(Point2D aimPoint) {
		Bullet newBullet = null;
		if(bullet!=null) {newBullet = (Bullet) DeepCopy.copy(bullet);} //Deep Copy까지 안해도 될거같은뎅
		else {newBullet = new TestBullet();}// Make Basic Bullet
		
		Rectangle masterBound = master.getShape().getBounds();
		Shape result = null;
		result = EditShape.getMovedShapeCenterTo(newBullet.getShape(), masterBound.getCenterX(), masterBound.getCenterY());
		result = EditShape.getRotatedShapeHeadTo(result, aimPoint);
		newBullet.setShooter(master);
		newBullet.setShape(result);
		newBullet.setAimPoint(aimPoint);
		return newBullet;
	}
	
	@Override public void additionalSound() {}
	@Override public void additionalDraw(Graphics2D g) {}
	@Override public void processMessage(Message msg) {}
}
