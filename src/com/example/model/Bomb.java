package com.example.model;

public class Bomb {

	private Position position;
	private Player playerFiredAt;
	
	public Bomb(Position position, Player player) {
		this.position = position;
		playerFiredAt = player;
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Bomb)) return false;
		Bomb bomb = (Bomb) object;
		if (!bomb.getPosition().equals(this.getPosition())) return false;
		if (!bomb.getPlayerFiredAt().equals(this.getPlayerFiredAt())) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return position.toString() + ", " + playerFiredAt.toString();
	}
	
	public Position getPosition() {
		return position;
	}
	
	public Player getPlayerFiredAt() {
		return playerFiredAt;
	}

}
