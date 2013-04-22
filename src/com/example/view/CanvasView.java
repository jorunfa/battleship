package com.example.view;

import java.util.Observable;

import com.example.starter.SurfaceViewActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View.OnTouchListener;

public class CanvasView extends SurfaceView implements View, SurfaceHolder.Callback, Runnable {
	
	SurfaceHolder surface;
	static Context context = SurfaceViewActivity.getAppContext();
	public CanvasView(){
		super(context);
		surface = this.getHolder();
		getHolder().addCallback(this);
		System.out.println("Er i canvas view");
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
		System.out.println("er i surface created");
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
	public void draw(Canvas canvas){
		System.out.println("Paa toppen av onDraw");
		canvas.drawColor(Color.BLACK);
		
	    Paint p = new Paint();
	    p.setColor(Color.RED);
	    canvas.drawCircle(x, 150, 10, p);
	    x += 10;
	    System.out.println("Paa bunn av onDraw");
	}

}
