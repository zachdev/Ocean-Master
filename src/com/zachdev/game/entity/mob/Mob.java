package com.zachdev.game.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.zachdev.game.entity.Entity;
import com.zachdev.game.entity.Projectile;
import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;
import com.zachdev.game.level.tile.Tile;

/**
 * Handles the movement of the mob, as well as other Mob characteristics
 * 
 * @author zach
 * 
 */
public abstract class Mob extends Entity {

	protected static final int TILE_SIZE = Tile.TILE_SIZE;

	protected Sprite sprite;

	protected int direction = 0; // 0 = north, 1 east, 2 south, 3 west

	protected boolean moving = false;
<<<<<<< HEAD
	
	protected boolean visible = true;
=======
>>>>>>> ad915c85f7f824be629cc8c431642fd3dc38b240

	protected List<Projectile> projectiles = new ArrayList<Projectile>();

	protected enum Direction {

		UP, DOWN, LEFT, RIGHT
	}

	protected Direction mobDirection;

	protected Player player; // The player, so they know the location of him at
	// all times

	protected double speed = 1;
	protected double friction = 0.98;

	protected double velocityX = 0;
	protected double velocityY = 0;

	// These methods are different for each Mob and so are implemented in each
	// Mob subclass
	public abstract void tick();

	public abstract void render(Screen screen);

	public boolean collision(int xa, int ya) {

		// xa is where we are want to move (look ahead)
		// ya is where we want to move (look ahead)
		boolean solid = false;

		// Need to check every corner of the tile for collision
		// Iterates through each corner of the tile, checking for a collision

		for (int corner = 0; corner < 4; corner++) {

			int xt = ((x + xa) + corner % 2 * 10 + 4) / TILE_SIZE;

			int yt = ((y + ya) + corner / 2 * 2 + 8) / TILE_SIZE;

<<<<<<< HEAD
			// String tileName = level.getTile(xt,
			// yt).getClass().getSimpleName();
=======
			String tileName = level.getTile(xt, yt).getClass().getSimpleName();
>>>>>>> ad915c85f7f824be629cc8c431642fd3dc38b240

			if (level.getTile(xt, yt).solid())
				solid = true; // We look at the tile just ahead of our Mob

			// if (solid)
			// System.out.println(String.format(" Collision with %s at (%d,%d)",
			// tileName, x / TILE_SIZE, y / TILE_SIZE));

<<<<<<< HEAD
		}

		return solid;
	}
	
	public boolean visible() {
		
		return visible;
	}
	
	public void setVisible(boolean visible) {
		
		this.visible = visible;
	}
	
	/**
	 * Returns the Tile located just in front of the Player
	 * @return
	 */
	protected Tile getForwardTile() {
		
		Tile forwardTile;
		int newX = 0;
		int newY = 0;
		
		if (mobDirection == Direction.UP) {
			
			newX = x / TILE_SIZE;
			newY = (y - (1 * TILE_SIZE - (TILE_SIZE - 1))) / TILE_SIZE;
			
			System.out.println(newX + " " + newY);
					
		}
		
		if (mobDirection == Direction.DOWN) {
			
			newX = (x / TILE_SIZE);
			newY = (y + (1 * TILE_SIZE)) / TILE_SIZE;
		
		}

		if (mobDirection == Direction.LEFT) {
			
			newX = (x - (1 * TILE_SIZE - (TILE_SIZE - 1))) / TILE_SIZE;
			newY = (y / TILE_SIZE);
=======
>>>>>>> ad915c85f7f824be629cc8c431642fd3dc38b240
		}

		if (mobDirection == Direction.RIGHT) {
			
			newX = (x + (1 * TILE_SIZE)) / TILE_SIZE;
			newY = (y / TILE_SIZE);

		}
		
		forwardTile = level.getTile(newX, newY);
		
		return forwardTile;
	}

}
