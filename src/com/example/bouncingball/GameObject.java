package com.example.bouncingball;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;

public class GameObject extends View {
	
	int ballX; //Ball x position.
    int ballY; //Ball y position.
    int initialY;
    float dY; //Ball vertical speed.
    float dX; //Ball vertical speed.
    int ballW;
    int ballH;
    float acc;
	int screenW; //Device's screen width.
    int screenH; //Devices's screen height.
    long animationTimer = System.currentTimeMillis();
    long timeNow = System.currentTimeMillis();
    Bitmap ball;
    Matrix matrix;
    Canvas canvas;
    Random generator;
    
    final Bitmap HELI_1 = BitmapFactory.decodeResource(getResources(),R.drawable.heli_1);
    final Bitmap HELI_2 = BitmapFactory.decodeResource(getResources(),R.drawable.heli_2);
    final Bitmap HELI_F_1 = BitmapFactory.decodeResource(getResources(),R.drawable.heli_f_1);
    final Bitmap HELI_F_2 = BitmapFactory.decodeResource(getResources(),R.drawable.heli_f_2);
	
	public GameObject(Context context) {
		super(context);
		ball = HELI_1;
		ballW = ball.getWidth();
        ballH = ball.getHeight();
        generator = new Random();
        
        animationTimer = System.currentTimeMillis();
        
        //Initialise animation variables.
        acc = 0.0f; //Acceleration
        dY = generator.nextInt(10)+1; //vertical speed
        dX = generator.nextInt(10)+1;
        ballX = generator.nextInt(500)+1;
        ballY = generator.nextInt(700)+1;;
	}
	
    public void calculateHeliPosistion() {
        ballY += (int)dY; //Increase or decrease vertical position.
        ballX += (int)dX;
        reverseYSpeedIfHitBottomOrTop();
        reverseXSpeedAndChangeDirectionIfHitSides();
        dY+= acc; //Increase or decrease speed.
    }
    
    public void reverseYSpeedIfHitBottomOrTop() {
    	if (ballY > (screenH - ballH)) {
            dY=(-1)*dY;
        }
    	if (ballY <= 0) {
    		dY = (-1)*dY;
    	}
    }

    public void reverseXSpeedAndChangeDirectionIfHitSides() {
    	if (ballX + ballW > screenW || ballX <= 0) {
    		reverseXSpeedAndChangeHeliDirectionBitmap();
    	}
    }
    
    public void reverseXSpeedAndChangeHeliDirectionBitmap() {
    	dX = (-1)*dX;
    	changeHeliDirectionBitmap();
    }
    
    public void changeHeliDirectionBitmap() {
    	if (dX < 0) {
    		ball = HELI_F_1;
    	}
    	else {
    		ball = HELI_1;	
    	}
    }
    
    public void maybeChangeHelicopterBitmapToMakeAnimation() {
    	timeNow = System.currentTimeMillis();
    	if (timeNow - animationTimer > 100) {
    		changeHeliFrom1To2BitmapOr2To1();
    		animationTimer = timeNow;
    	}
    }
    
    public int X() {
    	return ballX;
    }
    
    public int Y() {
    	return ballY;
    }
    
    public Bitmap getBitmap() {
    	maybeChangeHelicopterBitmapToMakeAnimation();
    	return ball;
    }
    
    public void changeHeliFrom1To2BitmapOr2To1() {
    	if (ball.equals(HELI_1)) {
    		ball = HELI_2;
    	}
    	else if (ball.equals(HELI_2)) {
    		ball = HELI_1;
    	}
    	else if (ball.equals(HELI_F_1)) {
    		ball = HELI_F_2;
    	}
    	else if (ball.equals(HELI_F_2)) {
    		ball = HELI_F_1;
    	}
    }
    
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
    	screenW = w;
        screenH = h;
    }
    
    public int getLeftSideX() {
    	return X();
    }
    
    public int getRightSideX() {
    	return X()+ballW;
    }
    
    public void collision() {
    	reverseXSpeedAndChangeHeliDirectionBitmap();
    }

    public int getTopY() {
		return Y();
	}
    
    public int getBottomY() {
		return Y()+ballH;
	}
}
