package WorldComponent.Obstacle.DamageObstacle;

import java.awt.Color;
import java.awt.Shape;

import AStuff.Message;
import Edit.EditShape;
import Function.Modify.Rotate;
import Shape.PredefinedShape.SSCircularSaw;
import WorldComponent.AStuff.WorldComponentState;
import WorldComponent.Obstacle.DamageObstacle.AStuff.DamageObstacle;

public class CircularSaw extends DamageObstacle{
	private static final long serialVersionUID = -9102157214716322531L;
	
	// Functions
	Rotate rotate;
	
	// Constructor
	public CircularSaw(int centerX, int centerY) {
		super(getSawShape(centerX, centerY));
		damage = 10;
		addFunctions();
	}

	private static Shape getSawShape(int centerX, int centerY) {
		Shape result;
		result = EditShape.getScaledShape(SSCircularSaw.getShape(), 3, 3);
		result = EditShape.getMovedShapeCenterTo(result, centerX, centerY);
		return result;
	}

	@Override
	public void additionalProcessMessage(Message msg) {
		super.additionalProcessMessage(msg);
	}
	
	@Override
	public boolean areYou(WorldComponentState state) {
		return false;
	}
	
	@Override
	public void addFunctions() {
		super.addFunctions();
		
		this.drawWorldComponentShape.setFillColor(new Color(255, 57, 112));
		
		this.rotate = new Rotate(this);
		this.rotate.setDiffAngle(3);
		this.add(this.rotate);
	}

}
