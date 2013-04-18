package com.example.model;

import java.util.ArrayList;
import java.util.Observable;

public class ArrayGameModel extends Model {
	
	private ModelTurn turn;
	private ModelStage stage;
	private ArrayList<Boat> boats;
	private BoatCollisionChecker boatCollisionChecker;
	private Direction direction;
	
	public ArrayGameModel() {
		turn = ModelTurn.PLAYER1;
		stage = ModelStage.PLACE_BOATS;
		boats = new ArrayList<Boat>();
		boatCollisionChecker = new BoatCollisionChecker(this);
		makeAllBoatsAndAddThemToBoatsArrayList();
		direction = Direction.RIGHT;
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

	@Override
	public void update(Observable observable, Object data) {
		if (data instanceof Position) {
			System.out.println(((Position) data).getColumn());
			System.out.println(((Position) data).getRow());
		}
		else if (data instanceof Button) {
			if ((Button) data == Button.ChangeDirection) {
				flipDirection();
			}
		}
	}

	private void flipDirection() {
		if (direction == Direction.RIGHT) {
			direction = Direction.UP;
		}
		else {
			direction = Direction.RIGHT;
		}
	}

	public ModelTurn getTurn() {
		return turn;
	}

	public ModelStage getStage() {
		return stage;
	}

	public ArrayList<Boat> getBoats() {
		return boats;
	}

	public Boat getBoat(BoatType boatType, Player player) {
		for (Boat boat : boats) {
			if (boat.getType() == boatType && boat.getPlayer() == player) {
				return boat;
			}
		}
		return null;
	}
	
	public void attemptToPlaceBoat(Boat boatToPlace, Orientation orientation) {
		if (legalPlacementOfBoat(boatToPlace, orientation))
			boatToPlace.placeBoat(orientation); 
	}
	
	public boolean legalPlacementOfBoat(Boat boatToPlace, Orientation orientation) {
		if (!boatToPlace.legalPlacementOfBoat(orientation)) return false;
		if (!boatCollisionChecker.leagalPlacementOfBoat(boatToPlace, orientation)) return false;
		return true;
	}

	public Direction getDirection() {
		return direction;
	}

	public Boat getNextBoatToPlace() {
		return null;
	}
}
