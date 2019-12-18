package whiteVirusStuff.defenders.aStuff;

import AStuff.Message;
import AStuff.MessageType;
import AStuff.TheWorld;
import Edit.ShapeTool;
import Function.force.Force;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.AStuff.WorldComponentState;
import whiteVirusStuff.WhiteVirusPlayer;

public class DefenderForce extends Force{
	private static final long serialVersionUID = 1L;

	public DefenderForce(WorldComponent master) {super(master);}
	
	@Override
	protected void sendMsgToIntersectWC() {
		for(WorldComponent wc : TheWorld.getWorldComponents()) {
			if(wc!=master && ShapeTool.isIntersect(master.getShape(), wc.getShape())) {
				if(!master.areYou(WorldComponentState.Dead) || wc instanceof WhiteVirusPlayer) {
					wc.giveMessage(resistIntersectMSG);
					master.giveMessage(new Message(MessageType.ResistIntersect, wc, this, null));
				}
			}
		}
	}
	
	@Override
	protected void resistCollide() {
		for(WorldComponent wc : TheWorld.getWorldComponents()) {
			if(wc!=master && ShapeTool.isIntersect(master.getShape(), wc.getShape())) {
				if(!master.areYou(WorldComponentState.Dead) || wc instanceof WhiteVirusPlayer) {
					wc.giveMessage(resistIntersectMSG);
					master.giveMessage(new Message(MessageType.ResistIntersect, wc, this, null));
				}
				if(wc.isForceUse() && !(wc instanceof BasicDefender)) {
					resolveCollide(wc);
					resistIntersect(wc);
				}
			}
		}
	}
	
}
