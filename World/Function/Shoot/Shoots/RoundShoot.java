package Function.Shoot.Shoots;

import AStuff.Camera;
import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.Shoot.AStuff.Shoot;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.Bullet.AStuff.Bullet;
import calculation.AffineMath;

public class RoundShoot extends Shoot{
	private static final long serialVersionUID = 5010238940329001164L;
	
	public RoundShoot(WorldComponent master) {super(master);}

	int bulletNum = 8;
	
	@Override
	public void shoot() {
		for(int i=0; i<bulletNum; i++) {
			double angle = (i * 360.0) / (bulletNum);
			Bullet newBullet = getBullet(Camera.getRelativeMousePoint());
			newBullet.setShape(EditShape.getRotatedShape(newBullet.getShape(), angle));
			newBullet.setAimPoint(AffineMath.rotatePoint(newBullet.getAimPoint(), ShapeTool.getCenterPoint(newBullet.getShape()), angle));
			TheWorld.addReservation(newBullet);
		}
	}
}