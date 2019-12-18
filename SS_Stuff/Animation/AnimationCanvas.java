package Animation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public abstract class AnimationCanvas extends Canvas {
	private static final long serialVersionUID = 1L;

	public int fps = 60;
	
	// System Value
	long startTime, diff;
	private static BufferStrategy canvasBufferStrategy;
	int fpsTime = 1000/fps;
	
	public void initialize() {
		this.createBufferStrategy(2);
		canvasBufferStrategy = this.getBufferStrategy();
		
		new Thread(new MainLoop()).start();
	}

	private class MainLoop implements Runnable {
		@Override
		public void run() {
			while (true) {
				startTime = System.currentTimeMillis();
				Time.update();
				Graphics2D g = (Graphics2D) canvasBufferStrategy.getDrawGraphics();
				g.setBackground(Color.BLACK);
				g.clearRect(0, 0, getWidth(), getHeight());//Canvas Width & Height
				update();
				draw(g);
//				sound();
				g.dispose();
				canvasBufferStrategy.show();
				diff = System.currentTimeMillis() - startTime;
				if(fpsTime > diff) {
//					try {Thread.sleep(fpsTime - diff);}
//					catch (InterruptedException e) {}
				}
//				Thread.yield();
//				long totalMemory = Runtime.getRuntime().totalMemory();
//				long freeMemory = Runtime.getRuntime().freeMemory();
//				long useMemory = totalMemory - freeMemory;
//				
//				System.out.println("totalMemory : "+ totalMemory);
//				System.out.println("freeMemory : "+ freeMemory);
//				System.out.println("useMemory : "+ useMemory);
//				System.out.println("@@@@@@@@@@@@");
			}
		}
	}

	public abstract void sound();
	public abstract void update();
	public abstract void draw(Graphics2D g);
}
