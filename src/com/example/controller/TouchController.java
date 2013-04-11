package com.example.controller;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.example.model.Position;

public class TouchController extends Controller {

	/*
	 * TODO: -Make method for calulating coordinates - CHECK ? -Scale with
	 * buttons -Handle touch on buttons
	 */
	Context ctx;
	private double windowWidth;
	private double windowHeight;

	/*
	 * window values from Activity
	 */
	public TouchController() {
		WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		this.windowWidth = display.getWidth();
		this.windowHeight = display.getHeight();
		System.out.println("Window width: " + windowWidth + "Window height: "
				+ windowHeight);
		
		
		
	}
	
	

	public void viewSwitchButtonPressed() {
		
		// TODO: Send statechange to model.

	}

	// Must be from touch listener in Activity
	public void gridGotTouched(int x, int y) {
		double realX = (x / windowWidth) * 100;
		double realY = (y / windowHeight) * 100;
		System.out.println("RealX: " + realX + "RealY: " + realY);
		
		/*
		 * sends the Position that was touched to the Model
		 */
		notifyObservers(returnCoord(realX, realY));
	}

	public Position returnCoord(double realX, double realY) {
		int row = 0;
		char column = 0;
		

		if (realX >= 0 && realX <= 10) {
			row = 0;
		} else if (realX > 10 && realX <= 20) {
			row = 1;
		} else if (realX > 20 && realX <= 30) {
			row = 2;
		} else if (realX > 30 && realX <= 40) {
			row = 3;
		} else if (realX > 40 && realX <= 50) {
			row = 4;
		} else if (realX > 50 && realX <= 60) {
			row = 5;
		} else if (realX > 60 && realX <= 70) {
			row = 6;
		} else if (realX > 70 && realX <= 80) {
			row = 7;
		} else if (realX > 80 && realX <= 90) {
			row = 8;
		} else if (realX > 90 && realX <= 100) {
			row = 9;
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

	
	public static void main(String[] args) {
		new TouchController();
	}
	// double windowWidth = getWindow().getDecorView().getWidth();
	// double windowHeight = getWindow().getDecorView().getHeight();
	// float xDensity = x;
	// double x = (xDensity / windowWidth) * 100;
	// float yDensity = x;
	// double y = (yDensity / windowHeight) * 100;

}
