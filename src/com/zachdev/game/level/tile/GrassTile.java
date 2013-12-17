package com.zachdev.game.level.tile;

import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;

public class GrassTile extends Tile {
	
	public static Tile grass = new GrassTile(Sprite.grass); // Static object - grass tile grabbed from Sprite class
	
	public GrassTile(Sprite sprite) {
		
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		
	// Render stuff here
		screen.renderTile(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, this);
		
	}

}
