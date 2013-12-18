package com.zachdev.game.entity;

import com.zachdev.game.graphics.Sprite;

public abstract class Projectile extends Entity {
	
	protected final int xOrigin, yOrigin;
	
	protected double angle;
	
	protected Sprite sprite;
	
	protected double nx, ny; // new x and new y, changes each tick
	
	protected double speed, rateOfFire, range, damage;
	
	public Projectile(int x, int y, int direction) {
		
		xOrigin = x;
		yOrigin = y;
		
		angle = direction;
		
		this.x = x;
		this.y = y;
		
		
	}

}
