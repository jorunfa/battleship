package com.example.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class Model extends Observable implements Observer {
	
	abstract public Boat getBoat(BoatType boatType, Player player);
	abstract public Direction getDirection();
	abstract public ArrayList<Boat> getBoats();
	abstract public ArrayList<Bomb> getPlacedBombs();
	abstract public Boat getNextBoatToPlace();
	abstract public Player getTurn();
	abstract public Stage getStage();
	abstract public boolean showChangingPlayersScreen();
	
	
}
