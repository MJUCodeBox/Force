package worlds.SomeHow.levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import Shape.SSRectangle;
import SomeHowStuff.SHBall;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.Obstacle.Wall;
import worlds.aStuff.World;

public class SHWorld6_PushForceSimulator extends World{

	// User Value
	private int obstacleWHNum = 3;
	private int obstacleCenterX = 1920/2, obstacleCenterY = 1080/2;
	private int obstacleGap = 60;
	private Color pushForceStuffColor = new Color(161, 170, 178);	
	@Override
	public void makeWorld() {
		// Wall
		int wallThick = 500;
		int wallShowThick = 60;
		Shape wallOutShape = new Rectangle2D.Double(-wallThick,-wallThick,1920+wallThick*2,1080+wallThick*2);
		Shape wallInShape = new Rectangle2D.Double(wallShowThick,wallShowThick,1920 - wallShowThick*2, 1080 - wallShowThick*2);
		Shape wallShape = ShapeTool.getSubtract(wallOutShape, new Shape[] {wallInShape});
		TheWorld.add(new Wall(wallShape));
		
		// Add SH Balls
		Shape rect = SSRectangle.getShape(0,0,50,50);
		int blockCenterX = this.obstacleCenterX - (obstacleWHNum-1) * obstacleGap / 2;
		int blockCenterY = this.obstacleCenterY - (obstacleWHNum-1) * obstacleGap / 2;
		Vector<SHBall> balls = new Vector<SHBall>();
		for(int i = 0; i < obstacleWHNum; i++) {
			for(int v = 0; v < obstacleWHNum; v++) {
				SHBall ball = new SHBall(EditShape.getMovedShapeCenterTo(rect, blockCenterX + v*obstacleGap, blockCenterY + i*obstacleGap));
				ball.setTopColor(pushForceStuffColor);
				ball.setSideColor(pushForceStuffColor.darker());
				ball.setDrawConnection(false);
				ball.setLoveMouse(false);
				ball.setZHeight(10*(obstacleWHNum*obstacleWHNum - (i+v)));
				balls.add(ball);
			}
		}
		for(SHBall ball : balls) {TheWorld.add(ball);}
	}

	@Override 
	public void draw(Graphics2D g) {
		for(WorldComponent WC : TheWorld.getWorldComponents()) {WC.draw(g);}
	}
	
	// No Use
	@Override public void update() {}
	@Override public void sound() {}
}
