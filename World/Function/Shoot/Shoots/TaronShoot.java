package Function.Shoot.Shoots;

import AStuff.Camera;
import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.Shoot.AStuff.Shoot;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.Bullet.AStuff.Bullet;
import calculation.AffineMath;

public class TaronShoot extends Shoot{
	private static final long serialVersionUID = 5010238940329001164L;
	
	public TaronShoot(WorldComponent master) {super(master);}

	int angleFactor = 12;
	
	@Override
	public void shoot() {
		for(int i=-1; i<2; i++) {
			double angle = (i * 360.0) / (angleFactor);
			Bullet newBullet = getBullet(Camera.getRelativeMousePoint());
			newBullet.setShape(EditShape.getRotatedShape(newBullet.getShape(), angle));
			newBullet.setAimPoint(AffineMath.rotatePoint(newBullet.getAimPoint(), ShapeTool.getCenterPoint(newBullet.getShape()), angle));
			TheWorld.addReservation(newBullet);
		}
	}
}