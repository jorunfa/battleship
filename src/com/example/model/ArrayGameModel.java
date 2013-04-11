package com.example.model;

import java.util.ArrayList;
import java.util.Observable;

public class ArrayGameModel extends Model {
	
	private ModelTurn turn;
	private ModelStage stage;
	private ArrayList<Boat> boats;
	
	public ArrayGameModel() {
		turn = ModelTurn.PLAYER1;
		stage = ModelStage.PLACE_BOATS;
		boats = new ArrayList<Boat>();
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameboardPressed(Position pos, Direction direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pressedButton(int b) {
		// TODO Auto-generated method stub
		
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
}
