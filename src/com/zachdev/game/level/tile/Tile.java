package com.zachdev.game.level.tile;

import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;

public class Tile {
	
	public static final int TILE_SIZE = 16;
	
	public int x, y; // Tile location on the grid
	
	public Sprite sprite; // Sprite of this tile
	
	//public static Tile grass = new GrassTile(Sprite.grass); // Static object - grass tile grabbed from Sprite class
	
	public static Tile water = new WaterTile(Sprite.water); // Static object - water tile grabbed from Sprite class
	
	public static Tile water1 = new WaterTile(Sprite.water1); // Static object - water tile grabbed from Sprite class
	
	public static Tile rock = new RockTile(Sprite.rock);	// Rock tile, collidable
	
	public static Tile bomb = new WaterTile(Sprite.bomb);
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public Tile(Sprite sprite) {
		
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		
		// Render stuff here
			screen.renderTile(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, this);
			
		}
		
	
	public boolean solid() {
		
		return false;		// False is returned by default in subclasses
	}

}
