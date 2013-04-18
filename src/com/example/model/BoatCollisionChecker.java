package com.example.model;

import java.util.ArrayList;

public class BoatCollisionChecker {
	
	ModelImplementation model;
	
	public BoatCollisionChecker(ModelImplementation model) {
		this.model = model;
	}
	
	public boolean leagalPlacementOfBoat(Boat boatToCheck, Orientation orientation) {
		Boat testBoat = new Boat(boatToCheck.getType(), boatToCheck.getPlayer());
		testBoat.placeBoat(orientation);
		for (Boat boat : model.getBoats()) {
			if (!boat.isPlaced()) break;
			if (testBoat.getPlayer() != boat.getPlayer()) break;
			if (areColliding(testBoat, boat)) return false;
		}
		return true;
	}

	public boolean areColliding(Boat boat1, Boat boat2){
		ArrayList<Position> coordinatesBoat1 = getBoatPositions(boat1);
		ArrayList<Position> coordinatesBoat2 = getBoatPositions(boat2);
		if (listsContainsIdenticalPoints(coordinatesBoat1, coordinatesBoat2)) return true;
		return false;
	}
	
	public ArrayList<Position> getBoatPositions(Boat boat) {
		ArrayList<Position> ret = new ArrayList<Position>();
		if (boat.getDirection() == Direction.RIGHT) {
			for (int i = 0; i < boat.getLength(); i++) {
				ret.add(new Position(boat.getPosition().getColumn()+i, boat.getPosition().getRow()));
			}
		}
		else {
			for (int i = 0; i < boat.getLength(); i++) {
				ret.add(new Position(boat.getPosition().getColumn(), (char) (boat.getPosition().getRow()-i)));
			}
		}
		return ret;
	}
	
	public boolean listsContainsIdenticalPoints(ArrayList<Position> list1, ArrayList<Position> list2) {
		for (Position position : list1) {
			if (list2.contains(position)) return true;
		}
		return false;
	}
}
