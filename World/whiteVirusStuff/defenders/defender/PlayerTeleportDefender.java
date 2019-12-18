package whiteVirusStuff.defenders.defender;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;

import AStuff.Message;
import AStuff.MessageType;
import AStuff.TheWorld;
import Animation.Time;
import Edit.EditShape;
import Edit.ShapeTool;
import WorldComponent.AStuff.WorldComponent;
import whiteVirusStuff.defenders.aStuff.BasicDefender;

public class PlayerTeleportDefender extends BasicDefender{
	private static final long serialVersionUID = 712603492980048372L;
	
	// Value
	private int babySpeed = 10;
	private Color babyColor = new Color(100,120,159);
	
	// Constructor
	public PlayerTeleportDefender(Shape s) {//or use parameter to set shape
		super(s);
		this.setHealth(1000);
		this.setPlayerChaseSpeed(10);
		this.setHealthColor(new Color(130,170,255));
	}
	
	@Override
	public void addFunctions() {
		super.addFunctions();
	}
	
	@Override 
	public void additionalProcessMessage(Message msg) {
		super.additionalProcessMessage(msg);
		if (msg.getMsgType() == MessageType.HealthUnderZero) {
			playerTeleporterShoot();
		}
	}
	
	@Override 
	public void draw(Graphics2D g){
		super.draw(g);
		if(!dead) {
			g.setColor(babyColor);
			g.fill(getPlayerTeleporterShape());
		}
	}

	public Shape getPlayerTeleporterShape() {
		WorldComponent player = TheWorld.getPlayer();
		Point2D parentCenter = ShapeTool.getCenterPoint(this.getShape());
		Shape babyShape = player.getShape();
		babyShape = EditShape.getMovedShapeCenterTo(babyShape, parentCenter.getX(), parentCenter.getY());
		return babyShape;
	}
	
	private void playerTeleporterShoot() {
		BasicDefender baby = new BasicDefender(getPlayerTeleporterShape());
		baby.setHealth(-1);
		baby.setPlayerChaseSpeed(babySpeed);
		baby.setHealthColor(babyColor);
		baby.remove(chasePlayer);
		
		WorldComponent player = TheWorld.getPlayer();
		Point2D playerCenter = ShapeTool.getCenterPoint(player.getShape());
		Point2D masterCenter = ShapeTool.getCenterPoint(this.getShape());
		double fullDx = playerCenter.getX() - masterCenter.getX();
		double fullDy = playerCenter.getY() - masterCenter.getY();
		double size = Math.sqrt(fullDx*fullDx + fullDy*fullDy);
		int fps = (Time.fps==0) ? 100 : Time.fps;
		double dx = fullDx / size * babySpeed / fps;
		double dy = fullDy / size * babySpeed / fps;
		baby.getForce().applyForce(-dx, -dy);
		baby.getForce().setResistFactor(0.97);
		
		TheWorld.addReservation(baby);
	}
}
