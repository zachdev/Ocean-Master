package com.zachdev.game.graphics;

/**
 * Represents a graphical sprite on the canvas
 * @author zach
 *
 */
public class Sprite {
	
	public int size;
	
	private int x, y;
	
	public int[] pixels;
	
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite brick = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite water = new Sprite(16, 2, 0, SpriteSheet.tiles);
	
	public Sprite(int size, int x, int y, SpriteSheet spriteSheet) {
		
		pixels = new int[size * size]; 		// Creates pixel array of sprite size
		
		this.size = size;
		this.x = x * size; 					// Gets sprite from x, y coordinates
		this.y = y * size;					//
		this.sheet = spriteSheet;
		
		loadSprite();
		
	}
	/**
	 * Loads specific sprite from sprite sheet
	 */
	private void loadSprite() {
		
		for (int y = 0; y < this.size; y++) {
			
			for (int x = 0; x < this.size; x++) {
				
				pixels[x + y * this.size] = sheet.pixels[(x+this.x) + (y+this.y) * sheet.size]; 
			}
		}
		
		
	}

}
