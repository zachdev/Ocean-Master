package com.zachdev.game.entity.mob;

import com.zachdev.game.entity.Entity;
import com.zachdev.game.entity.Projectile;
import com.zachdev.game.entity.ShipProjectile;
import com.zachdev.game.entity.vehicle.PlayerShip;
import com.zachdev.game.entity.vehicle.Vehicle;
import com.zachdev.game.graphics.AnimatedSprite;
import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.SpriteSheet;
import com.zachdev.game.input.Keyboard;
import com.zachdev.game.level.tile.Tile;

public class Player extends Mob {

	private Keyboard input;

	private AnimatedSprite playerUpAnim = new AnimatedSprite(
			SpriteSheet.playerUp, 16, 16, 2);
	private AnimatedSprite playerDownAnim = new AnimatedSprite(
			SpriteSheet.playerDown, 16, 16, 2);
	private AnimatedSprite playerLeftAnim = new AnimatedSprite(
			SpriteSheet.playerLeft, 16, 16, 2);
	private AnimatedSprite playerRightAnim = new AnimatedSprite(
			SpriteSheet.playerRight, 16, 16, 2);
	
	private AnimatedSprite playerShipUpAnim = new AnimatedSprite(SpriteSheet.playerShipUp, 16, 16, 2);
	private AnimatedSprite playerShipDownAnim = new AnimatedSprite(SpriteSheet.playerShipDown, 16, 16, 2);
	private AnimatedSprite playerShipLeftAnim = new AnimatedSprite(SpriteSheet.playerShipLeft, 16, 16, 2);
	private AnimatedSprite playerShipRightAnim = new AnimatedSprite(SpriteSheet.playerShipRight, 16, 16, 2);

	private AnimatedSprite animatedSprite = playerDownAnim;

	private AnimatedSprite originalAnimatedSprite = animatedSprite;

	// private Sprite sprite = Sprite.playerUp; // Auto set the sprite to the up
	// sprite

	private boolean moving = false;

	private Vehicle vehicle;
	private PlayerShip playerShip;
	
	private long timer = System.currentTimeMillis();

	public Player(Keyboard input) {

		this.input = input;
		animatedSprite = playerDownAnim;
		vehicle = null;

	}

	/**
	 * Constructor that instantiates a Player with the specified x,y coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public Player(int x, int y, Keyboard input) {

		this.x = x;
		this.y = y;
		this.input = input;
	}

	public void tick() {

		if (moving) {

			animatedSprite.tick();
		}

		else {

			animatedSprite.setFrame(0);
		}

		int xa = 0, ya = 0;

		if (input.up) {

			if (inVehicle()) {
				
				animatedSprite = playerShipUpAnim;


			} else {
				animatedSprite = playerUpAnim;

			}
			
			ya--;
		}
		if (input.down) {

			if (inVehicle()) {
				
				animatedSprite = playerShipDownAnim;


			} else {
				animatedSprite = playerDownAnim;

			}
			
			ya++;
		}
		if (input.left) {
			if (inVehicle()) {
				
				animatedSprite = playerShipLeftAnim;


			} else {
				animatedSprite = playerLeftAnim;

			}
			xa--;
		}
		if (input.right) {
			if (inVehicle()) {
				
				animatedSprite = playerShipRightAnim;


			} else {
				animatedSprite = playerRightAnim;

			}			
			xa++;
		}

		if (input.disembark) {
			
			playerShip = null;
			
			for (Entity e : level.getEntities()) {
				
				if (e instanceof PlayerShip) {
					
					playerShip = (PlayerShip) e;
				}
			}
			
			
			System.out.println("x: " + x/16 + " shipX: " + playerShip.x/16 + " y: " + y/16 + " shipY: " + playerShip.y/16);
			boolean sameTile = (x / 16 == playerShip.x/16) && (y / 16 == playerShip.y/16);
			
			boolean upTile = y / 16 == playerShip.y  /16 - 1;
			boolean downTile = y / 16 == playerShip.y /16 + 1;


			boolean leftTile = (x / 16 == playerShip.x / 16 - 1);
			boolean rightTile = (x / 16 == playerShip.x / 16 + 1);
			
			
			
			if (sameTile || upTile || downTile || leftTile || rightTile) {
				
				if (vehicle == null) {
					
					vehicle = playerShip;
					originalAnimatedSprite = animatedSprite;
					animatedSprite = playerShip.animatedSprite;
					x = playerShip.x;
					y = playerShip.y;
					playerShip.setVisible(false);
				}
				
			}
			
			if (vehicle != null) {
				
				System.out.println("Landing");
				

				Tile landingTile = level.getTile((x / TILE_SIZE), (y / TILE_SIZE));
				
				int newX = x;
				int newY = y;
				
				if (mobDirection == Direction.UP) {
					
					landingTile = level.getTile((x / TILE_SIZE), (y / TILE_SIZE) - TILE_SIZE / 8);
					newX = x;
					newY = y - (2 * TILE_SIZE);
					
				}
				
				if (mobDirection == Direction.DOWN) {
					
					landingTile = level.getTile((x / TILE_SIZE), (y / TILE_SIZE)
							+ TILE_SIZE / 8);
					
					newX = x;
					newY = y + (2 * TILE_SIZE);
				}

				if (mobDirection == Direction.LEFT) {

					landingTile = level.getTile((x / TILE_SIZE) - TILE_SIZE / 8,
							(y / TILE_SIZE));
					
					newX = x - (2 * TILE_SIZE);
					newY = y;
				}

				if (mobDirection == Direction.RIGHT) {
					
					landingTile = level.getTile((x / TILE_SIZE) + TILE_SIZE / 8,
							(y / TILE_SIZE));
					
					newX = x + (2 * TILE_SIZE);
					newY = y;

				}

				
				if (landingTile.getClass().getSimpleName().equals("WaterEdgeTile") || 
					landingTile.getClass().getSimpleName().equals("LandTile")) {

					vehicle = null;
					animatedSprite = originalAnimatedSprite;
					playerShip.x = x;
					playerShip.y = y;
					playerShip.setVisible(true);

					x = newX;
					y = newY;		

				}
				
			}
			


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
		
	
		
		Tile landingTile = getForwardTile();
		
		System.out.println("landingTile:" + landingTile.getClass().getSimpleName());



		if (!collision(x0, y0)) { // If there's no collision, we increase x and
									// y variables (mob location)
			if (inVehicle()){
								
				if (!(landingTile.getClass().getSimpleName().equals("WaterEdgeTile"))) {
			
					
					x += x0;
					y += y0;
				}
				
			}
			else {
				
				if (!(landingTile.getClass().getSimpleName().equals("WaterTile"))) {

					
					x += x0;
					y += y0;
				}
			}

			// System.out.printf("Player Location: (%d, %d)%n", x / 16, y / 16);
		}

	}
	

	protected void shoot(int x, int y, int direction) {

		Projectile p = new ShipProjectile(this.x, this.y, this.direction);
		p.initialize(level);
		projectiles.add(p);
		level.add(p);
	}

	public boolean inVehicle() {

		return (this.vehicle instanceof Vehicle);
	}

	public void setVehicle(Vehicle vehicle) {

		this.vehicle = vehicle;

	}

	public void render(Screen screen) {

		screen.renderMob(x, y, animatedSprite.getSprite());

	}
}
