package Function.Draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.Vector;

import AStuff.Message;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.AStuff.Function;
import WorldComponent.AStuff.WorldComponent;

public class DrawWCShapeBy3DHorn extends Function {// 네모 대충 8만 나노초.
	private static final long serialVersionUID = -3310306648643546896L;
	
	// System Values
	private Color sideColor = Color.WHITE;
	private Color lineColor = Color.GRAY;
	private boolean lineDraw = false;
	private int gapFromBottom, gapFromTop, height;
	private Vector<Shape> sides = new Vector<Shape>();
	
	public Vector<Shape> getSideShapes() {return this.sides;}
	public void setSideColor(Color sideColor) {this.sideColor = sideColor;}
	public void setLineColor(Color lineColor) {this.lineColor = lineColor;}
	public void setLineDraw(boolean draw) {this.lineDraw = draw;}
	public void setGapFromBottom(int i) {this.gapFromBottom = i;}
	public void setGapFromTop(int i) {this.gapFromTop = i;}
	public void setHeight(int i) {this.height = i;}
	
	// Constructor
	public DrawWCShapeBy3DHorn(WorldComponent master) {super(master);}

	@Override
	public void additionalDraw(Graphics2D g) {
		Shape masterShape = this.master.getShape();
		
		// Get Vertexes
		double[] temp = new double[2];
		PathIterator path = masterShape.getPathIterator(null);
		Vector<Point2D> botVertexPoints = new Vector<Point2D>();
		Vector<Point2D> topVertexPoints = new Vector<Point2D>();
		while (!path.isDone()) {
			path.currentSegment(temp);
			botVertexPoints.add(new Point2D.Double(temp[0], temp[1] - this.gapFromBottom));
			topVertexPoints.add(new Point2D.Double(temp[0], temp[1] -  this.height + this.gapFromTop));
			path.next();
		}
		
		// Get Sides
		Vector<Shape> sideShapes = new Vector<Shape>();
		Vector<Double> centerYs = new Vector<Double>();
		Point2D masterCenter = ShapeTool.getCenterPoint(masterShape);
		Point2D topPoint = new Point2D.Double(masterCenter.getX(), masterCenter.getY() - this.height + this.gapFromTop);
		for(int i = 0; i < botVertexPoints.size(); i++) {
			int nextIndex = (i ==  botVertexPoints.size() - 1) ? 0 : i + 1;
			Point2D nowBotPoint = botVertexPoints.get(i);
			Point2D nextBotPoint = botVertexPoints.get(nextIndex);
			
			double centerY = (nowBotPoint.getY() + nextBotPoint.getY() + topPoint.getY())/3;
			Shape side = makePolygon(nowBotPoint, nextBotPoint, topPoint);
			
			int index = getSortedIndex(centerYs, centerY);
			sideShapes.add(index, side);
			centerYs.add(index, centerY);
		}
		
		// Draw
		for(Shape sideShape : sideShapes) {
			g.setColor(this.sideColor);
			g.fill(sideShape);
			if(lineDraw) {
				g.setColor(this.lineColor);
				g.draw(sideShape);
			}
		}
		
		// Save Shapes
		this.sides.clear();
		this.sides.addAll(sideShapes);
	}
    
	private int getSortedIndex(Vector<Double> centerYs, double centerY) {
		if(centerYs.size() == 0) {return 0;}
		int index = 0;
		for(Double y : centerYs) {
			if(centerY < y) {return index;}
			index++;
		}
		return centerYs.size();
	}
	
	private Shape makePolygon(Point2D... points) {
		GeneralPath  path = new GeneralPath();
		path.moveTo(points[0].getX(), points[0].getY());
		for(Point2D p : points) {path.lineTo(p.getX(), p.getY());}
		path.closePath();
		return path;
	}
	
	@Override public void additionalUpdate() {}
	@Override public void additionalSound() {}
	@Override public void processMessage(Message msg) {}
}
