package Global_KeyListener;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	// Use This To Add Listeners
	public static void setMaster(Component c) {
		Mouse mouse = new Mouse();
		c.addMouseListener(mouse);
		c.addMouseMotionListener(mouse);
		c.addMouseWheelListener(mouse);
	}
	
	public static boolean mouseLeftPressed = false;
	public static boolean mouseWheelPressed = false;
	public static boolean mouseRightPressed = false;
	public static Point lastPressPoint = new Point(), nowPoint = new Point(0,0);
	public static int mouseWheelPosition = 0;

	public static boolean isMousePressed() {return mouseLeftPressed;}
	public static Point getLastPressPoint() {return lastPressPoint;}
	public static Point getNowPoint() {return nowPoint;}
	public static int getMouseWheelPosition() {return mouseWheelPosition;}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {mouseLeftPressed = true;}
		else if(e.getButton()==MouseEvent.BUTTON2) {mouseWheelPressed = true;}
		else if(e.getButton()==MouseEvent.BUTTON3) {mouseRightPressed = true;}
		lastPressPoint = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {mouseLeftPressed = false;}
		else if(e.getButton()==MouseEvent.BUTTON2) {mouseWheelPressed = false;}
		else if(e.getButton()==MouseEvent.BUTTON3) {mouseRightPressed = false;}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		nowPoint = e.getPoint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		nowPoint = e.getPoint();
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0) {mouseWheelPosition++;}
		else {mouseWheelPosition--;}
	}
	
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
}
