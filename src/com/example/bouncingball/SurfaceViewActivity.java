package com.example.bouncingball;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewActivity extends Activity {
    BallBounces ball;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ball = new BallBounces(this);
        setContentView(ball);
    }
}


class BallBounces extends SurfaceView implements SurfaceHolder.Callback {
    GameThread thread;
    int bgrW;
    int bgrH;
    Bitmap bgr;
    Canvas canvas;
    Matrix matrix;
    GameObject ball1;
    GameObject ball2;
    GameObject ball3;
    CollisionHandler collisionHandler;
    ArrayList<TouchObserver> touchObservers;

    //Measure frames per second.
    long now;
    int framesCount=0;
    int framesCountAvg=0;
    long framesTimer=0;
    Paint fpsPaint=new Paint();

    //Frame speed
    long timeNow;
    long timePrev = 0;
    long timePrevFrame = 0;
    long timeDelta;
    
    int scorePlayer = 0;
    int scoreComputer = 0;
    
    public BallBounces(Context context) {
        super(context);
        ball1 = new PlayerPaddle(context);
        ball2 = new Ball(context, this);
        ball3 = new ComputerPaddle(context, (Ball)ball2);
        collisionHandler = CollisionHandler.getInstance();
        collisionHandler.add(ball1);
        collisionHandler.add(ball2);
        collisionHandler.add(ball3);
        touchObservers = new ArrayList<TouchObserver>();
        touchObservers.add((TouchObserver) ball1);
        bgr = BitmapFactory.decodeResource(getResources(),R.drawable.sky_bgr); //Load a background.

        fpsPaint.setTextSize(30);
        fpsPaint.setARGB(254, 254, 254, 254);

        //Set thread
        getHolder().addCallback(this);

        setFocusable(true);
    }

    @Override
    public void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        
        bgr = Bitmap.createScaledBitmap(bgr, w, h, true); //Scale background to fit the screen.
        bgrW = bgr.getWidth();
        bgrH = bgr.getHeight();
        
        ball1.onSizeChanged(w, h, oldw, oldh);
        ball2.onSizeChanged(w, h, oldw, oldh);
        ball3.onSizeChanged(w, h, oldw, oldh);
        //This event-method provides the real dimensions of this custom view.
    }
    
    @Override
    public synchronized boolean onTouchEvent(MotionEvent ev) {
    	notifyTouchObservers(ev);
        return true;
    }
    
    private void notifyTouchObservers(MotionEvent ev) {
    	for (int i = 0; i < touchObservers.size(); i++) {
    		touchObservers.get(i).notifyMotionEvent(ev);
    	}
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        drawBackground();
        collisionHandler.checkAndHandleCollisions();
        ball1.calculateHeliPosistion();
        ball2.calculateHeliPosistion();
        ball3.calculateHeliPosistion();
        drawHelicopterToScreen(ball1);
        drawHelicopterToScreen(ball2);
        drawHelicopterToScreen(ball3);
        drawScoreToScreen();
    }
    
    public void drawBackground() {
        canvas.drawARGB(254, 0, 0, 0);
    }
    
    public void drawHelicopterToScreen(GameObject ball) {
    	matrix = new Matrix();
    	matrix.postTranslate(ball.X(), ball.Y()); //Move it into x, y position.
        canvas.drawBitmap(ball.getBitmap(), matrix, null); //Draw the ball with applied matrix.
    }
    
    public void drawScoreToScreen() {
	    canvas.drawText("Player: " + scorePlayer + ", Computer: " + scoreComputer + ".", 40, 70, fpsPaint);
   }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new GameThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }


    class GameThread extends Thread {
        private SurfaceHolder surfaceHolder;
        private BallBounces gameView;
        private boolean run = false;

        public GameThread(SurfaceHolder surfaceHolder, BallBounces gameView) {
            this.surfaceHolder = surfaceHolder;
            this.gameView = gameView;
        }

        public void setRunning(boolean run) {
            this.run = run;
        }

        public SurfaceHolder getSurfaceHolder() {
            return surfaceHolder;
        }

        @Override
        public void run() {
            Canvas c;
            while (run) {
                c = null;

                //limit frame rate to max 60fps
                timeNow = System.currentTimeMillis();
                timeDelta = timeNow - timePrevFrame;
                if ( timeDelta < 16) {
                    try {
                        Thread.sleep(16 - timeDelta);
                    }
                    catch(InterruptedException e) {

                    }
                }
                timePrevFrame = System.currentTimeMillis();

                try {
                    c = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                       //call methods to draw and process next fame
                        gameView.onDraw(c);
                    }
                } finally {
                    if (c != null) {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
}