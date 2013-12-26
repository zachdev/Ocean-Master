package com.zachdev.game.entity.mob;

import com.zachdev.game.entity.Projectile;
import com.zachdev.game.entity.ShipProjectile;
import com.zachdev.game.entity.mob.Mob.Direction;
import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;

public class Enemy extends Mob {

	private int time = 0; // incremented 60 times per second ie, time % 60 == 0
							// = 1 time/sec

	private int[] shootingTime = { 100, 200, 300, 100, 200, 300 };

	private int xa = 0;
	private int ya = 0;

	private int collisions = 0;

	private int[] directions = { -1, 1 };

	public Enemy(int x, int y, int dir) {

		this.x = x * 16;
		this.y = y * 16;
		sprite = Sprite.enemyRight;

		if (dir == 0) { // up

			ya = -1;

		}
		if (dir == 1) { // right

			xa = 1;

		}
		if (dir == 2) { // down

			ya = 1;

		}
		if (dir == 3) { // left

			xa = -1;

		}

	}

	@Override
	public void tick() {
		time++;

		if (xa > 1)
			xa = 1;
		if (xa < -1)
			xa = -1;

		if (ya > 1)
			ya = 1;
		if (ya < -1)
			ya = -1;

		// If we collide with something multiple times we change direction
		if (collisions > 2) {

			if (xa == 0) {

				xa = directions[random.nextInt(1)];
				ya = 0;
			}

			else if (ya == 0) {

				ya = directions[random.nextInt(1)];
				;
				xa = 0;
			}

			collisions = 0;

		}

		if (collision(xa, ya)) {

			collisions++;
			xa = -xa;
			ya = -ya;
		}

		if (time % shootingTime[random.nextInt(5)] == 0) {

			shoot(xa, ya, this.direction);
		}

		if (time % shootingTime[random.nextInt(5)] == 0) {

			xa = -xa;
			ya = -ya;
		}

		// Randomly change axis (x to y, y to x)
		if (time % shootingTime[random.nextInt(5)] == 0) {

			if (xa == 0) {

				xa = directions[random.nextInt(1)];
				ya = 0;
			}

			else if (ya == 0) {

				ya = directions[random.nextInt(1)];
				;
				xa = 0;
			}
		}

		if (time % 2 == 0) {

			// System.out.println(xa);

			if (moving) {
				// Insert animation stuff here
			}

			if (ya < 0) {

				sprite = Sprite.enemyUp;
				mobDirection = Direction.UP;
				ya--;
			}

			else if (ya > 0) {

				sprite = Sprite.enemyDown;
				mobDirection = Direction.DOWN;
				ya++;

			}

			if (xa < 0) {

				sprite = Sprite.enemyLeft;
				mobDirection = Direction.LEFT;
				xa--;

			}

			else if (xa > 0) {

				sprite = Sprite.enemyRight;
				mobDirection = Direction.RIGHT;
				xa++;

			}

			if (xa != 0 || ya != 0) {

				move(xa, ya);
				moving = true;
			} else {
				moving = false;
			}
		}

		if (time > 5000) {

			time = 0;
		}

	}

	@Override
	public void render(Screen screen) {

		if (mobDirection == Direction.UP) { // Adds animations to Player sprite

			if (moving) { // If we're moving up

				if (time % 20 > 10) { // animate incrases by 60 each second
										// Whenever the remainder of anim and 20
										// is > 10 (50% of the time)
					sprite = Sprite.enemyUp;
				} else {

					sprite = Sprite.enemyUp1;
				}
			}
		}
		if (mobDirection == Direction.RIGHT) {

			if (moving) {

				if (time % 20 > 10) {

					sprite = Sprite.enemyRight;
				} else {

					sprite = Sprite.enemyRight1;
				}
			}
		}
		if (mobDirection == Direction.DOWN) {

			if (moving) {

				if (time % 20 > 10) {

					sprite = Sprite.enemyDown;
				} else {

					sprite = Sprite.enemyDown1;
				}
			}

		}
		if (mobDirection == Direction.LEFT) {

			if (moving) {

				if (time % 20 > 10) {

					sprite = Sprite.enemyLeft;
				} else {

					sprite = Sprite.enemyLeft1;
				}
			}
		}

		screen.renderMob(x, y, sprite);

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
			//x += x0;

			//y += y0;

			if (mobDirection == Direction.UP) {
				
				if (velocityY > -speed) velocityY--;

			}
			else if (mobDirection == Direction.DOWN) {
				
				if (velocityY < speed) velocityY++;

			}
			else if (mobDirection == Direction.LEFT) {
				
				if (velocityX > -speed) velocityX--;

			}
			else if (mobDirection == Direction.RIGHT) {
				
				if (velocityX < speed) velocityX++;

			}
			
			//velocityY *= friction;
			//y += velocityY;
			//velocityX *= friction;
			//x += velocityX;
			
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
}
