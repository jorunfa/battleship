package com.example.model;

import java.util.ArrayList;

public class BoatCollisionChecker {
	
	private StateLogic stateLogic;
	
	public BoatCollisionChecker(StateLogic stateLogic) {
		this.stateLogic = stateLogic;
	}
	
	public boolean leagalPlacementOfBoat(Boat boatToCheck, Orientation orientation) {
		Boat testBoat = new Boat(boatToCheck.getType(), boatToCheck.getPlayer());
		testBoat.placeBoat(orientation);
		for (Boat boat : stateLogic.getBoats()) {
			if (!boat.isPlaced()) break;
			if (testBoat.getPlayer() != boat.getPlayer()) break;
			if (areColliding(testBoat, boat)) return false;
		}
		return true;
	}

	public boolean areColliding(Boat boat1, Boat boat2){
		ArrayList<Position> coordinatesBoat1 = boat1.getBoatPositions();
		ArrayList<Position> coordinatesBoat2 = boat2.getBoatPositions();
		if (listsContainsIdenticalPoints(coordinatesBoat1, coordinatesBoat2)) return true;
		return false;
	}
	
	public boolean listsContainsIdenticalPoints(ArrayList<Position> list1, ArrayList<Position> list2) {
		for (Position position : list1) {
			if (list2.contains(position)) return true;
		}
		return false;
	}
}
