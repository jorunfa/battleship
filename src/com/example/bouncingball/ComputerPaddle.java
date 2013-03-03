package com.example.bouncingball;

import android.content.Context;

public class ComputerPaddle extends Paddle {
	
	Ball movingBall;
	int PADDLE_SPEED = 10;
	
	public ComputerPaddle(Context context, Ball ball) {
		super(context);
		this.movingBall = ball;
	}
	
	public void calculateHeliPosistion() {
		setSpeedToGetToMovingBall();
        ballY += (int)dY; //Increase or decrease vertical position.
    }
	
	public void setSpeedToGetToMovingBall() {
		int ballPosistion = movingBall.Y();
		if (ballPosistion > ballY) {
			dY = PADDLE_SPEED;
		}
		else {
			dY = -PADDLE_SPEED;
		}
	}
	
	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
    	ballX = screenW - ballW*2;
    	ballY = screenH/2;
    }
}
