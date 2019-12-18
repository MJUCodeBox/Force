package AStuff;

import java.util.Vector;

import WorldComponent.AStuff.WorldComponent;

public class TheWorld {

	// Player
	private static WorldComponent player;
	public static WorldComponent getPlayer() {return player;}
	public static void setPlayer(WorldComponent player) {TheWorld.player = player;}

	// World Component
	public static Vector<WorldComponent> WorldComponents = new Vector<WorldComponent>();
	public static void add(WorldComponent WC) {WorldComponents.add(WC);}
	public static Vector<WorldComponent> getWorldComponents(){return WorldComponents;}
	public static void clearWorld() {WorldComponents.clear();}
	
	// Remove On Real Time
	public static Vector<WorldComponent> RemoveReservationLine = new Vector<WorldComponent>();
	public static void removeReservation(WorldComponent WC) {RemoveReservationLine.add(WC);}
	public static void removeReservated() {
		for(WorldComponent WC : RemoveReservationLine) {WorldComponents.remove(WC);}
		RemoveReservationLine.clear();
	}
	
	// Add On Real Time
	public static Vector<WorldComponent> AddReservationLine = new Vector<WorldComponent>();
	public static void addReservation(WorldComponent WC) {AddReservationLine.add(WC);}
	public static void addReservated() {
		for(WorldComponent WC : AddReservationLine) {WorldComponents.add(WC);}
		AddReservationLine.clear();
	}
}
