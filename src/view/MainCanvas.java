package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;

import AStuff.Camera;
import AStuff.TheWorld;
import Animation.AnimationCanvas;
import Animation.Time;
import Edit.EditShape;
import Edit.TextShape;
import Global_KeyListener.KeyBoard;
import Global_KeyListener.Mouse;
import WorldComponent.AStuff.WorldComponent;
import worlds.SomeHow.SomeHowWorld;
import worlds.aStuff.World;

public class MainCanvas extends AnimationCanvas{
	private static final long serialVersionUID = 1L;

	// Value
	private World nowWorld = new SomeHowWorld();
	
	// System Value
	private boolean fpsDraw = false, fpsDrawChangeReady = false;
	
	public MainCanvas() {
		this.fps = 60;
		Mouse.setMaster(this);
		this.nowWorld.makeWorld();
	}

	@Override
	public void update() {
		if(KeyBoard.isKeyDown(KeyEvent.VK_1) && fpsDrawChangeReady) {fpsDraw = (!fpsDraw); fpsDrawChangeReady = false;}
		if(!KeyBoard.isKeyDown(KeyEvent.VK_1)) {fpsDrawChangeReady = true;}
		
		this.nowWorld.update();
		TheWorld.addReservated();
		TheWorld.removeReservated();
		for(WorldComponent WC : TheWorld.getWorldComponents()) {WC.processMessage();}
		for(WorldComponent WC : TheWorld.getWorldComponents()) {WC.update();}
	}

	@Override
	public void draw(Graphics2D g) {
		Camera.setNowGraphic(g);
		Camera.debug(g);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		this.nowWorld.draw(g);
//		for(WorldComponent WC : TheWorld.getWorldComponents()) {WC.draw(g);}
		drawFPS(g);
	}

	@Override 
	public void sound() {
		this.nowWorld.sound();
		for(WorldComponent WC : TheWorld.getWorldComponents()) {WC.sound();}
	}
	
	public void drawFPS(Graphics2D g) {
		if(fpsDraw) {
			g.setFont(new Font(null, Font.BOLD, 200));
			Shape textShape = EditShape.getMovedShapeCenterTo(TextShape.getTextShape(g, "fps : "+ Time.fps+" / n : "+ TheWorld.getWorldComponents().size()), 1920/2, 100);
			g.setColor(Color.white);
			g.fill(textShape);
		}
	}
}
