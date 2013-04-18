package com.example.view;

import java.util.Observable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View.OnTouchListener;

public class CanvasView extends SurfaceView implements View, SurfaceHolder.Callback, Runnable {
	
	SurfaceHolder surface;
	public CanvasView(Context context){
		super(context);
		surface = getHolder();
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Thread drawThread = new Thread(this);
	    drawThread.start();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		Canvas c;
	    while (true) {
	        c = null;
			try {
	            c = surface.lockCanvas(null);
	            synchronized (surface) {
	                draw(c);
	 
	            }
	        } finally {
	            // do this in a finally so that if an exception is thrown
	            // during the above, we don't leave the Surface in an
	            // inconsistent state
	            if (c != null) {
	                surface.unlockCanvasAndPost(c);
	            }
	        }
	    }	
	}
	
	int x = 10;
	public void onDraw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		 
	    Paint p = new Paint();
	    p.setColor(Color.RED);
	    canvas.drawCircle(x, 10, 10, p);
	    x += 1;
	}

}
