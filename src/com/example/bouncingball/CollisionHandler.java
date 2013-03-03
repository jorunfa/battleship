package com.example.bouncingball;

import java.util.ArrayList;

public class CollisionHandler {
	
	ArrayList<GameObject> bumpableElements = new ArrayList<GameObject>();
	private static final CollisionHandler instance = new CollisionHandler();
	
	private CollisionHandler() {	}
	
	public static CollisionHandler getInstance() {
        return instance;
    }
	
	public void add(GameObject h) {
		bumpableElements.add(h);
	}
	
	public void checkAndHandleCollisions() {
		GameObject heli1;
		GameObject heli2;
		for (int i = 0; i < bumpableElements.size()-1;i++) {
			heli1 = bumpableElements.get(i);
			for (int j = i+1; j < bumpableElements.size(); j++) {
				heli2 = bumpableElements.get(j);
				if (collision(heli1, heli2)) {
					heli1.collision();
					heli2.collision();
				}
			}
		}
	}
	
	private boolean collision(GameObject heli1, GameObject heli2) {
		if (haveHorizontalIntersection(heli1, heli2) && haveVerticalIntersection(heli1, heli2)) {
			return true;
		}
		else {
			return false;			
		}
	}

	private boolean haveHorizontalIntersection(GameObject heli1, GameObject heli2) {
		int heli1_left_side = heli1.getLeftSideX();
		int heli2_left_side = heli2.getLeftSideX();
		int heli1_right_side = heli1.getRightSideX();
		int heli2_right_side = heli2.getRightSideX();
		
		if (heli1_left_side < heli2_left_side && heli2_left_side < heli1_right_side) {
			return true;
		}
		else if (heli2_left_side < heli1_left_side && heli2_right_side > heli1_left_side) {
			return true;
		}
		else return false;	
	}
	
	private boolean haveVerticalIntersection(GameObject heli1, GameObject heli2) {
		int heli1_top_side = heli1.getTopY();
		int heli2_top_side = heli2.getTopY();
		int heli1_bottom_side = heli1.getBottomY();
		int heli2_bottom_side = heli2.getBottomY();
		
		if (heli1_top_side < heli2_top_side && heli2_top_side < heli1_bottom_side) {
			return true;
		}
		else if (heli2_top_side < heli1_top_side && heli2_bottom_side > heli1_top_side) {
			return true;
		}
		else return false;
	}
}
