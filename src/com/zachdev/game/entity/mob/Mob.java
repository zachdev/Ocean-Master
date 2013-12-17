package com.zachdev.game.entity.mob;

import com.zachdev.game.entity.Entity;
import com.zachdev.game.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	
	protected int direction = 0; // 0 = north, 1 east, 2 south, 3 west
	
	protected boolean moving = false;
	
	
	public void move(int x0, int y0) {
		
		if (x0 > 0) direction = 1;		// Right
		if (x0 < 0) direction = 3;		// Left
		if (y0 > 0) direction = 2;		// Down
		if (y0 < 0) direction = 0;		// Up

		if (!collision()) { // If there's no collision, we increase x and y variables (mob location)
			// -1, 0, or 1
			x += x0;
			
			y += y0;
		}
		
	}
	
	public void tick() {
		
		
	}
	
	public void render() {
		
		
	}
	
	private boolean collision() {
		
		return false;
	}

}
