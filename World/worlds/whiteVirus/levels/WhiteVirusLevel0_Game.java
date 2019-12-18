package worlds.whiteVirus.levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import AStuff.Camera;
import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import Edit.TextShape;
import Shape.SSNStar;
import Shape.SSRectangle;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.AStuff.WorldComponentState;
import WorldComponent.Obstacle.Wall;
import whiteVirusStuff.WhiteVirusPlayer;
import whiteVirusStuff.defenders.aStuff.BasicDefender;
import worlds.aStuff.World;

public class WhiteVirusLevel0_Game extends World{

	// User Value
	private String clearText = "WOW! Clear!";
	
	// System Value
	private boolean clear = false;
	private WhiteVirusPlayer p;
	private Point2D pp;
	
	@Override
	public void makeWorld() {
		// Wall
		int wallThick = 1000;
		int wallShowThick = 0;
		Shape wallOutShape = new Rectangle2D.Double(-wallThick,-wallThick,1920+wallThick*2,1080+wallThick*2);
		Shape wallInShape = new Rectangle2D.Double(wallShowThick,wallShowThick,1920 - wallShowThick*2, 1080 - wallShowThick*2);
		Shape wallShape = ShapeTool.getSubtract(wallOutShape, new Shape[] {wallInShape});
		TheWorld.add(new Wall(wallShape));
				
		// Make Hexagon Shape
		SSNStar.setSpikiness(1);
		SSNStar.setNSpike(3);
		Shape hexagon = SSNStar.getShape(0, 0, 50, 50);
				
		// Player
		Shape playerShape;
		playerShape = EditShape.getMovedShapeCenterTo(hexagon, 1920/2, 1080/2);
		p = new WhiteVirusPlayer(playerShape);
		TheWorld.setPlayer(p);
		TheWorld.add(p);
		
		// Defender
		Shape rect = SSRectangle.getShape(0,0,50,50);
		rect = EditShape.getScaledShape(rect, 3, 3);
		
		rect = EditShape.getMovedShapeCenterTo(rect, 1920/4*3, 1080/2);
		BasicDefender defender2 = new BasicDefender(rect);
		TheWorld.add(defender2);
		
		rect = EditShape.getMovedShapeCenterTo(rect, 1920/4, 1080/2);
		BasicDefender defender3 = new BasicDefender(rect);
		TheWorld.add(defender3);
	}

	@Override 
	public void draw(Graphics2D g) {
		// Set Camera
		if(TheWorld.getPlayer() != null) {pp = ShapeTool.getCenterPoint(TheWorld.getPlayer().getShape());}
		g.translate(-pp.getX() + 1920/2, -pp.getY() + 1080/2);
		Camera.setNowGraphic(g);
		Camera.debug(g);
		
		// If Clear Draw Text
		if(clear) {
			Shape textShape = TextShape.getTextShape(g, clearText);
			textShape = EditShape.getScaledShape(textShape, 20, 20);
			textShape = EditShape.getMovedShapeCenterTo(textShape, 1920/2, 1080/2);
			g.setColor(Color.WHITE);
			g.fill(textShape);
		}
		
		for(WorldComponent WC : TheWorld.getWorldComponents()) {WC.draw(g);}
	}
	
	
	@Override 
	public void update() {
		
		// Check Clear
		boolean allDead = true;
		for(WorldComponent wc : TheWorld.getWorldComponents()) {
			if(wc instanceof BasicDefender && !wc.areYou(WorldComponentState.Dead)) {
				allDead = false;
				break;
			}
		}
		if(allDead) {clear = true; p.setImortal(true);}
	}
	
	// No Use
	@Override public void sound() {}
}
