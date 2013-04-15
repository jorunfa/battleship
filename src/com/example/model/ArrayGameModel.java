package com.example.model;

import java.util.ArrayList;
import java.util.Observable;

public class ArrayGameModel extends Model {
	
	private ModelTurn turn;
	private ModelStage stage;
	private ArrayList<Boat> boats;
	private BoatCollisionChecker boatCollisionChecker;
	
	public ArrayGameModel() {
		turn = ModelTurn.PLAYER1;
		stage = ModelStage.PLACE_BOATS;
		boats = new ArrayList<Boat>();
		boatCollisionChecker = new BoatCollisionChecker();
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

	@Override
	public void update(Observable observable, Object data) {
		if(data instanceof Position){
			
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

	public boolean legalPlacementOfBoat(Boat boatToPlace, Orientation orientation) {
		return false;
	}
}
