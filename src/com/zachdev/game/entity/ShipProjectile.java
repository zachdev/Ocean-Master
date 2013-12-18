package com.zachdev.game.entity;

import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;
import com.zachdev.game.level.tile.Tile;

public class ShipProjectile extends Projectile {
	

	public ShipProjectile(int x, int y, int direction) {
		super(x, y, direction);
		System.out.println(direction);
		range = 200;
		damage = 20;
		rateOfFire = 15;
		angle = direction;
		
		sprite = Sprite.bomb;
		
		nx = speed * angle;
		ny = speed * angle;
	}
	
	public void tick() {
		
		move();
	}
	
	protected void move() {
		
		x += nx;
		y += ny;
	}
	
	public void render(Screen screen) {
		
		screen.renderTile(x, y, Tile.bomb);
		
		
	}
	
	

}
