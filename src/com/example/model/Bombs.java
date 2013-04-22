package com.example.model;

import java.util.ArrayList;

public class Bombs {

	private ArrayList<Bomb> bombs;
	
	public Bombs() {
		bombs = new ArrayList<Bomb>();
	}

	public void placeBomb(Position position, Player player) {
		Bomb bomb = new Bomb(position, player);
		bombs.add(bomb);
	}

	public boolean leagalPlacementOfBomb(Position position, Player player) {
		Bomb testBomb = new Bomb(position, player);
		return !bombs.contains(testBomb);
	}
}
