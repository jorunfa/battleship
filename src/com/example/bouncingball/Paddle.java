package com.example.bouncingball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Paddle extends GameObject {

	final Bitmap paddleBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.paddle);
	
	public Paddle(Context context) {
		super(context);
		dX = 0;
		dY = 0;
		ball = paddleBitmap;
		ballW = ball.getWidth();
        ballH = ball.getHeight();
	}
	
	public Bitmap getBitmap() {
		return ball;
	}
	
	public void reverseXSpeedAndChangeHeliDirectionBitmap() {
    	dX = (-1)*dX;
    }
	
}