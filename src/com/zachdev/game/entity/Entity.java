package com.zachdev.game.entity;

import java.util.Random;

import com.zachdev.game.graphics.Screen;
import com.zachdev.game.level.Level;

public class Entity {
	
	public int x, y;
	
	public boolean removed = false; // If the entity has been removed from the level  or not
	
	protected Level level;		// The level associated with this entity
	protected final Random random = new Random();
	
	
	public void tick() {
		
		
	}
	
	public void render(Screen screen) {
		
		
	}
	
	public void remove() {
		// Removed the Entity from the level
		removed = true;
	}
	
	public boolean isRemoved() {
		
		return removed;
	}
		
	public void initialize(Level level) {		// Initializes with the provided Level
		
		this.level = level;
	}

}
