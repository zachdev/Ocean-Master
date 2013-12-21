package com.zachdev.game.entity.mob;

import com.zachdev.game.entity.mob.Mob.Direction;
import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;

public class Enemy extends Mob {

	private int time = 0; // incremented 60 times per second ie, time % 60 == 0
							// = 1 time/sec
	
	int[] shootingTime = {100, 200, 300};

	private int xa = 0;
	private int ya = 0;

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
		
		if (time % shootingTime[random.nextInt(3)] == 0) {
			
			shoot(xa, ya, this.direction);
		}

		if (time % 120 == 0) {

			xa = -xa;
			ya = -ya;
		}

		if (time % 2 == 0) {

			//System.out.println(xa);

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
}
