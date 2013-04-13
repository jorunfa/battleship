package com.example.starter;

import java.util.Observer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.example.controller.Controller;
import com.example.controller.TouchController;
import com.example.model.ArrayGameModel;
import com.example.model.Model;
import com.example.view.CanvasView;
import com.example.view.View;

public class SurfaceViewActivity extends Activity {
    Model model;
    View view;
    Controller controller;
    Context cont =this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpDisplaySize();
        instantiateClasses();
        setUpListeners();

        setContentView(view);
    }

	private void setContentView(View view2) {
		// TODO Auto-generated method stub
		
	}

	private void instantiateClasses() {
		controller = new TouchController();
		view = new CanvasView();
		model = new ArrayGameModel();
	}
	
	private void setUpListeners() {
		//this is a comment.
		model.addObserver(view);
		controller.addObserver(model);
	}
	
	private void setUpDisplaySize(){
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
	}
	
	public Context getContext(){
		return this.cont;
	}
}