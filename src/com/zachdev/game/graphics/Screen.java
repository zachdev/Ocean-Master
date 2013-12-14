package com.zachdev.game.graphics;

import java.util.Random;

/**
 * Renders the screen
 * @author zach
 *
 */
public class Screen {
	
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	
	private int width, height;
	
	public int[] pixels;
	
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // Tile map is 64 x 64
	
	private Random random = new Random();
	
	public Screen(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];
		
		
		
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) { // Generates the tiles
			
			tiles[i]= random.nextInt(0xffffff); // Will generate a random color each time 
		}
	}
	
	public void clear() {
		
		//Cycle through every pixel in the pixels[] array and set to black 
		for (int i = 0; i < pixels.length; i++) {
			
			pixels[i] = 0; 
		}
		
	}
	
	public void render(int xOffset, int yOffset) {
		
		for (int y = 0; y < height; y++) {
			
			int yp = y + yOffset;
			
			if (yp < 0 || yp >= height) continue;
			
			//int yy = y + yOffset;
			
			//if (y < 0 || y >= height) break;
			
			
			for (int x = 0; x < width; x++) {
				
				int xp = x + xOffset;
				
				if (xp < 0 || xp >= width) continue;
				
				//int xx = x + xOffset;				
				
				//int tileIndex = ((xx / 16) & MAP_SIZE_MASK) + ((yy / 16) & MAP_SIZE_MASK) * 8;  // Finds the tile index from x,y coords. Tile size is 8 x 8
				
				pixels[x + y * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.size];
				
			}
		}
	}

}
