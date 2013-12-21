package com.zachdev.game.entity.mob;

import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;
import com.zachdev.game.input.Keyboard;

public class Player extends Mob {
	
	private Keyboard input;
	
	private Sprite sprite = Sprite.playerUp;	// Auto set the sprite to the up sprite
	
	private int animate = 0;
	
	private boolean moving = false;
	
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
		
		if (animate % 20 < 10) {
			
			if(input.shooting) {		// If we are pressing the space bar
				
				if (System.currentTimeMillis() - timer > 1000) { // Only allow one bomb to be dropped per second
					
					timer = System.currentTimeMillis();
					shoot(x, y, direction);	
				}
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
