package com.example.view;

import java.util.HashMap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.battleship.R;
import com.example.model.BoatType;

public class BoatBitmaps {
	
	private Resources resources;
	private HashMap<BoatType, Bitmap> bitmaps;
	
	public BoatBitmaps(Resources res) {
		this.resources = res;
		bitmaps = new HashMap<BoatType, Bitmap>();
	}

	public Bitmap getBoatBitmap(BoatType type) {
		if (bitmaps.containsKey(type)) return bitmaps.get(type);
		else {
			Bitmap bitmap = createBitmap(type);
			bitmaps.put(type, bitmap);
			return getBoatBitmap(type);
		}
	}

	private Bitmap createBitmap(BoatType type) {
		if (type == BoatType.AIRCRAFT_CARRIER)
			return BitmapFactory.decodeResource(resources, R.drawable.aircraft_carrier);
		if (type == BoatType.BATTLESHIP)
			return BitmapFactory.decodeResource(resources, R.drawable.battleship);
		if (type == BoatType.DESTROYER)
			return BitmapFactory.decodeResource(resources, R.drawable.destroyer);
		if (type == BoatType.PATROL_BOAT)
			return BitmapFactory.decodeResource(resources, R.drawable.patrolboat);
		if (type == BoatType.SUBMARINE)
			return BitmapFactory.decodeResource(resources, R.drawable.submarine);
		else return null;
	}
	
}
