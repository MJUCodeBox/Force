package WorldComponent.Bullet.Bullets;

import java.awt.Graphics2D;
import java.awt.Shape;

import AStuff.Message;
import Shape.SSRectangle;
import Shape.SSRombus;
import WorldComponent.Bullet.AStuff.Bullet;

public class WhiteVirusBullet extends Bullet{
	private static final long serialVersionUID = 542566198724981834L;

	public WhiteVirusBullet() {
		super(getBulletShape());
		rate = 10;
		damage = 10;
		speed = 5; 
		range = 1700;
		addFunctions();
	}

	private static Shape getBulletShape() {
		Shape result = null;
		result = SSRectangle.getShape(0,0,10,40);
		result = SSRombus.getShape(0, 0, 20, 40, 10);
//		result = SSRectangle.getShape(0,0,20,100);
//		result = ShapeTool.getCompound(new Shape[] {result, SSRombus.getShape(30, 0, 20, 40, 10)});
		return result;
	}
	
	@Override
	public void draw(Graphics2D g){
		super.draw(g);
	}
	
	@Override
	public void additionalProcessMessage(Message msg) {
		super.additionalProcessMessage(msg);
	}
	
	@Override
	public void addFunctions() {
		super.addFunctions();
//		this.drawWorldComponentShape.setFillColor(new Color(0, 230,255));
	}
}
