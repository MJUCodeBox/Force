package Random_Stuff;

import java.awt.Color;

public class Random {

	public static int getRandomIntBetween(int min, int max) {
		return (int) (Math.random() * max) + min;
	}

	public static double getRandomDoubleBetween(double min, double max) {
		return (Math.random() * max) + min;
	}
	
	public static Color getRandomColor() {
		return new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
	}
	
}
