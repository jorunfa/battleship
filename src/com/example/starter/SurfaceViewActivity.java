package com.example.starter;

import android.app.Activity;
import android.os.Bundle;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instantiateClasses();
        setUpListeners();

        setContentView(view);
    }

	private void instantiateClasses() {
		controller = new TouchController();
		view = new CanvasView();
		model = new ArrayGameModel();
	}
	
	private void setUpListeners() {
		model.addObserver(view);
		controller.addObserver(model);
	}
}