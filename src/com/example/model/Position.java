package com.example.model;

public class Position {
	
	private int row;
	private char column;
	public Position(int row, char column){
		this.row = row;
		this.column = column;
		
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public char getColumn() {
		return column;
	}
	public void setColumn(char column) {
		this.column = column;
	}

	
	
}
