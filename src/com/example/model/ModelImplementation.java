package com.example.model;

import java.util.ArrayList;
import java.util.Observable;

public class ModelImplementation extends Model {

	private StateLogic stateLogic;

	public ModelImplementation() {
		stateLogic = new StateLogic(this);
	}

	@Override
	public void update(Observable observable, Object data) {
		stateLogic.update(observable, data);
	}

	public Boat getBoat(BoatType boatType, Player player) {
		return stateLogic.getBoat(boatType, player);
	}

	public Direction getDirection() {
		return stateLogic.getDirection();
	}

	public ArrayList<Boat> getBoats() {
		return stateLogic.getBoats();
	}

	public Boat getNextBoatToPlace() {
		return stateLogic.getNextBoatToPlace();
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

	public void setStateChanged() {
		setChanged();
		notifyObservers();
	}
}