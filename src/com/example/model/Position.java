package com.example.model;

public class Position {
	
	private int column;
	private char row;
	public Position(int column, char row){
		this.setRow(row);
		this.setColumn(column);
		
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Position)) return false;
		Position pos = (Position)object;
		if (pos.getRow() == this.getRow()) {
			if (pos.getColumn() == this.getColumn()) {
				return true;
			}
		}
		return false;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public char getRow() {
		return row;
	}

	public void setRow(char row) {
		this.row = row;
	}
	
}
