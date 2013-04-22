package com.example.model;

public class Position {

	private int column;
	private char row;
	
	public Position(int column, char row) {
		if (leagalValues(column, row)) {
			this.setRow(row);
			this.setColumn(column);
		}
		else {
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
		}

	}

	private boolean leagalValues(int column, char row) {
		if (column >= 1 && column <= 10 && row >= 'a' && row <= 'j') return true;
		else return false;
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
	
	@Override
	public String toString() {
		return "" + column + ", " + row + ".";
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
