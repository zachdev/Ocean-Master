package com.zachdev.game.entity.vehicle;

import com.zachdev.game.entity.Projectile;
import com.zachdev.game.entity.ShipProjectile;
import com.zachdev.game.entity.mob.Player;
import com.zachdev.game.graphics.AnimatedSprite;
import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;
import com.zachdev.game.graphics.SpriteSheet;
import com.zachdev.game.input.Keyboard;
import com.zachdev.game.level.tile.Tile;

public class PlayerShip extends Vehicle {

	private AnimatedSprite playerUpAnim = new AnimatedSprite(
			SpriteSheet.playerShipUp, 16, 16, 2);
	private AnimatedSprite playerDownAnim = new AnimatedSprite(
			SpriteSheet.playerShipDown, 16, 16, 2);
	private AnimatedSprite playerLeftAnim = new AnimatedSprite(
			SpriteSheet.playerShipLeft, 16, 16, 2);
	private AnimatedSprite playerRightAnim = new AnimatedSprite(
			SpriteSheet.playerShipRight, 16, 16, 2);

	private long timer = System.currentTimeMillis();

	Keyboard input;

	public PlayerShip(int x, int y, Keyboard keyboard) {
		super(x, y, keyboard);
		this.input = keyboard;
		animatedSprite = playerLeftAnim;

		//System.out.println("Loading player ship");
	}

	public void tick() {
		if (moving) {

			animatedSprite.tick();
		}

		else {

			animatedSprite.setFrame(0);
		}

		int xa = 0, ya = 0;

		if (controllingPlayer != null) {

			if (input.up) {
				animatedSprite = playerUpAnim;
				ya--;
			}
			if (input.down) {
				animatedSprite = playerDownAnim;
				ya++;
			}
			if (input.left) {
				animatedSprite = playerLeftAnim;
				xa--;
			}
			if (input.right) {
				animatedSprite = playerRightAnim;
				xa++;
			}

			if (input.disembark) {

				Tile forwardTile = getForwardTile();

				if (forwardTile.getClass().getSimpleName()
						.equals("WaterEdgeTile")) {

					// this.x = adjacentTiles[i].x / 16;
					// this.y = adjacentTiles[i].y / 16;
					// System.out.println(x / TILE_SIZE + " " + y / TILE_SIZE);
					System.out.println(level.getTile(x / TILE_SIZE, y
							/ TILE_SIZE + 1));
					// System.out.println(adjacentTiles[i].y);
					System.out.println("Disembarkable");
					y = y + 2;
					this.sprite = Sprite.brick;

				}
			}

			// String tileName = level.getTile((x / TILE_SIZE), (y / TILE_SIZE)
			// + TILE_SIZE / 2).getClass().getSimpleName(); // check underneath

			// System.out.println(tileName);

		}

		if (input.shooting) { // If we are pressing the space bar

			if (System.currentTimeMillis() - timer > 200) { // Only allow one
															// shot per 200
															// millisec

				timer = System.currentTimeMillis();
				shoot(x, y, direction);
			}
		}

		if (xa != 0 || ya != 0) {

			move(xa, ya); // Uses the Mob superclass move method to move the
							// Player
			moving = true;
		} else {

			moving = false;
		}
	}

	public void move(int x0, int y0) {

		/*
		 * If we are advancing on 2 axis, we run each one separately so we can
		 * process collision separately for each axis
		 */
		if (x0 != 0 && y0 != 0) {

			move(x0, 0); // Enables collision sliding because we are moving each
							// coordinate separately
			move(0, y0);
			return;
		}

		if (x0 > 0) {
			direction = 3;
			mobDirection = Direction.RIGHT; // Right
		}
		if (x0 < 0) {
			direction = 1;
			mobDirection = Direction.LEFT; // Left
		}
		if (y0 > 0) {
			direction = 2;
			mobDirection = Direction.DOWN; // Down
		}
		if (y0 < 0) {
			direction = 0;
			mobDirection = Direction.UP; // Up
		}

		if (!collision(x0, y0)) { // If there's no collision, we increase x and
									// y variables (mob location)
			// -1, 0, or 1
			x += x0;

			y += y0;

			// System.out.printf("Player Location: (%d, %d)%n", x / 16, y / 16);
		}

	}

	protected void shoot(int x, int y, int direction) {

		Projectile p = new ShipProjectile(this.x, this.y, this.direction);
		p.initialize(level);
		projectiles.add(p);
		level.add(p);

		// System.out.println("Shooting!");

	}

	private void updateShooting() {

	}

	public void render(Screen screen) {

		if (visible) {

			screen.renderMob(x, y, animatedSprite.getSprite());
		}

	}

	@Override
	public Player getControllingPlayer(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setControllingPlayer(Player player) {
		// TODO Auto-generated method stub

	}
}
