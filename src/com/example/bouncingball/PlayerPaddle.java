package com.example.bouncingball;

import android.content.Context;
import android.view.MotionEvent;

public class PlayerPaddle extends Paddle implements TouchObserver {
	boolean ballFingerMove;

	public PlayerPaddle(Context context) {
		super(context);
	}
	
	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
    	ballX = ballW*2;
    	ballY = screenH/2;
    }
	
	public void notifyMotionEvent(MotionEvent ev) {
		switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN: {
            ballX = (int) ev.getX() - ballW/2;
            ballY = (int) ev.getY() - ballH/2;
            ballFingerMove = true;
            break;
        }

        case MotionEvent.ACTION_MOVE: {
            ballX = (int) ev.getX() - ballW/2;
            ballY = (int) ev.getY() - ballH/2;
            break;
        }

        case MotionEvent.ACTION_UP:
            ballFingerMove = false;
            dY = 0;
            break;
        }
	}
}
