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

	public boolean leagalPlacementOfBomb(Position position, Player firedAt) {
		Bomb testBomb = new Bomb(position, firedAt);
		for (Bomb bomb : getPlacedBombs()) {
			if (bomb.equals(testBomb)) return false;
		}
		return true;
	}
	
	public ArrayList<Bomb> getPlacedBombs() {
		return placedBombs;
	}
}
