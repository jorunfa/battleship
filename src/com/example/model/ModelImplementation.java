package com.example.model;

import java.util.ArrayList;
import java.util.Observable;

public class ModelImplementation extends Model {
	
	private StateLogic stateLogic;
	private Boats boats;
	private BoatCollisionChecker boatCollisionChecker;
	private BombsHandler bombsHandler;
	
	public ModelImplementation() {
		boats = new Boats();
		boatCollisionChecker = new BoatCollisionChecker(this);
		bombsHandler = new BombsHandler();
		stateLogic = new StateLogic(this, boats, bombsHandler);
	}

	@Override
	public void update(Observable observable, Object data) {
		stateLogic.update(observable, data);
		setChanged();
	}

	public Boat getBoat(BoatType boatType, Player player) {
		return boats.getBoat(boatType, player);
	}
	
	public void attemptToPlaceBoat(Boat boatToPlace, Orientation orientation) {
		if (legalPlacementOfBoat(boatToPlace, orientation)) {
			boatToPlace.placeBoat(orientation);
			setChanged();
		}
	}
	
	public boolean legalPlacementOfBoat(Boat boatToPlace, Orientation orientation) {
		if (!boatToPlace.legalPlacementOfBoat(orientation)) return false;
		if (!boatCollisionChecker.leagalPlacementOfBoat(boatToPlace, orientation)) return false;
		return true;
	}
	
	public void attemptToPlaceBomb(Position position) {
		if (legalPlacementOfBomb(position)) {
			placeBomb(position);
			setChanged();
		}
	}

	public boolean legalPlacementOfBomb(Position position) {
		return bombsHandler.leagalPlacementOfBomb(position, getTurn());
	}
	
	private void placeBomb(Position position) {
		bombsHandler.placeBomb(position, getTurn());
	}

	public Direction getDirection() {
		return stateLogic.getDirection();
	}

	public ArrayList<Boat> getBoats() {
		return boats.getBoats();
	}

	public Boat getNextBoatToPlace() {
		return boats.getNextBoatToPlace();
	}

	public Player getTurn() {
		return stateLogic.getTurn();
	}

	public Stage getStage() {
		return stateLogic.getStage();
	}

	public boolean showChangingPlayersScreen() {
		return stateLogic.showChangingPlayersScreen();
	}
}
