package com.zachdev.game.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.zachdev.game.entity.Entity;
import com.zachdev.game.entity.Projectile;
import com.zachdev.game.entity.ShipProjectile;
import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;
import com.zachdev.game.level.Level;

/**
 * Handles the movement of the mob, as well as other Mob characteristics
 * 
 * @author zach
 *
 */
public abstract class Mob extends Entity {
	
	private static final int TILE_SIZE = Level.TILE_SIZE;
	
	protected Sprite sprite;
	
	protected int direction = 0; // 0 = north, 1 east, 2 south, 3 west
	
	protected boolean moving = false;
	
	protected List<Projectile> projectiles = new ArrayList<Projectile>();
	
	protected enum Direction {
		
		UP, DOWN, LEFT, RIGHT
	}
	
	protected Direction mobDirection;
	
	// These methods are different for each Mob and so are implemented in each Mob subclass
	public abstract void tick();
	public abstract void render(Screen screen);
	
	
	public void move(int x0, int y0) {
		
		/*
		 * If we are advancing on 2 axis, we run each one separately so we can process collision separately for each axis
		 */
		if (x0 != 0 && y0 != 0) {
			
			move(x0, 0);		// Enables collision sliding because we are moving each coordinate separately
			move(0, y0);
			return;
		}
		
		if (x0 > 0) mobDirection = Direction.RIGHT;		// Right
		if (x0 < 0) mobDirection = Direction.LEFT;		// Left
		if (y0 > 0) mobDirection = Direction.DOWN;		// Down
		if (y0 < 0) mobDirection = Direction.UP;		// Up

		if (!collision(x0, y0)) { // If there's no collision, we increase x and y variables (mob location)
			// -1, 0, or 1
			x += x0;
			
			y += y0;
			
			//System.out.printf("Player Location: (%d, %d)%n", x / 16, y / 16);
		}
		
	}
	
	
	protected void shoot(int x, int y, double direction) {
		
		Projectile p = new ShipProjectile(this.x,  this.y, 2);
		projectiles.add(p);
		level.add(p);
		
		//System.out.println("Shooting!"); 
		
		
	}
	
	public boolean collision(int xa, int ya) {
		
		// xa is where we are want to move (look ahead)
		// ya is where we want to move (look ahead)
		boolean solid = false;
		
		// Need to check every corner of the tile for collision
		// Iterates through each corner of the tile, checking for a collision
		
		for (int corner = 0; corner < 4; corner++) {
			
			int xt = ((x + xa) + corner % 2 * 10 + 4) / TILE_SIZE;
			
			int yt = ((y + ya) + corner / 2 * 2 + 8) / TILE_SIZE;
			
			if (level.getTile(xt, yt).solid()) solid = true;	// We look at the tile just ahead of our Mob
			
			if (solid) System.out.println("Collision");	
			
		}

		return solid;
	}

}
