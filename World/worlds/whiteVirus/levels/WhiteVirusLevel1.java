package worlds.whiteVirus.levels;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import Shape.SSEllipse;
import WorldComponent.Obstacle.Wall;
import whiteVirusStuff.WhiteVirusPlayer;
import whiteVirusStuff.defenders.aStuff.BasicDefender;
import worlds.aStuff.World;

public class WhiteVirusLevel1 extends World{

	@Override
	public void makeWorld() {
		// Wall
		int wallThick = 500;
		int wallShowThick = 60;
		Shape wallOutShape = new Rectangle2D.Double(-wallThick,-wallThick,1920+wallThick*2,1080+wallThick*2);
		Shape wallInShape = new Rectangle2D.Double(wallShowThick,wallShowThick,1920 - wallShowThick*2, 1080 - wallShowThick*2);
		Shape wallShape = ShapeTool.getSubtract(wallOutShape, new Shape[] {wallInShape});
		TheWorld.add(new Wall(wallShape));
		
		// Player
		Shape playerShape;
		playerShape = SSEllipse.getShape(0, 0, 60, 60);
		playerShape = EditShape.getMovedShapeCenterTo(playerShape, 1920/4, 1080/2);
		WhiteVirusPlayer p = new WhiteVirusPlayer(playerShape);
		TheWorld.setPlayer(p);
		TheWorld.add(p);
		
		// Defender
		BasicDefender dfender = new BasicDefender(new Rectangle(1000,100,100,100));
		TheWorld.add(dfender);
	}

	// No Use
	@Override public void update() {}
	@Override public void draw(Graphics2D g) {}
	@Override public void sound() {}
}
