package com.example.bouncingball;

import android.view.MotionEvent;

public interface TouchObserver {
	public void notifyMotionEvent(MotionEvent ev);
}
