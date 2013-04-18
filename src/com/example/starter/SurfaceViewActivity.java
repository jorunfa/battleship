package com.example.starter;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;

import com.example.controller.Controller;
import com.example.controller.TouchController;
import com.example.model.ArrayGameModel;
import com.example.model.Model;
import com.example.view.CanvasView;
import com.example.view.View;

public class SurfaceViewActivity extends Activity implements OnTouchListener{
    Model model;
    View view;
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

	

	private void setContentView(View view2) {
		
	}



	private void instantiateClasses() {
		controller = new TouchController();
		view = new CanvasView(getApplicationContext());
		model = new ArrayGameModel();
		
		
		
	}
	
	private void setUpListeners() {
		//this is a comment.
		model.addObserver(view);
		controller.addObserver(model);
		
	}
		
	public static Context getAppContext() {
		return context;
	}

	@Override
	public boolean onTouch(android.view.View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		return false;
	}
	
	public Controller getController(){
		return this.controller;
	}
}