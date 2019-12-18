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
import Function.AStuff.Function;
import WorldComponent.AStuff.WorldComponent;

public class DrawWCShapeBy3D extends Function {// 네모 대충 8만 나노초.
	private static final long serialVersionUID = -3310306648643546896L;
	
	// System Values
	private Color sideColor = Color.WHITE;
	private Color lineColor = Color.GRAY;
	private Color topColor = Color.WHITE;
	private boolean lineDraw = false;
	private int gapFromBottom, gapFromTop, height;
	private Vector<Shape> sides = new Vector<Shape>();
	
	// Constructor
	public DrawWCShapeBy3D(WorldComponent master) {super(master);}

	@Override
	public void additionalDraw(Graphics2D g) {
		Shape masterShape = this.master.getShape();
		
		// Get Vertexes
		double[] temp = new double[2];
		PathIterator path = masterShape.getPathIterator(null);
		Vector<Point2D> botVertexPoints = new Vector<Point2D>();
		Vector<Point2D> topVertexPoints = new Vector<Point2D>();
		int saveIndex = 0, nowIndex = 0;
		while (!path.isDone()) {
			path.currentSegment(temp);
			Point2D nowBot = new Point2D.Double(temp[0], temp[1] - this.gapFromBottom);
			Point2D nowTop = new Point2D.Double(temp[0], temp[1] -  this.height + this.gapFromTop);
			if(nowIndex > 0 && nowBot.equals(botVertexPoints.lastElement()) && nowTop.equals(topVertexPoints.lastElement())) {
				botVertexPoints.add(botVertexPoints.get(saveIndex));
				topVertexPoints.add(topVertexPoints.get(saveIndex));//도넛 해결
				saveIndex = nowIndex + 1;
			}else {
				botVertexPoints.add(nowBot);
				topVertexPoints.add(nowTop);
			}
			path.next();
			nowIndex++;
		}
		
		// Get Sides
		Vector<Shape> sideShapes = new Vector<Shape>();
		Vector<Double> centerYs = new Vector<Double>();
		for(int i = 0; i < botVertexPoints.size(); i++) {
			int nextIndex = (i ==  botVertexPoints.size() - 1) ? 0 : i + 1;
			Point2D nowBotPoint = botVertexPoints.get(i);
			Point2D nextBotPoint = botVertexPoints.get(nextIndex);
			Point2D nowTopPoint = topVertexPoints.get(i);
			Point2D nextTopPoint = topVertexPoints.get(nextIndex);
			
			double centerY = (nowBotPoint.getY() + nextBotPoint.getY() + nowTopPoint.getY() + nextTopPoint.getY())/4;
			Shape side = makePolygon(nowBotPoint, nextBotPoint, nextTopPoint, nowTopPoint);
			
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
		
		Shape topShape = EditShape.getMovedShape(masterShape, 0, -this.height + this.gapFromTop);
		g.setColor(this.topColor);
		g.fill(topShape);
		
		// Save Shapes
		this.sides.clear();
		this.sides.addAll(sideShapes);
		this.sides.add(topShape);
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
	
	// Getter & Setter
	public Vector<Shape> getSideShapes() {return this.sides;}
	public void setSideColor(Color sideColor) {this.sideColor = sideColor;}
	public void setLineColor(Color lineColor) {this.lineColor = lineColor;}
	public void setTopColor(Color topColor) {this.topColor = topColor;}
	public void setLineDraw(boolean draw) {this.lineDraw = draw;}
	public void setGapFromBottom(int i) {this.gapFromBottom = i;}
	public void setGapFromTop(int i) {this.gapFromTop = i;}
	public void setHeight(int i) {this.height = i;}
	
	// No Use
	@Override public void additionalUpdate() {}
	@Override public void additionalSound() {}
	@Override public void processMessage(Message msg) {}
}
