package SomeHowStuff;

import java.awt.Color;
import java.awt.Shape;

import AStuff.Message;
import AStuff.MessageType;
import Function.Draw.DrawWCShapeBy3D;
import Function.Effect.Exposion3DEffect;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.AStuff.WorldComponentState;

public class SHBlock extends WorldComponent{
	private static final long serialVersionUID = 712603492980048372L;
	
	// Functions
	private DrawWCShapeBy3D drawWCShapeBy3D;
	private Exposion3DEffect exposion3DEffect;
	
	// Value
	private int zHeight = 10;
	private int breakCriticalPoint = 10;
	private Color topColor = new Color(17, 193, 253);
	private Color sideColor = new Color(20, 159, 236);
	
	// System Value
	private boolean dead = false;
	
	// Constructor
	public SHBlock(Shape s) {
		super(s);
		addFunctions();
		this.force.setNoEffect(true);
	}

	@Override 
	public void additionalProcessMessage(Message msg) {
		if(msg.getMsgType() == MessageType.ResistIntersect && msg.getSource() instanceof SHBall && !dead) {
			if(msg.getSource().getForce().getVelocity().size() > breakCriticalPoint) {
				die();
			}
		}
	}
	
	private void die() {
		this.exposion3DEffect.startEffect(drawWCShapeBy3D.getSideShapes(), sideColor, topColor);
		this.remove(drawWCShapeBy3D);
		this.forceOff();
		this.dead = true;
	}
	
	@Override
	public void addFunctions() {
		this.drawWCShapeBy3D = new DrawWCShapeBy3D(this);
		this.drawWCShapeBy3D.setHeight(zHeight);
		this.drawWCShapeBy3D.setTopColor(topColor);
		this.drawWCShapeBy3D.setSideColor(sideColor);
		this.add(drawWCShapeBy3D);
		
		this.exposion3DEffect = new Exposion3DEffect(this);
		this.add(exposion3DEffect);
	}
	
	// Getter & Setter
	public void setZHeight(int zHeight) {this.zHeight = zHeight;}
	public void setBreakCriticalPoint(int p) {this.breakCriticalPoint = p;}
	public void setSideColor(Color sideColor) {this.sideColor = sideColor;}
	public void setTopColor(Color topColor) {this.topColor = topColor;}
	
	// No Use
	@Override public boolean areYou(WorldComponentState state) {return false;}
}
