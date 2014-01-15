package com.zachdev.game.level.tile;

import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;

public class RockTile extends Tile {

	public RockTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		
		// Render stuff here
		screen.renderTile(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, this);
			
	}
	
	public boolean solid() {
		
		return true;
	}
	
	

}
