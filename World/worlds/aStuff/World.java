package worlds.aStuff;

import java.awt.Graphics2D;

public abstract class World {
	public abstract void makeWorld();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void sound();
}
