package worlds.whiteVirus.levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import AStuff.Camera;
import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.Draw.DrawWCShapeBy3D;
import Function.forPlayer.tool.GravityGun;
import Function.forPlayer.tool.SummonBall;
import Global_KeyListener.KeyBoard;
import Shape.SSNStar;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.Obstacle.Wall;
import whiteVirusStuff.WhiteVirusPlayer;
import worlds.aStuff.World;

public class WhiteVirusLevel3 extends World{

	Color bgColor = new Color(37, 42, 52);
	Color wallColor = new Color(234,234,234);
	Color playerColor = new Color(8,217,214);
	Color ballColor = new Color(255,46,99);
	
	@Override
	public void makeWorld() {
		// Wall
		
		int wallThick = 100;
		int wallZHeight = 100;
		
		Shape wallShapes[] = {
				new Rectangle(-wallThick, 0, wallThick, 1080),
				new Rectangle(0, -wallThick, 1920, wallThick),
				new Rectangle(1920, 0, wallThick, 1080),
				new Rectangle(0, 1080, 1920, wallThick)
		};
		
		for(Shape s : wallShapes) {
			Wall wall = new Wall(s);
			
			wall.remove(wall.drawWorldComponentShape);
			DrawWCShapeBy3D wallDrawWCShapeBy3D = new DrawWCShapeBy3D(wall);
			wallDrawWCShapeBy3D.setHeight(wallZHeight);
			wallDrawWCShapeBy3D.setTopColor(wallColor);
			wallDrawWCShapeBy3D.setSideColor(wallColor.darker());
			wall.add(wallDrawWCShapeBy3D);
			
			TheWorld.add(wall);
		}
					
		// Make Hexagon Shape
		SSNStar.setSpikiness(1);
		SSNStar.setNSpike(3);
		Shape hexagon = SSNStar.getShape(0, 0, 50, 50);
		hexagon = new Rectangle(0,0,50,50);
		
//		hexagon = new Rectangle(0,0,300,300);
//		hexagon = ShapeTool.getSubtract(hexagon, new Shape[] {new Rectangle(100,100,100,100)});
		
		// Player
		Shape playerShape;
		playerShape = EditShape.getMovedShapeCenterTo(hexagon, 1920/2, 1080/2);
		WhiteVirusPlayer p = new WhiteVirusPlayer(playerShape);

		// Edit Player Functions
		p.remove(p.shoot);//이거 이래두 돼나
		p.remove(p.drawWorldComponentShape);
		
		p.add(new GravityGun(p));
		SummonBall sb = new SummonBall(p);
		sb.setBallColor(ballColor);
		p.add(sb);
		
		DrawWCShapeBy3D drawWCShapeBy3D = new DrawWCShapeBy3D(p);
		drawWCShapeBy3D.setHeight(30);
		drawWCShapeBy3D.setTopColor(playerColor);
		drawWCShapeBy3D.setSideColor(playerColor.darker());
		p.add(drawWCShapeBy3D);
		
		p.setImortal(true);
		
		// Add Player
		TheWorld.setPlayer(p);
		TheWorld.add(p);
	}

	Point2D pp;
	@Override 
	public void draw(Graphics2D g) {
		if(TheWorld.getPlayer() != null) {
			pp = ShapeTool.getCenterPoint(TheWorld.getPlayer().getShape());
		}
		g.translate(-pp.getX() + 1920/2, -pp.getY() + 1080/2);
		
		Camera.setNowGraphic(g);
		Camera.debug(g);
		
		//wall빼고 1부터.
		g.setColor(bgColor);
		g.fillRect(0, 0, 1920, 1080);
		
		Vector<WorldComponent> WCs = TheWorld.getWorldComponents();
		Vector<WorldComponent> sortedWCs = new Vector<WorldComponent>();
		Vector<Double> centerYs = new Vector<Double>();
		for(int i = 0; i<WCs.size(); i++) {
			double centerY = ShapeTool.getCenterPoint(WCs.get(i).getShape()).getY();
			int index = getSortedIndex(centerYs, centerY);
			sortedWCs.add(index, WCs.get(i));
			centerYs.add(index, centerY);
		}
		for(WorldComponent wc : sortedWCs) {
			wc.draw(g);
		}
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
	
	// No Use
	@Override public void update() {
		Point2D playerCenter = ShapeTool.getCenterPoint(TheWorld.getPlayer().getShape());
		
		if(KeyBoard.isKeyDown(KeyEvent.VK_Q)) {
			for(WorldComponent wc : TheWorld.getWorldComponents()) {
				wc.setShape(EditShape.getRotatedShape(wc.getShape(), 1920/2, 1080/2, -1));
//				wc.setShape(EditShape.getRotatedShape(wc.getShape(), playerCenter.getX(), playerCenter.getY(), -1));
			}
		}
		if(KeyBoard.isKeyDown(KeyEvent.VK_E)) {
			for(WorldComponent wc : TheWorld.getWorldComponents()) {
				wc.setShape(EditShape.getRotatedShape(wc.getShape(), 1920/2, 1080/2, 1));
//				wc.setShape(EditShape.getRotatedShape(wc.getShape(), playerCenter.getX(), playerCenter.getY(), 1));
			}
		}
	}
	@Override public void sound() {}
}
