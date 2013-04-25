package com.example.view;

import android.graphics.Rect;

import com.example.model.Direction;
import com.example.model.Orientation;
import com.example.model.Position;

public class CoordinateCalculator {
	
	private float m_GridWidth;
	private float m_GridHeight;
	
	public CoordinateCalculator(float m_GridWidth, float m_GridHeight) {
		this.m_GridWidth = m_GridWidth;
		this.m_GridHeight = m_GridHeight;
	}

	public Rect calculateDestinationRect(Orientation orientation, int length) {
		return calculateDestinationRect(orientation.getPosistion().getColumn(),
				orientation.getPosistion().getRow(), orientation.getDirection(), length);
	}

	public Rect calculateDestinationRect(int column, char row,	Direction direction, int length) {
		if (direction == Direction.RIGHT) {
			return makeRightRect(column, row, length);
		}
		else {
			return makeDownwardRect(column, row, length);
		}
	}

	public Rect makeRightRect(Position position, int length) {
		return makeRightRect(position.getColumn(), position.getRow(), length);
	}

	public Rect makeRightRect(int column, char row, int length) {
		int left = getGridLeftCoordinate(column);
		int top = getGridTopCoordinate(row);
		int rightMostColumn = column + length - 1;
		int right = getGridRightCoordinate(rightMostColumn);
		int bottom = getGridBottomCoordinate(row);
		return new Rect(left, top, right, bottom);
	}

	public Rect makeDownwardRect(Position position, int length) {
		return makeDownwardRect(position.getColumn(), position.getRow(), length);
	}
	
	private Rect makeDownwardRect(int column, char row, int length) {
		int left = getGridLeftCoordinate(column);
		int bottom = getGridBottomCoordinate(row);
		int right = getGridRightCoordinate(column);
		char topMostColumn = (char) (row - length + 1);
		int top = getGridTopCoordinate(topMostColumn);
		return new Rect(left, top, right, bottom);
	}

	public int getGridLeftCoordinate(int columnNumber){ 
		return (int) (m_GridWidth * (columnNumber-1));
	}
	
	public int getGridTopCoordinate(char row) {
		return (int) ((row - 'a') * m_GridHeight);
	}
	
	public int getGridRightCoordinate(int columnNumber) {
		return (int) (getGridLeftCoordinate(columnNumber) + m_GridWidth);
	}
	
	public int getGridBottomCoordinate(char row) {
		return (int) (getGridTopCoordinate(row) + m_GridHeight);
	}
	
}
