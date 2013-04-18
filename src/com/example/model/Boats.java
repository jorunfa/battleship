package com.example.model;

import java.util.ArrayList;

public class Boats {
	
	private ArrayList<Boat> boats;

	public Boats() {
		boats = new ArrayList<Boat>();
		makeAllBoatsAndAddThemToBoatsArrayList();
	}
	
	public void makeAllBoatsAndAddThemToBoatsArrayList() {
		makeAllBoatsForPlayer(Player.PLAYER1);
		makeAllBoatsForPlayer(Player.PLAYER2);
	}
	
	public void makeAllBoatsForPlayer(Player player) {
		boats.add(new Boat(BoatType.AIRCRAFT_CARRIER, player));
		boats.add(new Boat(BoatType.BATTLESHIP, player));
		boats.add(new Boat(BoatType.SUBMARINE, player));
		boats.add(new Boat(BoatType.DESTROYER, player));
		boats.add(new Boat(BoatType.PATROL_BOAT, player));
	}
	
	public Boat getBoat(BoatType boatType, Player player) {
		for (Boat boat : boats) {
			if (boat.getType() == boatType && boat.getPlayer() == player) {
				return boat;
			}
		}
		return null;
	}
	
	public Boat getNextBoatToPlace() {
		for (Boat boat : boats) {
			if (boat.isPlaced()) return boat;
		}
		return null;
	}

	public ArrayList<Boat> getBoats() {
		return boats;
	}
}
