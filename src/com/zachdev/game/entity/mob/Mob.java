package com.zachdev.game.entity.mob;

import com.zachdev.game.entity.Entity;
import com.zachdev.game.graphics.Sprite;

/**
 * Handles the movement of the mob, as well as other Mob characteristics
 * 
 * @author zach
 *
 */
public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	
	protected int direction = 0; // 0 = north, 1 east, 2 south, 3 west
	
	protected boolean moving = false;
	
	
	public void move(int x0, int y0) {
		
		/*
		 * If we are advancing on 2 axis, we run each one separately so we can process collision separately for each axis
		 */
		if (x0 != 0 && y0 != 0) {
			
			move(x0, 0);		// Enables collision sliding because we are moving each coordinate separately
			move(0, y0);
			return;
		}
		
		if (x0 > 0) direction = 1;		// Right
		if (x0 < 0) direction = 3;		// Left
		if (y0 > 0) direction = 2;		// Down
		if (y0 < 0) direction = 0;		// Up

		if (!collision(x0, y0)) { // If there's no collision, we increase x and y variables (mob location)
			// -1, 0, or 1
			x += x0;
			
			y += y0;
			
			//System.out.printf("Player Location: (%d, %d)%n", x / 16, y / 16);
		}
		
	}
	
	public void tick() {
		
		
	}
	
	public void render() {
		
		
	}
	
	public boolean collision(int xa, int ya) {
		
		// xa is where we are want to move (look ahead)
		// ya is where we want to move (look ahead)
		boolean solid = false;
		
		// Need to check every corner of the tile for collision
		
		for (int corner = 0; corner < 4; corner++) {
			
			int xt = ((x + xa) + corner % 2 * 10 + 4) / 16;
			
			int yt = ((y + ya) + corner / 2 * 2 + 8) / 16;
			
			if (level.getTile(xt, yt).solid()) solid = true;	// We look at the tile just ahead of our Mob
			
			if (solid) System.out.println("Collision");
			
			
			
		}
																			// And if it's solid, we return true
		return solid;
	}

}
