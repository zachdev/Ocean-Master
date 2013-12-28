package com.zachdev.game.entity.vehicle;

import com.zachdev.game.entity.mob.Mob;
import com.zachdev.game.entity.mob.Player;
<<<<<<< HEAD
import com.zachdev.game.graphics.AnimatedSprite;
import com.zachdev.game.graphics.Screen;
import com.zachdev.game.input.Keyboard;

public abstract class Vehicle extends Mob {

	public int animate = 0;

	public Player controllingPlayer = null;

	public AnimatedSprite animatedSprite;
	
	public Vehicle(int x, int y, Keyboard keyboard) {
		
		this.x = x;
		this.y = y;
		
		
	}

	public abstract void tick();

	public abstract void render(Screen screen);
	
	public abstract Player getControllingPlayer(Player player);

	public abstract void setControllingPlayer(Player player);
=======
import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;
import com.zachdev.game.input.Keyboard;

public class Vehicle extends Mob {

	private Keyboard input;
	
	protected int animate = 0;

	protected Player controllingPlayer;

	protected Sprite sprite;

	public Vehicle(int x, int y, Keyboard keyboard) {

		this.x = x;
		this.y = y;
		this.input = keyboard;

	}

	public void tick() {
		int xa = 0, ya = 0;

		if (input.up)
			ya--;
		if (input.down)
			ya++;
		if (input.left)
			xa--;
		if (input.right)
			xa++;

		if (xa != 0 || ya != 0) {

			move(xa, ya); // Uses the Mob superclass move method to move the
							// Player
			moving = true;
		} else {

			moving = false;
		}
	}

	@Override
	public void render(Screen screen) {
		// Vehicle subclasses handle actual rendering
	}
	
	public void setControllingPlayer(Player player) {
		this.player = player;
	}
>>>>>>> ad915c85f7f824be629cc8c431642fd3dc38b240

}
