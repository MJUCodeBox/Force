package WorldComponent.Obstacle.AStuff;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Vector;

import AStuff.Message;
import AStuff.MessageType;
import Edit.ShapeTool;
import Function.Draw.DrawWorldComponentShape;
import Function.Modify.MoveToPoint;
import WorldComponent.AStuff.WorldComponent;

public abstract class Obstacle extends WorldComponent{
	private static final long serialVersionUID = 371507147776385747L;

	// Values
	public int speed = 5;
	int nowPointNum = 0;
	Vector<Point2D> movePoints;
	
	// Functions
	public DrawWorldComponentShape drawWorldComponentShape;
	public MoveToPoint moveToPoint;
	
	// Constructor
	public Obstacle(Shape s) {
		super(s);
		movePoints = new Vector<Point2D>();
		movePoints.add(ShapeTool.getCenterPoint(this.getShape()));
	}
	
	public void addMovePoint(int x, int y) {movePoints.add(new Point2D.Double(x, y));}
	public void removeMovePoint(int i) {movePoints.remove(i);}
	
	@Override
	public void additionalProcessMessage(Message msg) {
		if(msg.getMsgType() == MessageType.MoveTaskEnd) {
			nowPointNum++;
			if(movePoints.size()==nowPointNum) {nowPointNum=0;}
			this.moveToPoint.setAimPoint(movePoints.get(nowPointNum));
		}
	}
	
	@Override
	public void addFunctions() {
		this.drawWorldComponentShape = new DrawWorldComponentShape(this);
		this.add(drawWorldComponentShape);
		
		this.moveToPoint = new MoveToPoint(this);
		this.moveToPoint.setAimPoint(movePoints.get(nowPointNum));
		this.moveToPoint.setSpeed(speed);
		this.add(moveToPoint);
	}
}
