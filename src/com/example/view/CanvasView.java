package com.example.view;

import java.util.Observable;

import com.example.model.Model;
import com.example.model.ModelImplementation;
import com.example.model.Player;
import com.example.model.Position;
import com.example.model.Stage;
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
	ModelImplementation model;
	private Display disp;
	private float dispWidth;
	public float m_GridWidth;
    public float m_GridHeight;
    public float m_NoOfRows;
    public float m_NoOfCols;
    public float m_XOffset;
    public float m_YOffset;
    public static final float DEFAULT_X_OFFSET= 0;
    public static final float DEFAULT_Y_OFFSET= 0;
    public static final float DEFAULT_NO_ROWS = 10;
    public static final float DEFAULT_NO_COLS=  10;
    
    public CanvasView(ModelImplementation model){
		super(context);
		this.model = model;
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		disp = wm.getDefaultDisplay();
		dispWidth = disp.getWidth();
		m_GridWidth = dispWidth/10;
		m_GridHeight = dispWidth/10;
		surface = this.getHolder();
		getHolder().addCallback(this);
		InitializeValues();
		System.out.println("Er i canvas view");
		
		
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (model.getStage() == Stage.PLACE_BOATS) {
			if(model.getTurn() == Player.PLAYER1){
				System.out.println("PLAYER ONES TUUUURN");
			}
			else if (model.getTurn() == Player.PLAYER2) {
				System.out.println("PLAYER TWOS TUUUUUURN");
			}
		}
		else System.out.println("DIDNT WORK");
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
	
	public void getCorrectGridCoordinates(Position position){
		int coordX;
		int coordY;
		switch (position.getColumn()) {
		case 1:
			coordX = 0;
			break;
		case 2:
			coordX = (int) m_GridWidth;
			break;
		case 3:
			coordX = (int) (m_GridWidth*2);
			break;
		case 4:
			coordX = (int) (m_GridWidth*3);
			break;
		case 5:
			coordX = (int) (m_GridWidth*4);
			break;
		case 6:
			coordX = (int) (m_GridWidth*5);
			break;
		case 7:
			coordX = (int) (m_GridWidth*6);
			break;
		case 8:
			coordX = (int) (m_GridWidth*7);
			break;
		case 9:
			coordX = (int) (m_GridWidth*8);
			break;
		case 10:
			coordX = (int) (m_GridWidth*9);
			break;
		default:
			break;
		}
		
		switch (position.getRow()) {
		case 'a':
			coordY = 0;
			break;
		case 'b':
			coordY = (int) m_GridHeight;
			break;
		case 'c':
			coordY = (int) (m_GridHeight*2);
			break;
		case 'd':
			coordY = (int) (m_GridHeight*3);
			break;
		case 'e':
			coordY = (int) (m_GridHeight*4);
			break;
		case 'f':
			coordY = (int) (m_GridHeight*5);
			break;
		case 'g':
			coordY = (int) (m_GridHeight*6);
			break;
		case 'h':
			coordY = (int) (m_GridHeight*7);
			break;
		case 'i':
			coordY = (int) (m_GridHeight*8);
			break;
		case 'j':
			coordY = (int) (m_GridHeight*9);
			break;
		default:
			break;
		}
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
                canvas.drawLine(X, Y,X+ this.m_GridWidth* this.m_NoOfCols,Y, paint);
                Y=Y+ m_GridHeight;

        }
        

        //Draw The Cols
        X=DEFAULT_X_OFFSET;
        Y=DEFAULT_Y_OFFSET;
        for(float iColumn=0;iColumn<=m_NoOfCols;iColumn++)
        {
                canvas.drawLine(X, Y,X,Y+this.m_GridHeight*this.m_NoOfRows,paint );
                X=X+ this.m_GridWidth;
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
