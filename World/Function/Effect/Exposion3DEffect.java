package Function.Effect;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Vector;

import AStuff.Message;
import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.AStuff.Function;
import Function.force.Force;
import Function.force.ForceVector;
import Random_Stuff.Random;
import WorldComponent.AStuff.WorldComponent;

public class Exposion3DEffect extends Function{// Not Generalized. Only For DrawWCShapeBy3D.
	private static final long serialVersionUID = 8182143283191393666L;

	// User Value
	private int alphaFactor = 5;
	
	// System Value
	private Vector<Effect> effects = new Vector<Effect>();
	private boolean effectStart = false;
	private double friction;
	
	// Constructor
	public Exposion3DEffect(WorldComponent master) {super(master);}
		
	public void startEffect(Vector<Shape> sides, Color sideColor, Color topColor) {
		this.effectStart = true;
		
		int sumCenterY = 0;
		for(Shape s : sides) {sumCenterY += s.getBounds().getCenterY();}
		int avgCenterX = (int) sides.firstElement().getBounds().getCenterX();
		int avgCenterY = sumCenterY / sides.size();
		
		Force masterForce = master.getForce();
		this.friction = masterForce.getFriction();
		ForceVector masterVelocity = master.getForce().getVelocity();
		double masterAngleVelocity = master.getForce().getAngularVelocity();
		
		Effect e;
		for(Shape s : sides) {
			e = new Effect();
			e.setShape(s);
			e.setColor(sideColor);
			e.setVector(avgCenterX, avgCenterY, masterVelocity, masterAngleVelocity);
			this.effects.add(e);
		}
		this.effects.lastElement().setColor(topColor);
	}
	
	private Color createAlphaColor(Color c) {
		int alpha = (c.getAlpha() - alphaFactor > 0) ? c.getAlpha() - alphaFactor : 0;
		return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
	}

	// Inner Class
	private class Effect{
		private Shape effectShape;
		private ForceVector vector;
		private Color fillColor;
		private double torque;
		
		public void setVector(int avgCenterX, int avgCenterY, ForceVector masterVelocity, double masterAngleVelocity) {
			Point2D effectCenter = ShapeTool.getCenterPoint(effectShape);
			double xVelocity = effectCenter.getX() - avgCenterX;
			double yVelocity = effectCenter.getY() - avgCenterY;
			
			// annoying but it's OK. because this is EFFECT 
			xVelocity = masterVelocity.x*100000000*Random.getRandomIntBetween(5, 20)/10;
			yVelocity =  masterVelocity.y*100000000*Random.getRandomIntBetween(5, 20)/10;
			torque = masterAngleVelocity *1000000000*10000000*Random.getRandomIntBetween(5, 20)/10;
			vector = new ForceVector(xVelocity, yVelocity);
			
			this.effectShape = EditShape.getScaledShape(effectShape, 1, ((double)Random.getRandomIntBetween(1, 10))/10);
			this.effectShape = EditShape.getMovedShapeCenterTo(effectShape, effectCenter.getX(), effectCenter.getY());
		}
		
		public void draw(Graphics2D g) {
			g.setColor(fillColor);
			g.fill(effectShape);
		}
		
		public void update() {
			this.effectShape = EditShape.getMovedShape(effectShape, vector.x, vector.y);
			this.effectShape = EditShape.getRotatedShape(effectShape, torque);
			this.fillColor = createAlphaColor(fillColor);
			if(fillColor.getAlpha() == 0) {TheWorld.removeReservation(master);}// NO See, Die!
			this.vector.scale(friction);
			torque *=friction;
		}
		
		// Getter & Setter
		public void setShape(Shape s) {this.effectShape = s;}
		public void setColor(Color c) {this.fillColor = c;}
	}
	
	@Override public void additionalDraw(Graphics2D g) {if(effectStart) {for(Effect e : effects) {e.draw(g);}}}
	@Override public void additionalUpdate() {if(effectStart) {for(Effect e : effects) {e.update();}}}
	
	// No Use
	@Override public void processMessage(Message msg) {}
	@Override public void additionalSound() {}
}
