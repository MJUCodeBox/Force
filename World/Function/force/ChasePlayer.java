package Function.force;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import AStuff.Message;
import AStuff.TheWorld;
import Animation.Time;
import Edit.ShapeTool;
import Function.AStuff.Function;
import WorldComponent.AStuff.WorldComponent;

public class ChasePlayer extends Function{
	private static final long serialVersionUID = 1L;
	
	// Value
	private int speed = 10;
	public void setChaseSpeed(int speed) {this.speed = speed;}
	
	// Constructor
	public ChasePlayer(WorldComponent master) {super(master);}
	
	@Override 
	public void additionalUpdate() {
		WorldComponent player = TheWorld.getPlayer();
		if(player != null) {
			Point2D playerCenter = ShapeTool.getCenterPoint(player.getShape());
			Point2D masterCenter = ShapeTool.getCenterPoint(master.getShape());
			double fullDx = playerCenter.getX() - masterCenter.getX();
			double fullDy = playerCenter.getY() - masterCenter.getY();
			double size = Math.sqrt(fullDx*fullDx + fullDy*fullDy);
			int fps = (Time.fps==0) ? 100 : Time.fps;
			double dx = fullDx / size * speed / fps;
			double dy = fullDy / size * speed / fps;
			master.getForce().applyForce(dx, dy);
			
			// Limit Speed
			Force masterForce = master.getForce();
			ForceVector masterVelocity = masterForce.getVelocity();
			double velocitySize = masterVelocity.size();
			if(velocitySize > 5) {masterVelocity.scale(5/velocitySize);}
		}
	}

	@Override public void processMessage(Message msg) {}
	@Override public void additionalDraw(Graphics2D g) {}
	@Override public void additionalSound() {}
}
