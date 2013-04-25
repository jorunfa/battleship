package com.example.controller;


import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import com.example.model.Button;
import com.example.model.Position;
import com.example.starter.SurfaceViewActivity;

public class TouchController extends Controller {

	/*
	 * TODO: -Make method for calulating coordinates - CHECK ? -Scale with
	 * buttons 
	 * -Handle touch on buttons
	 */
	private double gridWidth;
	private double gridHeight;

	/*
	 * window values from Activity
	 */
	public TouchController() {
		try {			
			WindowManager wm = (WindowManager) SurfaceViewActivity.getAppContext().getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			this.gridWidth = display.getWidth();
			this.gridHeight = display.getWidth();
		} catch (Exception e) {
			/*
			 * This is for making the test able to run..
			 */
			this.gridWidth = 480;
			this.gridHeight = 480;
		}
	}
	
	

	public void viewSwitchButtonPressed() {
		
		// TODO: Send statechange to model.

	}

	// Must be from touch listener in Activity
	public void gridGotTouched(int x, int y) {
		double realX = (x / gridWidth) * 100;
		double realY = (y / gridHeight) * 100;
		
		if (realY <= 100){
			setChanged();
			System.out.println("returnCoord(realX, realY) " + returnCoord(realX, realY));
			notifyObservers(returnCoord(realX, realY));
		}
		else{
			setChanged();
			notifyObservers(Button.CHANGING_PLAYERS_PAUSESCREEN_NEXT);
			setChanged();
			notifyObservers(Button.CHANGE_DIRECTION);
		}
	}

	public Position returnCoord(double realX, double realY) {
		int row = 0;
		char column = 0;
		

		if (realX >= 0 && realX <= 10) {
			row = 1;
		} else if (realX > 10 && realX <= 20) {
			row = 2;
		} else if (realX > 20 && realX <= 30) {
			row = 3;
		} else if (realX > 30 && realX <= 40) {
			row = 4;
		} else if (realX > 40 && realX <= 50) {
			row = 5;
		} else if (realX > 50 && realX <= 60) {
			row = 6;
		} else if (realX > 60 && realX <= 70) {
			row = 7;
		} else if (realX > 70 && realX <= 80) {
			row = 8;
		} else if (realX > 80 && realX <= 90) {
			row = 9;
		} else if (realX > 90 && realX <= 100) {
			row = 10;
		}

		if (realY >= 0 && realY <= 10) {
			column = 'a';
		} else if (realY > 10 && realY <= 20) {
			column = 'b';
		} else if (realY > 20 && realY <= 30) {
			column = 'c';
		} else if (realY > 30 && realY <= 40) {
			column = 'd';
		} else if (realY > 40 && realY <= 50) {
			column = 'e';
		} else if (realY > 50 && realY <= 60) {
			column = 'f';
		} else if (realY > 60 && realY <= 70) {
			column = 'g';
		} else if (realY > 70 && realY <= 80) {
			column = 'h';
		} else if (realY > 80 && realY <= 90) {
			column = 'i';
		} else if (realY > 90 && realY <= 100) {
			column = 'j';
		}
		return new Position(row, column);
	}
}
