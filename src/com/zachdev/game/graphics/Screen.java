package com.zachdev.game.graphics;

import com.zachdev.game.level.tile.Tile;

/**
 * Renders the screen
 * @author zach
 *
 */
public class Screen {
	
	private static final int TILE_SIZE = Tile.TILE_SIZE;
	private static final int MAP_SIZE_MASK = TILE_SIZE - 1;
	
	public int width, height;
	
	public int[] pixels;
	
	public int[] tiles = new int[TILE_SIZE * TILE_SIZE]; 
	
	public int xOffset, yOffset;
	
	
	public Screen(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];

	}
	
	/**
	 * Clears the screen of all image data and resets all pixels to black
	 */
	public void clear() {
		
		//Cycle through every pixel in the pixels[] array and set to black 
		for (int i = 0; i < pixels.length; i++) {
			
			pixels[i] = 0; 
		}
		
	}
	
	public void render(int xOffset, int yOffset) {
		
		for (int y = 0; y < height; y++) {
			
			int yp = y + yOffset;
			
			if (yp < 0 || yp >= height) continue; // Prevent arrayindexoutofboundsexception
			
			for (int x = 0; x < width; x++) {
				
				int xp = x + xOffset;
				
				if (xp < 0 || xp >= width) continue;			
				
				int tileIndex = ((xp / 16) & MAP_SIZE_MASK) + ((yp / 16) & MAP_SIZE_MASK) * 8;  // Finds the tile index from x,y coords. Tile size is 8 x 8
				
				
				
			}
		}
	}
	
	
	public void renderTile(int xp, int yp, Tile tile) {
		
		xp -= xOffset;	// Displacing the x, y position of the tile based on offset
		yp -= yOffset;
		
		for (int y = 0; y < tile.sprite.size; y++) {
			
			int ya = y + yp; // Adds offset
			
			for (int x = 0; x < tile.sprite.size; x++) {
				
				int xa = x + xp; // Adds offset
				
				if (xa < -tile.sprite.size || xa >= width || ya < 0 || ya >= height) { // If a tile exits the screen view, stop rendering that tile
					
					break;
				}
				
				if (xa < 0) xa = 0;
				
				int color = tile.sprite.pixels[x + y * tile.sprite.size];
				
				//pixels[xa + ya * width] = color; 
				
				if (color != 0xFFFFFF) {	// If the tile background is white don't render it
					
					pixels[xa + ya * width] = color; 
					
					
				}
			}
		}
		
		
	}
	
	public void renderMob(int xp, int yp, Sprite sprite) {
		
		xp -= xOffset;	// Displacing the x, y position of the tile based on offset
		yp -= yOffset;
		
		for (int y = 0; y < 16; y++) {
			
			int ya = y + yp; // Adds offset
			
			for (int x = 0; x < 16; x++) {
				
				int xa = x + xp; // Adds offset
				
				if (xa < -16 || xa >= width || ya < 0 || ya >= height) { // If a tile exits the screen view, stop rendering that tile
					
					break;
				}
				
				if (xa < 0) xa = 0;
				
				int color = sprite.pixels[x + y * sprite.size];
				
				if (color != 0xFFFFFF) {	// If the tile background is white don't render it
					
					pixels[xa + ya * width] = color; 
					
					
				}
			}
		}
		
	}
	
	public void setOffset(int xOffset, int yOffset) {
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
