package worlds.whiteVirus;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import AStuff.TheWorld;
import Global_KeyListener.KeyBoard;
import WorldComponent.AStuff.WorldComponent;
import WorldComponent.AStuff.WorldComponentState;
import whiteVirusStuff.defenders.aStuff.BasicDefender;
import worlds.aStuff.World;
import worlds.whiteVirus.levels.WhiteVirusLevel0_Game;
import worlds.whiteVirus.levels.WhiteVirusLevel1;

public class WhiteVirusWorld extends World{

	// Value
	int resetKey = KeyEvent.VK_R;
	int sleepLong = 10000;
	
	// System Value
	int nowLevel = 0;
	World[] levels = {new WhiteVirusLevel0_Game(), new WhiteVirusLevel1()};
	World nowWorld;
	boolean sleep = false;
	long sleepStartTime;
	
	@Override
	public void makeWorld() {
		changeToNextLevel();
	}

	@Override
	public void update() {
		if(KeyBoard.isKeyDown(KeyEvent.VK_ESCAPE)) {System.exit(0);}
		
		if(sleep) {
			sleep = false;
//			if(System.currentTimeMillis() - sleepStartTime > sleepLong) {sleep = false;}
//			System.out.println(sleep);
		}else {
			// test Clear
			boolean clear = true;
			for(WorldComponent wc : TheWorld.getWorldComponents()) {
				if(wc instanceof BasicDefender) {
					if(!wc.areYou(WorldComponentState.Dead)){
						clear = false;
					}
				}
			}
			
			if(clear) {
				TheWorld.clearWorld();
				changeToNextLevel();
				this.sleep = true;
				this.sleepStartTime = System.currentTimeMillis();
			}
			
			// test reset
			if(TheWorld.getPlayer()==null && KeyBoard.isKeyDown(resetKey)) {
				TheWorld.clearWorld();
				this.nowWorld.makeWorld();
				this.sleep = true;
				this.sleepStartTime = System.currentTimeMillis();
			}
			
			this.nowWorld.update();
		}
	}
	
	private void changeToNextLevel() {
		nowWorld = levels[nowLevel];
		nowLevel++;
		if(nowLevel > levels.length - 1) {nowLevel = 0;}
		this.nowWorld.makeWorld();
	}
	
	// No Use
	@Override public void draw(Graphics2D g) {}
	@Override public void sound() {}
}
