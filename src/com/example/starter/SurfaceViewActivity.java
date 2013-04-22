package com.example.starter;


import com.example.battleship.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

import com.example.controller.Controller;
import com.example.controller.TouchController;
import com.example.model.ModelImplementation;
import com.example.model.Model;
import com.example.view.CanvasView;
import com.example.view.View;

public class SurfaceViewActivity extends Activity implements OnTouchListener{
    Model model;
    CanvasView view;
    Controller controller;
    private static Context context;
   

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        instantiateClasses();
        setUpListeners();
        setContentView(view);	   
    }


	private void instantiateClasses() {
		controller = new TouchController();
		view = new CanvasView();
		model = new ModelImplementation();
	}
	
	private void setUpListeners() {
		//this is a comment.
		model.addObserver(view);
		controller.addObserver(model);
		
	}
		
	public static Context getAppContext() {
		return context;
	}

	
	
	public Controller getController(){
		return this.controller;
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		System.out.println("From event: " + "x: " + event.getX() + " y: " + event.getY());
		controller.gridGotTouched((int)event.getX(), (int)event.getY() - 110);
		return false;
	}

	

	@Override
	public boolean onTouch(android.view.View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}