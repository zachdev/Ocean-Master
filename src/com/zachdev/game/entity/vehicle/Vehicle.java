package com.zachdev.game.entity.vehicle;

import com.zachdev.game.entity.mob.Mob;
import com.zachdev.game.entity.mob.Player;
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

}
