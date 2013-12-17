package com.zachdev.game.level;

import java.util.Random;

public class RandomLevel extends Level {
	
	private static final Random random = new Random();

	public RandomLevel(int width, int height) {
		super(width, height);
		
	}
	
	protected void generateLevel() {
		
		for (int y = 0; y < height; y++) {
			
			for (int x = 0; x < width; x++) {
				
				if (y == 25) {					// If we're at the 25th tile (player starting position) make sure there's no rock there 
					
					tiles[x + y * width]= 1;
				}
				else {
					
					tiles[x + y * width]= random.nextInt(3); // Generates a random tile between 0 and 3
				}
				
				
			}
		}
	}
	
	

}
