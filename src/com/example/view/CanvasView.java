package com.example.view;

import java.util.Observable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.example.battleship.R;
import com.example.model.Boat;
import com.example.model.BoatType;
import com.example.model.Bomb;
import com.example.model.Direction;
import com.example.model.Model;
import com.example.model.Orientation;
import com.example.model.Player;
import com.example.model.Position;
import com.example.model.Stage;
import com.example.starter.SurfaceViewActivity;

public class CanvasView extends SurfaceView implements View, SurfaceHolder.Callback {
	
	private SurfaceHolder surface;
	static Context context = SurfaceViewActivity.getAppContext();
	
	Paint paint = new Paint();
	private Display disp;
	private float dispWidth;
	private float dispHight;
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
    private Bitmap explosion;
    private Bitmap hitWater;
    private CoordinateCalculator coordinateCalculator;
    
    public CanvasView(){
		super(context);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		disp = wm.getDefaultDisplay();
		dispWidth = disp.getWidth();
		dispHight = disp.getHeight();
		m_GridWidth = dispWidth/10;
		m_GridHeight = dispWidth/10;
		surface = this.getHolder();
		getHolder().addCallback(this);
		initializeValues();
		coordinateCalculator = new CoordinateCalculator(m_GridWidth, m_GridHeight);
		
		Resources res = getResources();
		explosion = BitmapFactory.decodeResource(res, R.drawable.explosion);
		hitWater = 	BitmapFactory.decodeResource(res, R.drawable.water);
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
            	drawModel();
            }
        } finally {
            if (canvas != null) {
                surface.unlockCanvasAndPost(canvas);
            }
        }
	}

	private void drawModel() {
		drawBlank();
    	if (model == null) {
    		drawGrid();
    		drawChangeDirectionButton();
    	}
    	else if (model.showChangingPlayersScreen()) drawChangingPlayersScreen();
		else if (model.viewOwnShips()) drawOwnShips();
		else if (model.getStage() == Stage.GAME_OVER) drawGameOver();
		else if (model.getStage() == Stage.PLACE_BOMB) drawPlacingBombs();
		else if (model.getStage() == Stage.PLACE_BOATS) drawPlacingBoats();
	}

	private void drawBlank() {
		canvas.drawRGB(0, 0, 0);
	}

	private void drawChangingPlayersScreen() {
		drawTitleAndSubTitle(model.getTurn().toString()+"'s turn", "Press bottom 1/3 of screen to continue");
	}

	private void drawTitleAndSubTitle(String title, String subTitle) {
		float size = paint.getTextSize();
		int x =(int) (dispWidth * (1.0/10.0));
		paint.setTextSize(50);
		drawBlank();
		canvas.drawText(title, x, 100, paint);
		paint.setTextSize(20);
		canvas.drawText(subTitle, x, 150, paint);
		paint.setTextSize(size);
	}
	
	private void drawOwnShips() {
		// TODO Auto-generated method stub
		
	}

	private void drawGameOver() {
		String winnerString;
		Player winner = model.getWinner();
		if (winner == Player.PLAYER1) winnerString = "PLAYER1!";
		else winnerString = "PLAYER2!";
		drawTitleAndSubTitle("GAME OVER", "The winner is: " + winner);
	}

	private void drawPlacingBombs() {
		drawGrid();
		drawAllYourPlacedBombs();
	}

	private void drawAllYourPlacedBombs() {
		for (Bomb bomb : model.getPlacedBombs()) {
			if (bomb.getPlayerFiredAt().equals(model.getTurn())) continue;
			if (model.bombHitShip(bomb)) {
				drawBombThatHit(bomb);
			}
			else {
				drawBombThatMissed(bomb);
			}
		}
	}
	
	private void drawBombThatHit(Bomb bomb) {
		drawBombFromBitmap(bomb, explosion);
	}
	
	private void drawBombThatMissed(Bomb bomb) {
		drawBombFromBitmap(bomb, hitWater);
	}
	
	private void drawBombFromBitmap(Bomb bomb, Bitmap boatBitmap) {
		Orientation orientation = new Orientation(bomb.getPosition(), Direction.RIGHT);
		Rect destinationToDrawTo = coordinateCalculator.calculateDestinationRect(orientation, 1);
		canvas.drawBitmap(boatBitmap, null, destinationToDrawTo, paint);
	}

	private void drawPlacingBoats() {
		drawGrid();
		drawAllYourPlacedBoats();
		drawNextBoatToPlace();
		drawChangeDirectionButton();
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
		drawBoat(boat, boat.getPosition().getColumn(), boat.getPosition().getRow(), boat.getDirection());
	}

	private void drawBoat(Boat boat, int column, char row, Direction direction) {
		BoatType type = boat.getType();
		Bitmap boatBitmap = getBoatBitmap(type);
		Rect destinationToDrawTo = coordinateCalculator.calculateDestinationRect(column,
				row, direction, boat.getLength());
		if (direction == Direction.RIGHT)
			canvas.drawBitmap(boatBitmap, null, destinationToDrawTo, null);
		else {
			drawFLipped(boatBitmap, destinationToDrawTo);
		}
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
	
	private void drawFLipped(Bitmap bitmap, Rect destinationToDrawTo) {
		Matrix matrix = new Matrix();
		matrix.postRotate(90);
		Bitmap rotated = Bitmap.createBitmap(bitmap, 0, 0, 
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		canvas.drawBitmap(rotated, null, destinationToDrawTo, null);
	}

	private void drawNextBoatToPlace() {
		Boat boat = model.getNextBoatToPlace();
		Direction direction = model.getDirection();
		drawBoat(boat, 1, 'o', direction);
	}
	
	private void drawChangeDirectionButton() {
		float size = paint.getTextSize();
		paint.setTextSize(20);
		int y = (int) (dispHight * (2.0/3.0));
		int x = (int) (dispWidth * (1.0/10.0));
		canvas.drawText("Press her to change boat placing direction", x, y, paint);
		paint.setTextSize(size);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	    tryToDrawModel();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
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
