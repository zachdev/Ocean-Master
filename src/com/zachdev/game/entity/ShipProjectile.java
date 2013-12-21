package com.zachdev.game.entity;

import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;
import com.zachdev.game.level.tile.Tile;

public class ShipProjectile extends Projectile {
	
	private int direction;
	

	public ShipProjectile(int x, int y, int direction) {
		super(x, y, direction);
		range = 200;
		damage = 20;
		rateOfFire = 15;
		this.direction = direction;
		
		sprite = Sprite.bomb;
		
		nx = speed * angle;
		ny = speed * angle;
	}
	
	public void tick() {
		
		move();
	}
	
	protected void move() {
		
		//System.out.println(direction);
		
		if (direction == 0) y--; 
		if (direction == 2) y++; 
		if (direction == 3) x++; 
		if (direction == 1) x--; 
		
		//x++;
		//y += ny;
	}
	
	public void render(Screen screen) {
		
		screen.renderTile(x, y, Tile.bomb);
		
		
	}
	
	

}
