package com.zachdev.game.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.zachdev.game.entity.Entity;
import com.zachdev.game.entity.Projectile;
import com.zachdev.game.entity.ShipProjectile;
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

			String tileName = level.getTile(xt, yt).getClass().getSimpleName();

			if (level.getTile(xt, yt).solid())
				solid = true; // We look at the tile just ahead of our Mob

			// if (solid)
			// System.out.println(String.format(" Collision with %s at (%d,%d)",
			// tileName, x / TILE_SIZE, y / TILE_SIZE));

		}

		return solid;
	}

}
