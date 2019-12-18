package Function.Effect;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import AStuff.Message;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.AStuff.Function;
import WorldComponent.AStuff.WorldComponent;

public class ScaleEffect extends Function{
	private static final long serialVersionUID = 8182143283191393666L;

	// Value
	Color effectColor = Color.WHITE;
	public void setEffectColor(Color c) {this.effectColor=c;}
	
	double nowScale, endScale, differentScale;
	public void setEffectFactors(double startScale, double endScale, double differentScale) {
		if(startScale < endScale) {taskType = Task.expansion;}
		else {taskType = Task.shrink;}
		
		this.nowScale=startScale;
		this.endScale=endScale;
		this.differentScale=differentScale;
	}
	
	public enum EffectShape{MasterShape, Ellipse}
	private EffectShape type = EffectShape.MasterShape;
	public void setEffectShape(EffectShape type) {this.type=type;}
	
	// System Value
	Task taskType;
	public enum Task{expansion, shrink}
	
	// Constructor
	public ScaleEffect(WorldComponent master) {super(master);}

	@Override 
	public void additionalDraw(Graphics2D g) {
		if(!taskFinished()) {
			nowScale*=differentScale;
			
			Shape scaledShape = null;
			if(type==EffectShape.MasterShape) {
				scaledShape = EditShape.getScaledShape(master.getShape(), nowScale, nowScale);
			}else if(type==EffectShape.Ellipse) {
				Shape asd = EditShape.getScaledShape(master.getShape(), nowScale, nowScale);
				Rectangle bound = asd.getBounds();
				scaledShape = new Ellipse2D.Double(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
			}
			
			Point2D center = ShapeTool.getCenterPoint(master.getShape());
			Shape effectShape = EditShape.getMovedShapeCenterTo(scaledShape, center.getX(), center.getY());
			
			g.setColor(effectColor);
			g.fill(effectShape);
		}
	}
	
	private boolean taskFinished() {
		if(taskType == Task.expansion) {return nowScale > endScale;}
		else{return nowScale < endScale;}// Task.shrink
	}

	@Override public void processMessage(Message msg) {}
	@Override public void additionalUpdate() {}
	@Override public void additionalSound() {}
}
