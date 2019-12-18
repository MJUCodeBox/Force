package worlds.SomeHow.levels;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import Shape.SSNStar;
import SomeHowStuff.SHBall;
import SomeHowStuff.SHBlock;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.Obstacle.Wall;
import worlds.aStuff.World;

public class SHWorld3_BlockSimulator extends World{

	// User Value
	private int blockWHNum = 4;
	private int blockCenterX = 1920/2, blockCenterY = 1080/2;
	private int blockGap = 100;
	
	@Override
	public void makeWorld() {
		// Wall
		int wallThick = 500;
		int wallShowThick = 60;
		Shape wallOutShape = new Rectangle2D.Double(-wallThick,-wallThick,1920+wallThick*2,1080+wallThick*2);
		Shape wallInShape = new Rectangle2D.Double(wallShowThick,wallShowThick,1920 - wallShowThick*2, 1080 - wallShowThick*2);
		Shape wallShape = ShapeTool.getSubtract(wallOutShape, new Shape[] {wallInShape});
		TheWorld.add(new Wall(wallShape));
		
		// Make Hexagon Shape
		SSNStar.setSpikiness(1);
		SSNStar.setNSpike(3);
		Shape hexagon = SSNStar.getShape(0, 0, 50, 50);
		
		// Add SH Blocks
		int blockCenterX = this.blockCenterX - (blockWHNum-1) * blockGap / 2;
		int blockCenterY = this.blockCenterY - (blockWHNum-1) * blockGap / 2;
		for(int i = 0; i < blockWHNum; i++) {
			for(int v = 0; v < blockWHNum; v++) {
				TheWorld.add(new SHBlock(EditShape.getMovedShapeCenterTo(hexagon, blockCenterX + i*blockGap, blockCenterY + v*blockGap)));
			}
		}
		
		// Player
		Shape playerShape;
		playerShape = hexagon;
		playerShape = EditShape.getMovedShapeCenterTo(playerShape, 1920/4, 1080/2);
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
