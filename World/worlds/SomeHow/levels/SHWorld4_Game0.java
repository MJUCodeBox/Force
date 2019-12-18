package worlds.SomeHow.levels;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import Shape.SSNStar;
import Shape.SSRectangle;
import SomeHowStuff.SHBall;
import SomeHowStuff.SHBlock;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.Obstacle.Wall;
import calculation.SuperVector;
import worlds.aStuff.World;

public class SHWorld4_Game0 extends World{

	// User Value
	private int blockWHNum = 5;
	private int blockCenterX = 1920/2, blockCenterY = 1080/6*2;
	private int blockGap = 50;
	
	@Override
	public void makeWorld() {
		// Wall
		int wallThick = 1000;
		Shape wallOutShape = new Rectangle2D.Double(-wallThick,-wallThick,1920+wallThick*2,1080+wallThick*2);
		Shape wallInShape = new Rectangle2D.Double(0,0,600,900);
		wallInShape = EditShape.getMovedShapeCenterTo(wallInShape, 1920/2, 1080/2);
		Shape wallShape = ShapeTool.getSubtract(wallOutShape, new Shape[] {wallInShape});
		TheWorld.add(new Wall(wallShape));
		
		// Add SH Blocks
		Shape rect = SSRectangle.getShape(0,0,50,50);
		int blockCenterX = this.blockCenterX - (blockWHNum-1) * blockGap / 2;
		int blockCenterY = this.blockCenterY - (blockWHNum-1) * blockGap / 2;
		Vector<SHBlock> blocks = new Vector<SHBlock>();
		for(int i = 0; i < blockWHNum; i++) {
			for(int v = 0; v < blockWHNum; v++) {
				blocks.add(new SHBlock(EditShape.getMovedShapeCenterTo(rect, blockCenterX + v*blockGap, blockCenterY + i*blockGap)));
			}
		}
		SuperVector.remove(blocks, 6, 8, 11, 13, 21, 23);
		for(SHBlock block : blocks) {TheWorld.add(block);}
		
		// Make Hexagon Shape
		SSNStar.setSpikiness(1);
		SSNStar.setNSpike(3);
		Shape hexagon = SSNStar.getShape(0, 0, 50, 50);
				
		// Player
		Shape playerShape;
		playerShape = hexagon;
		playerShape = EditShape.getMovedShapeCenterTo(playerShape, 1920/2, 1080/4*3);
		SHBall p = new SHBall(playerShape);
		TheWorld.add(p);
	}

	@Override 
	public void draw(Graphics2D g) {
		for(WorldComponent WC : TheWorld.getWorldComponents()) {WC.draw(g);}
	}
	
	// No Use
	@Override public void update() {}
	@Override public void sound() {}
}
