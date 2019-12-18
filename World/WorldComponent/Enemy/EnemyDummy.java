package WorldComponent.Enemy;

import java.awt.Shape;

import AStuff.Message;
import AStuff.MessageType;
import AStuff.TheWorld;
import Function.Damage.GetDamage;
import Function.Draw.DrawWorldComponentShape;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.AStuff.WorldComponentState;

public class EnemyDummy extends WorldComponent {
	private static final long serialVersionUID = -1240767666187762561L;

	// Functions
	DrawWorldComponentShape drawWorldComponentShape;
	GetDamage getDamage;
		
	// Constructor
	public EnemyDummy(Shape s) {
		super(s);
		addFunctions();
	}
	
	@Override
	public void additionalProcessMessage(Message msg) {
		if(msg.getMsgType() == MessageType.HealthUnderZero) {
			TheWorld.removeReservation(this);
		}
	}
	
	@Override
	public boolean areYou(WorldComponentState state) {
		return false;
	}
	
	@Override
	public void addFunctions() {
		this.drawWorldComponentShape = new DrawWorldComponentShape(this);
		this.add(drawWorldComponentShape);
		
		this.getDamage = new GetDamage(this);
		this.getDamage.setHealth(1000);
		this.add(getDamage);
	}
	
}
