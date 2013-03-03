package com.example.bouncingball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Ball extends GameObject {
	
	final Bitmap ballBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
	int BALL_SPEED = 12;
	private BallBounces ballBounces;
	
	public Ball(Context context, BallBounces ballBounces) {
		super(context);
		this.ballBounces = ballBounces;
		ball = ballBitmap;
		ballW = ball.getWidth();
        ballH = ball.getHeight();
        dY = BALL_SPEED;
	}
	
	public void calculateHeliPosistion() {
        ballY += (int)dY; //Increase or decrease vertical position.
        ballX += (int)dX;
        reverseYSpeedIfHitBottomOrTop();
        reportScoreIfHitSidesAndResetPosistion();
        dY+= acc; //Increase or decrease speed.
    }
	
	public void reportScoreIfHitSidesAndResetPosistion() {
		if (ballX + ballW > screenW || ballX <= 0) {
			if (ballX <= 0) {
				reportPointComputer();
			}
			else {
				reportPointPlayer();
			}
			resetPosistion();
			dX = (-1)*dX;
    	}
	}
	
	public void reportPointComputer() {
		ballBounces.scoreComputer++;
	}
	
	public void reportPointPlayer() {
		ballBounces.scorePlayer++;
	}
	
	public void resetPosistion() {
		ballX = screenW/2;
		ballY = screenH/2;
	}
	
	public Bitmap getBitmap() {
		return ball;
	}
	
	public void reverseXSpeedAndChangeHeliDirectionBitmap() {
    	dX = (-1)*dX;
    }
}
