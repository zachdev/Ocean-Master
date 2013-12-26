package com.zachdev.game.entity;

import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;
import com.zachdev.game.level.tile.Tile;

public class ShipProjectile extends Projectile {

	private static final int SPEED = 3;

	private int direction;

	private int distanceTraveled = 0;

	public ShipProjectile(int x, int y, int direction) {
		super(x, y, direction);
		range = 75;
		damage = 20;
		rateOfFire = 15;
		this.direction = direction;

		sprite = Sprite.cannonBall;

		nx = speed * angle;
		ny = speed * angle;
	}

	public void tick() {
		
		if (collision(x,y)) {
			
			//System.out.println("boom");
			//this.remove();
			this.explode();
		}

		if (distanceTraveled >= range) {
			this.remove();
		}

		if (!(distanceTraveled > range)) {

			move();
			distanceTraveled += SPEED;

		}

		if (distanceTraveled > 200) {

			distanceTraveled = 1;
		}
	}

	protected void move() {

		// System.out.println(direction);

		if (direction == 0) {

			y -= SPEED;
		}
		if (direction == 2) {

			y += SPEED;
		}
		if (direction == 3) {

			x += SPEED;
		}
		if (direction == 1) {

			x -= SPEED;
		}
	}

	public void render(Screen screen) {

		screen.renderTile(x, y, Tile.bomb);

	}
	
	private void explode() {	// not working right yet
		
		this.sprite = Sprite.explosion1;
		this.sprite = Sprite.explosion2;
		this.sprite = Sprite.explosion3;
		this.sprite = Sprite.explosion4;
		this.sprite = Sprite.explosion5;
		this.remove();
	}

	public boolean collision(int xa, int ya) {

		// xa is where we are want to move (look ahead)
		// ya is where we want to move (look ahead)
		boolean solid = false;

		// Need to check every corner of the tile for collision
		// Iterates through each corner of the tile, checking for a collision

		for (int corner = 0; corner < 4; corner++) {

			int xt = ((x + xa) + corner % 2 * 10 + 8) / 16;

			int yt = ((y + ya) + corner / 2 * 2 - 6) / 16;

			//String tileName = level.getTile(xt, yt).getClass().getSimpleName();

			if (level.getTile(xt, yt).solid())
				solid = true; // We look at the tile just ahead of our Mob

			if (solid) {}
				//System.out.println("Collision with " + tileName);

		}

		return solid;
	}

}
