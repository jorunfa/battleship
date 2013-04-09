package com.example.model;

import java.util.Observer;

public interface Model extends Observer {
	public void gameboardPressed(Posistion pos);
	public void pressedButton(int b);
}
