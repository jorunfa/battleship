package com.example.starter;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;

import com.example.controller.Controller;
import com.example.controller.TouchController;
import com.example.model.Button;
import com.example.model.Model;
import com.example.model.ModelImplementation;
import com.example.view.CanvasView;
import com.example.view.View;

public class SurfaceViewActivity extends Activity implements OnTouchListener {
    private Model model;
    private View view;
    private Controller controller;
    private static Context context;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        instantiateClasses();
        setUpListeners();
        setContentView((CanvasView)view);
    }


	private void instantiateClasses() {
		controller = new TouchController();
		model = new ModelImplementation();
		view = new CanvasView();
	}
	
	private void setUpListeners() {
		model.addObserver(view);
		controller.addObserver(model);
	}
		
	public static Context getAppContext() {
		return context;
	}	

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			controller.gridGotTouched((int)event.getX(), (int)event.getY() - 120);
		}
		return super.onTouchEvent(event);
	}


	@Override
	public boolean onTouch(android.view.View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}