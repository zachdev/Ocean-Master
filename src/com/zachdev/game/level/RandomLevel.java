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
				tiles[x + y * width]= random.nextInt(3); // Generates a random tile between 0 and 3
				
				if (x == 17) {
					
					tiles[x + y * width]= 0;
				}
				
				
			}
		}
	}
	
	

}
