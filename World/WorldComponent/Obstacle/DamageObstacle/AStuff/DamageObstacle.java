package WorldComponent.Obstacle.DamageObstacle.AStuff;

import java.awt.Shape;

import AStuff.Message;
import Function.Damage.GiveDamage;
import WorldComponent.Obstacle.AStuff.Obstacle;

public abstract class DamageObstacle extends Obstacle{
	private static final long serialVersionUID = 371507147776385747L;

	// Value
	protected int damage = 0;
	
	// Functions
	public GiveDamage giveDamage;
	
	// Constructor
	public DamageObstacle(Shape s) {super(s);}

	@Override
	public void additionalProcessMessage(Message msg) {
		super.additionalProcessMessage(msg);
	}
	
	@Override
	public void addFunctions() {
		super.addFunctions();
		
		this.giveDamage = new GiveDamage(this);
		this.giveDamage.setDamage(damage);
		this.add(giveDamage);
	}
}
