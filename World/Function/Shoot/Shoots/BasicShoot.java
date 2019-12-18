package Function.Shoot.Shoots;

import AStuff.Camera;
import AStuff.TheWorld;
import Function.Shoot.AStuff.Shoot;
import WorldComponent.AStuff.WorldComponent;

public class BasicShoot extends Shoot{
	private static final long serialVersionUID = -2574874886957018933L;

	public BasicShoot(WorldComponent master) {super(master);}

	@Override
	public void shoot() {
		TheWorld.addReservation(getBullet(Camera.getRelativeMousePoint()));
	}
	
}