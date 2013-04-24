package com.example.view;

import java.util.Observable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.example.battleship.R;
import com.example.model.Boat;
import com.example.model.BoatType;
import com.example.model.Direction;
import com.example.model.Model;
import com.example.model.Player;
import com.example.model.Position;
import com.example.model.Stage;
import com.example.starter.SurfaceViewActivity;

public class CanvasView extends SurfaceView implements View, SurfaceHolder.Callback, Runnable {
	
	SurfaceHolder surface;
	static Context context = SurfaceViewActivity.getAppContext();
	
	Paint paint = new Paint();
	private Display disp;
	private float dispWidth;
	public float m_GridWidth;
    public float m_GridHeight;
    public float m_NoOfRows;
    public float m_NoOfCols;
    public float m_XOffset;
    public float m_YOffset;
	private Model model;
	private Canvas canvas;
    public static final float DEFAULT_X_OFFSET= 0;
    public static final float DEFAULT_Y_OFFSET= 0;
    public static final float DEFAULT_NO_ROWS = 10;
    public static final float DEFAULT_NO_COLS=  10;
    
    public CanvasView(){
		super(context);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		disp = wm.getDefaultDisplay();
		dispWidth = disp.getWidth();
		m_GridWidth = dispWidth/10;
		m_GridHeight = dispWidth/10;
		surface = this.getHolder();
		getHolder().addCallback(this);
		initializeValues();	
	}
	
	private void initializeValues() {
       m_NoOfRows=DEFAULT_NO_ROWS;
       m_NoOfCols=DEFAULT_NO_COLS;
       m_XOffset=DEFAULT_X_OFFSET;
       m_YOffset=DEFAULT_Y_OFFSET;
   }
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof Model) {
			this.model = (Model) arg0;
			tryToDrawModel();
		}
	}

	private void tryToDrawModel() {
		canvas = null;
		try {
            canvas = surface.lockCanvas(null);
            synchronized (surface) {
            	drawGrid();
        		if (model.showChangingPlayersScreen()) drawChangingPlayersScreen();
        		else if (model.viewOwnShips()) drawOwnShips();
        		else if (model.getStage() == Stage.GAME_OVER) drawGameOver();
        		else if (model.getStage() == Stage.PLACE_BOMB) drawPlacingBombs();
        		else if (model.getStage() == Stage.PLACE_BOATS) drawPlacingBoats();
            }
        } finally {
            if (canvas != null) {
                surface.unlockCanvasAndPost(canvas);
            }
        }
	}

	private void drawChangingPlayersScreen() {
		// TODO Auto-generated method stub
		
	}

	private void drawOwnShips() {
		// TODO Auto-generated method stub
		
	}

	private void drawGameOver() {
		// TODO Auto-generated method stub
		
	}

	private void drawPlacingBombs() {
		// TODO Auto-generated method stub
		
	}

	private void drawPlacingBoats() {
		drawAllYourPlacedBoats();
		drawNextBoatToPlace();
	}

	private void drawAllYourPlacedBoats() {
		drawAllBoatsForPlayer(model.getTurn());
	}
	
	private void drawAllBoatsForPlayer(Player player) {
		for (Boat boat : model.getBoats()) {
			if (!boat.isPlaced()) continue;
			if (boat.getPlayer() != player) continue;
			drawBoat(boat);
		}
	}

	private void drawBoat(Boat boat) {
		Position position = boat.getPosition();
		BoatType type = boat.getType();
		Direction direction = boat.getDirection();
		Bitmap boatBitmap = getBoatBitmap(type);
		Rect destinationToDrawTo = calculateDestinationRect(position, type, direction);
		canvas.drawBitmap(boatBitmap, null, destinationToDrawTo, null);
	}

	private Bitmap getBoatBitmap(BoatType type) {
		Resources res = getResources();
		if (type == BoatType.AIRCRAFT_CARRIER)
			return BitmapFactory.decodeResource(res, R.drawable.aircraft_carrier);
		if (type == BoatType.BATTLESHIP)
			return BitmapFactory.decodeResource(res, R.drawable.battleship);
		if (type == BoatType.DESTROYER)
			return BitmapFactory.decodeResource(res, R.drawable.destroyer);
		if (type == BoatType.PATROL_BOAT)
			return BitmapFactory.decodeResource(res, R.drawable.patrolboat);
		if (type == BoatType.SUBMARINE)
			return BitmapFactory.decodeResource(res, R.drawable.submarine);
		else return null;
	}
	
	private Rect calculateDestinationRect(Position position, BoatType type,	Direction direction) {
		//return new Rect(left, top, right, bottom);
		// TODO make correct rect
		return new Rect(0, 0, 978, 264);
	}

	private void drawNextBoatToPlace() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
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
		int column = position.getColumn(); 
		coordX = (int) (m_GridWidth * (column-1));
		
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
		tryToDrawGrid();
	}
	
	private void tryToDrawGrid() {
		canvas = null;
		try {
            canvas = surface.lockCanvas(null);
            synchronized (surface) {
                drawGrid();
            }
        } finally {
            if (canvas != null) {
                surface.unlockCanvasAndPost(canvas);
            }
        }
	}

	private void drawGrid() {
		paint.setColor(Color.RED);
        float X=DEFAULT_X_OFFSET;
        float Y=DEFAULT_Y_OFFSET;
        //Draw The rows
        for(float iRow=0;iRow<=m_NoOfRows;iRow++) {
                canvas.drawLine(X, Y,X+ this.m_GridWidth* this.m_NoOfCols,Y, paint);
                Y=Y+ m_GridHeight;
        }
        
        //Draw The Cols
        X=DEFAULT_X_OFFSET;
        Y=DEFAULT_Y_OFFSET;
        for(float iColumn=0;iColumn<=m_NoOfCols;iColumn++) {
                canvas.drawLine(X, Y,X,Y+this.m_GridHeight*this.m_NoOfRows,paint );
                X=X+ this.m_GridWidth;
        }
	}
}
