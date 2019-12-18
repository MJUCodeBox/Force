package Function.Effect;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;

import AStuff.Message;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.AStuff.Function;
import WorldComponent.AStuff.WorldComponent;

public class Blink extends Function{
	private static final long serialVersionUID = 8182143283191393666L;

	// Value
	Color blinkColor = Color.WHITE;
	public void setBlinkColor(Color c) {this.blinkColor=c;}
	
	double scaleX, scaleY;
	public void setEffectScale(double scaleX, double scaleY) {
		this.scaleX=scaleX;
		this.scaleY=scaleY;
	}
	
	int interval, showUpTime;
	public void setInterval(int interval, int showUpTime) {
		this.interval=interval;
		this.showUpTime=showUpTime;
	}
	
	// System Value
	long lastShowUpTime = 0;
	boolean drawing = false;
	
	// Constructor
	public Blink(WorldComponent master) {super(master);}
	
	@Override 
	public void additionalDraw(Graphics2D g) {
		if(drawing) {
			if(stillDrawing()) {drawBlink(g);}
			else {drawing = false;}
		}else {
			if(ready()) {
				drawBlink(g);
				drawing = true;
				lastShowUpTime = System.currentTimeMillis();
			}
		}
	}
	
	private boolean ready() {return System.currentTimeMillis() - lastShowUpTime > interval;}
	private boolean stillDrawing() {return System.currentTimeMillis() - lastShowUpTime <  showUpTime;}

	private void drawBlink(Graphics2D g) {
		Shape masterShape = master.getShape();
		Point2D masterCenter = ShapeTool.getCenterPoint(masterShape);
		
		Shape scaledSape = EditShape.getScaledShape(masterShape, scaleX, scaleY);
		Shape movedShape = EditShape.getMovedShapeCenterTo(scaledSape, masterCenter.getX(), masterCenter.getY());
		
		g.setColor(blinkColor);
		g.fill(movedShape);
	}
	
	@Override public void processMessage(Message msg) {}
	@Override public void additionalUpdate() {}
	@Override public void additionalSound() {}
}
