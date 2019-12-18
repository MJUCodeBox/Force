package Function.force;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import AStuff.Camera;
import AStuff.Message;
import Animation.Time;
import Edit.ShapeTool;
import Function.AStuff.Function;
import Global_KeyListener.Mouse;
import WorldComponent.AStuff.WorldComponent;

public class HateMouseByForce extends Function{
	private static final long serialVersionUID = 1L;
	
	// Value - Speed
	private int speed = 10;
	private int distanceSpeedFactor = 100;
	
	// Value - Control
	private boolean activateByPress = false;
	
	// Value - Draw Connect
	private int connectionThick = 5;
	private boolean drawConnection = false;
	private Color connectionColor = Color.white;
	
	// Constructor
	public HateMouseByForce(WorldComponent master) {super(master);}
	
	@Override 
	public void additionalUpdate() {
		Point2D playerCenter = Camera.getRelativeMousePoint();
		if(playerCenter != null && activate()) {
			Point2D masterCenter = ShapeTool.getCenterPoint(master.getShape());
			int speed = this.speed * (int)playerCenter.distance(masterCenter)/distanceSpeedFactor;
			double fullDx = playerCenter.getX() - masterCenter.getX();
			double fullDy = playerCenter.getY() - masterCenter.getY();
			double size = Math.sqrt(fullDx*fullDx + fullDy*fullDy);
			int fps = (Time.fps==0) ? 100 : Time.fps;
			double dx = fullDx / size * speed / fps;
			double dy = fullDy / size * speed / fps;
			this.master.getForce().applyForce(dx, dy);
		}
	}

	@Override 
	public void additionalDraw(Graphics2D g) {
		if(drawConnection) {
			Point2D playerCenter = Camera.getRelativeMousePoint();
			if(playerCenter != null && activate()) {
				Point2D masterCenter = ShapeTool.getCenterPoint(master.getShape());
				g.setColor(connectionColor);
				g.setStroke(new BasicStroke(connectionThick));
				g.drawLine((int)playerCenter.getX(), (int)playerCenter.getY(), (int)masterCenter.getX(), (int)masterCenter.getY());
				g.setStroke(new BasicStroke(1));// Back
			}
		}
	}
	
	public boolean activate() {return !activateByPress || (activateByPress&&Mouse.isMousePressed());}
	
	// Getter & Setter
	public void setChaseSpeed(int speed) {this.speed = speed;}
	public void setDistanceSpeedFactor(int factor) {this.distanceSpeedFactor = factor;}
	public void setActivateByPress(boolean boo) {this.activateByPress = boo;}
	public void setDrawConnection(boolean boo) {this.drawConnection = boo;}
	public void setConnectionThick(int thick) {this.connectionThick = thick;}
	public void setConnectionColor(Color color) {this.connectionColor = color;}
	
	// No Use
	@Override public void processMessage(Message msg) {}
	@Override public void additionalSound() {}
}
