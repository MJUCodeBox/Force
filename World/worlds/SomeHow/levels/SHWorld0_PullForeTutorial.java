package worlds.SomeHow.levels;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import Shape.SSNStar;
import SomeHowStuff.SHBall;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.Obstacle.Wall;
import worlds.aStuff.World;

public class SHWorld0_PullForeTutorial extends World{

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
		
		// SH Ball
		Shape ballShape;
		ballShape = hexagon;
		ballShape = EditShape.getMovedShapeCenterTo(ballShape, 1920/2, 1080/2);
		SHBall p = new SHBall(ballShape);
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
