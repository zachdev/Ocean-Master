package com.zachdev.game.entity.mob;

import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;

public class Enemy extends Mob {
		
	public Enemy(int x, int y) {
		
		this.x = x * 16;
		this.y = y * 16;
		
		sprite = Sprite.enemyRight;
		
		
	}

	@Override
	public void tick() {
		
		int xa = 0;
		int ya = 0;
	
		if (moving) {
			// Insert animation stuff here
		}
		
		if (ya < 0) {
			
			sprite = Sprite.enemyUp;
			mobDirection = Direction.UP;
		}
		
		else if (ya > 0) {
			
			sprite = Sprite.enemyDown;
			mobDirection = Direction.DOWN;			
			
		}

		if (xa < 0) {
			
			sprite = Sprite.enemyLeft;
			mobDirection = Direction.LEFT;	
	
		}

		else if (xa > 0) {
			
			sprite = Sprite.enemyRight;
			mobDirection = Direction.RIGHT;	
	
		}
		
		if (xa != 0 || ya != 0) {
			
			move(xa, ya);
			moving = true;
		}
		else {
			moving = false;
		}		
		
	}

	@Override
	public void render(Screen screen) {
		
		screen.renderMob(x, y, sprite);
		
		
	}

}
