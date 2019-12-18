package worlds.SomeHow;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import AStuff.TheWorld;
import Global_KeyListener.KeyBoard;
import worlds.SomeHow.levels.SHWorld0_PullForeTutorial;
import worlds.SomeHow.levels.SHWorld1_PullForceSimulator;
import worlds.SomeHow.levels.SHWorld2_Block;
import worlds.SomeHow.levels.SHWorld3_BlockSimulator;
import worlds.SomeHow.levels.SHWorld4_Game0;
import worlds.SomeHow.levels.SHWorld5_ForceReactionSimulator;
import worlds.SomeHow.levels.SHWorld6_PushForceSimulator;
import worlds.aStuff.World;
import worlds.whiteVirus.levels.WhiteVirusLevel0_Game;
import worlds.whiteVirus.levels.WhiteVirusLevel2_GravityGun;
import worlds.whiteVirus.levels.WhiteVirusLevel3;

public class SomeHowWorld extends World {

	// Value
	private int offKey = KeyEvent.VK_ESCAPE;
	private int resetKey = KeyEvent.VK_R;
	private int nextWorldKey = KeyEvent.VK_N;
	private World[] worlds = {
			new WhiteVirusLevel3(), 
			new WhiteVirusLevel2_GravityGun(), 
			new WhiteVirusLevel0_Game(), 
			
			new SHWorld0_PullForeTutorial(),
			new SHWorld1_PullForceSimulator(),
			new SHWorld6_PushForceSimulator(),
			new SHWorld2_Block(),
			new SHWorld3_BlockSimulator(),
			new SHWorld4_Game0(),
			new SHWorld5_ForceReactionSimulator(),
	};
	
	// System Value
	private boolean resetReady = false, nextWorldReady = false; // for one action per one press
	private int nowLevel = 0;
	private World nowWorld;

	@Override
	public void makeWorld() {changeToNextWorld();}

	@Override
	public void update() {
		// System Off
		if (KeyBoard.isKeyDown(offKey)) {System.exit(0);}

		// Next World
		if (!KeyBoard.isKeyDown(nextWorldKey)) {nextWorldReady = true;}
		else if (KeyBoard.isKeyDown(nextWorldKey) && nextWorldReady) {changeToNextWorld(); nextWorldReady = false;}
		
		// Reset World
		if (!KeyBoard.isKeyDown(resetKey)) {resetReady = true;}
		else if (KeyBoard.isKeyDown(resetKey) && resetReady) {
			TheWorld.clearWorld();
			this.nowWorld.makeWorld();
			resetReady = false;
		}
		
		this.nowWorld.update();
	}

	private void changeToNextWorld() {
		TheWorld.clearWorld();
		nowWorld = worlds[nowLevel];
		nowLevel++;
		if (nowLevel > worlds.length - 1) {nowLevel = 0;}
		this.nowWorld.makeWorld();
	}

	// No Use
	@Override public void draw(Graphics2D g) {nowWorld.draw(g);}
	@Override public void sound() {nowWorld.sound();}
}
