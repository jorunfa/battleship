package com.example.model;

import java.util.ArrayList;

public class Bombs {

	private ArrayList<Bomb> placedBombs;
	
	public Bombs() {
		placedBombs = new ArrayList<Bomb>();
	}

	public void placeBomb(Position position, Player player) {
		Bomb bomb = new Bomb(position, player);
		placedBombs.add(bomb);
	}

	public boolean leagalPlacementOfBomb(Position position, Player player) {
		Bomb testBomb = new Bomb(position, player);
		return !placedBombs.contains(testBomb);
	}
	
	public ArrayList<Bomb> getPlacedBombs() {
		return placedBombs;
	}
}
