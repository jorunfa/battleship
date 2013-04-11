package com.example.model;

import java.util.Observable;
import java.util.Observer;

public abstract class Model extends Observable implements Observer {
	public abstract void gameboardPressed(Position pos, Direction direction);
	public abstract void pressedButton(int b);
}
