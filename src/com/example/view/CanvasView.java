package com.example.view;

import java.util.Observable;

import com.example.starter.SurfaceViewActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View.OnTouchListener;
import android.view.WindowManager;

public class CanvasView extends SurfaceView implements View, SurfaceHolder.Callback, Runnable {
	
	SurfaceHolder surface;
	static Context context = SurfaceViewActivity.getAppContext();
	
	Paint paint = new Paint();
	private float dispWidth;
	public float m_width;
    public float m_Height;
    public float m_NoOfRows;
    public float m_NoOfCols;
    public float m_XOffset;
    public float m_YOffset;
    public static final float DEFAULT_X_OFFSET= 0;
    public static final float DEFAULT_Y_OFFSET= 0;
    public static final float DEFAULT_NO_ROWS = 10;
    public static final float DEFAULT_NO_COLS=  10;
    
	public CanvasView(){
		super(context);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		dispWidth = disp.getWidth();
		m_width = dispWidth/10;
		m_Height = dispWidth/10;
		surface = this.getHolder();
		getHolder().addCallback(this);
		InitializeValues();
		System.out.println("Er i canvas view");
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		System.out.println("Changed...");
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
	
	public void draw(Canvas canvas){
		
		paint.setColor(Color.RED);
        float X=DEFAULT_X_OFFSET;
        float Y=DEFAULT_Y_OFFSET;
        //Draw The rows
        for(float iRow=0;iRow<=m_NoOfRows;iRow++)
        {
                canvas.drawLine(X, Y,X+ this.m_width* this.m_NoOfCols,Y, paint);
                Y=Y+ m_Height;

        }
        

        //Draw The Cols
        X=DEFAULT_X_OFFSET;
        Y=DEFAULT_Y_OFFSET;
        for(float iColumn=0;iColumn<=m_NoOfCols;iColumn++)
        {
                canvas.drawLine(X, Y,X,Y+this.m_Height*this.m_NoOfRows,paint );
                X=X+ this.m_width;
        }
		
	}
	
	private void InitializeValues()
    {
         //Put all the default values
        m_NoOfRows=DEFAULT_NO_ROWS;
        m_NoOfCols=DEFAULT_NO_COLS;
        m_XOffset=DEFAULT_X_OFFSET;
        m_YOffset=DEFAULT_Y_OFFSET;
    }


}
