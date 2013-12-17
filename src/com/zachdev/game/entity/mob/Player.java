package com.zachdev.game.entity.mob;

import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;
import com.zachdev.game.input.Keyboard;

public class Player extends Mob {
	
	private Keyboard input;
	
	private Sprite sprite = Sprite.playerUp;	// Auto set the sprite to the up sprite
	
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
		
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		
		if (xa != 0 || ya != 0) {
			
			move(xa, ya);			// Uses the Mob superclass move method to move the Player
		}
		
		
	}
	
	public void render(Screen screen) {
		
		if (direction == 0) sprite = Sprite.playerUp;
		if (direction == 1) sprite = Sprite.playerRight;
		if (direction == 2) sprite = Sprite.playerDown;
		if (direction == 3) sprite = Sprite.playerLeft;
		
		
		screen.renderPlayer(x, y, sprite);
		
		
	}
}
