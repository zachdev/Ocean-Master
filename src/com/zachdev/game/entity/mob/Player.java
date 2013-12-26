package com.zachdev.game.entity.mob;

import com.zachdev.game.entity.Entity;
import com.zachdev.game.entity.Projectile;
import com.zachdev.game.entity.ShipProjectile;
import com.zachdev.game.entity.mob.Mob.Direction;
import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;
import com.zachdev.game.input.Keyboard;
import com.zachdev.game.level.tile.Tile;

public class Player extends Mob {
	
	private Keyboard input;
	
	private Sprite sprite = Sprite.playerUp;	// Auto set the sprite to the up sprite
	
	private int animate = 0;
	
	private boolean moving = false;
	
	private boolean visible = true;
	
	private long timer = System.currentTimeMillis();
	
	public Player (Keyboard input) {
		
		this.input = input;
		sprite = Sprite.playerUp;
		
	}
	
	/**
	 * Constructor that instantiates a Player with the specified x,y coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public Player (int x, int y, Keyboard input) {
		
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.playerUp;
		
		
	}
	
	public void tick() {
		
		int xa = 0, ya = 0;
		
		if (animate < 10000) {
			
			animate++;				// Increase animation count
		}else {
			
			animate = 0;			// Else reset the animation count to 0 after 10000 ticks so game doesn't crash
		}
		
		
		
		
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
	
		
		if (input.disembark) {
			
			
			Tile[] adjacentTiles = new Tile[4];
			
			adjacentTiles[0] = level.getTile((x / TILE_SIZE), (y / TILE_SIZE) - TILE_SIZE / 8); // up tile
			adjacentTiles[1] = level.getTile((x / TILE_SIZE), (y / TILE_SIZE) + TILE_SIZE / 8); // down tile
			adjacentTiles[2] = level.getTile((x / TILE_SIZE) - TILE_SIZE / 8, (y / TILE_SIZE)); // left tile
			adjacentTiles[3] = level.getTile((x / TILE_SIZE) + TILE_SIZE / 8, (y / TILE_SIZE)); // right tile
			
			for (int i = 0; i < adjacentTiles.length; i++) {
				
				if (adjacentTiles[i].getClass().getSimpleName().equals("LandTile")) {
					
					//this.x = adjacentTiles[i].x / 16;
					//this.y = adjacentTiles[i].y / 16;
					//System.out.println(x / TILE_SIZE + " " + y / TILE_SIZE);
					System.out.println(level.getTile(x / TILE_SIZE, y / TILE_SIZE + 1));
					//System.out.println(adjacentTiles[i].y);
					System.out.println("Disembarkable");
					y = y + 2;
					this.sprite = Sprite.brick;
				
				}
			}

			
			
			//String tileName = level.getTile((x / TILE_SIZE), (y / TILE_SIZE) + TILE_SIZE / 2).getClass().getSimpleName(); // check underneath
			
			//System.out.println(tileName);
			
			
		}
		
		
		if(input.shooting) {		// If we are pressing the space bar
			
			if (System.currentTimeMillis() - timer > 200) { // Only allow one shot per 200 millisec
				
				timer = System.currentTimeMillis();
				shoot(x, y, direction);	
			}
		}
		
		if (xa != 0 || ya != 0) {
			
			move(xa, ya);			// Uses the Mob superclass move method to move the Player
			moving = true;
		} else {
			
			moving = false;
		}
		
		//updateShooting();
		
		
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

			//System.out.printf("Player Location: (%d, %d)%n", x / 16, y / 16);
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
		
		if (mobDirection == Direction.UP) {								// Adds animations to Player sprite
					
			if (moving) {									// If we're moving up
				
				if (animate % 20 > 10) {					// animate incrases by 60 each second
															// Whenever the remainder of anim and 20 is > 10 (50% of the time)
					sprite = Sprite.playerUp;
				}
				else {
					
					sprite = Sprite.playerUp1;
				}
			}
		}
		if (mobDirection == Direction.RIGHT) {
			
			if (moving) {
				
				if (animate % 20 > 10) {
					
					sprite = Sprite.playerRight;
				}
				else {
					
					sprite = Sprite.playerRight1;
				}
			}
		}
		if (mobDirection == Direction.DOWN) {
			
			if (moving) {
				
				if (animate % 20 > 10) {
					
					sprite = Sprite.playerDown;
				}
				else {
					
					sprite = Sprite.playerDown1;
				}
			}
			
		}
		if (mobDirection == Direction.LEFT) {
			
			if (moving) {
				
				if (animate % 20 > 10) {
					
					sprite = Sprite.playerLeft;
				}
				else {
					
					sprite = Sprite.playerLeft1;
				}
			}
		}
		
		
		screen.renderMob(x, y, sprite);
		
		
	}
}
