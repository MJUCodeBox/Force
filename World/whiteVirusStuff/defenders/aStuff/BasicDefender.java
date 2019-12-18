package whiteVirusStuff.defenders.aStuff;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import AStuff.Message;
import AStuff.MessageType;
import Function.Damage.GetDamage;
import Function.force.ChasePlayer;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.AStuff.WorldComponentState;

public class BasicDefender extends WorldComponent{
	private static final long serialVersionUID = 712603492980048372L;
	
	// Functions
	public ChasePlayer chasePlayer;
	protected GetDamage getDamage;
		
	// Value
	private int health = 1000;
	private int playerChaseSpeed = 10;
	private Color healthColor = new Color(255, 57, 112);
	protected String bgImgName = "binary 2.jpg";
	
	// System Value
	protected boolean dead = false;
	private BufferedImage bg;
	
	// Constructor
	public BasicDefender(Shape s) {//or use parameter to set shape
		super(s);
		addFunctions();
		try {bg = ImageIO.read(new File(bgImgName));}
		catch (IOException e) {e.printStackTrace();}
	}

	@Override
	public void draw(Graphics2D g){
		g.setClip(this.getShape());
		g.drawImage(bg, 0, 0, null);
		g.setClip(null);
		super.draw(g);
	}
	
	@Override
	public void addFunctions() {
		this.chasePlayer = new ChasePlayer(this);
		this.chasePlayer.setChaseSpeed(playerChaseSpeed);
		this.add(this.chasePlayer);
		
		this.getDamage = new GetDamage(this);
		this.getDamage.setHealth(health);
		this.getDamage.setHealthColor(healthColor);
		this.add(this.getDamage);
		
		this.remove(force);
		this.force = new DefenderForce(this);
		this.force.setResistFactor(1);
		this.force.setElasticity(1);
		this.force.setAngularVelocity(1);
		this.force.setInertia(10000);
		this.add(force);
	}
	
	@Override 
	public void additionalProcessMessage(Message msg) {
		if (msg.getMsgType() == MessageType.HealthUnderZero) {
			dead = true;
			this.remove(this.chasePlayer);
		}
	}
	
	@Override 
	public boolean areYou(WorldComponentState state) {
		if(state == WorldComponentState.Dead) {return dead;}
		return false;
	}
	
	// Getter & Setter
	public void setHealth(int health) {
		this.health=health;
		this.getDamage.setHealth(health);
	}
	
	public void setPlayerChaseSpeed(int speed) {
		this.playerChaseSpeed = speed;
		this.chasePlayer.setChaseSpeed(speed);
	}
	
	public void setHealthColor(Color c) {
		this.healthColor = c;
		this.getDamage.setHealthColor(healthColor);
	}
}
