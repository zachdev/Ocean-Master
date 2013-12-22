package com.zachdev.game.level.tile;

import com.zachdev.game.graphics.Sprite;

public class LandTile extends Tile {

	public LandTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean solid() {
		
		return true;
	}

}
