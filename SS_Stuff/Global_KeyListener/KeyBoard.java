package Global_KeyListener;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class KeyBoard implements KeyEventDispatcher {//Check All Key Event.
	
	// User Value
	private static int filterFactor = 256;// Don't Check Event if(key Code > filterFactor).
	
	// System Value
	private static boolean[] keyDown = new boolean[filterFactor];
	
	// Initialize
	public static void init() {
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	    manager.addKeyEventDispatcher(new KeyBoard());
	}
	
	// Get Value Methods
	public static boolean isKeyDown(int keyCode) {return keyDown[keyCode];}// check every time
	
	// Core
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			setKeyDown(keyDown, e.getKeyCode(), true);
		}else if (e.getID() == KeyEvent.KEY_RELEASED) {
			setKeyDown(keyDown, e.getKeyCode(), false);
		}
		return false;
	}
	
	public static void setKeyDown(boolean[] arr, int keyCode, boolean down) {
		if (keyCode > arr.length - 1) {return;}
		arr[keyCode] = down;
	}
}
