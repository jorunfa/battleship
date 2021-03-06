package com.example.model;

import java.util.ArrayList;

public class Boats {
	
	private ArrayList<Boat> boats;

	public Boats() {
		boats = new ArrayList<Boat>();
		makeAllBoatsAndAddThemToBoatsArrayList();
	}
	
	private void makeAllBoatsAndAddThemToBoatsArrayList() {
		makeAllBoatsForPlayer(Player.PLAYER1);
		makeAllBoatsForPlayer(Player.PLAYER2);
	}
	
	private void makeAllBoatsForPlayer(Player player) {
		boats.add(new Boat(BoatType.AIRCRAFT_CARRIER, player));
		boats.add(new Boat(BoatType.BATTLESHIP, player));
		boats.add(new Boat(BoatType.SUBMARINE, player));
		boats.add(new Boat(BoatType.DESTROYER, player));
		boats.add(new Boat(BoatType.PATROL_BOAT, player));
	}
	
	public Boat getBoat(BoatType boatType, Player player) {
		for (Boat boat : boats) {
			if (boat.getType().equals(boatType) && boat.getPlayer().equals(player)) {
				return boat;
			}
		}
		return null;
	}
	
	public Boat getNextBoatToPlace() {
		for (Boat boat : boats) {
			if (!boat.isPlaced()) return boat;
		}
		return null;
	}

	public ArrayList<Boat> getBoats() {
		return boats;
	}

	public boolean allBoatsPlacedForPlayer(Player player) {
		for (Boat boat : getBoats()) {
			if (!boat.getPlayer().equals(player)) continue;
			if (!boat.isPlaced()) return false;
		}
		return true;
	}
}
