package com.zachdev.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Manages the sprite sheet
 * 
 * @author zach
 *
 */
public class SpriteSheet {
	
	public static SpriteSheet tiles = new SpriteSheet("/graphics/spritesheet.png", 256);
	
	public static SpriteSheet playerSheet = new SpriteSheet("/graphics/textures/sheets/playerSheet.png", 80, 32);
	
	public static SpriteSheet playerDown = new SpriteSheet(playerSheet, 0, 0, 1, 2, 16);
	
	public static SpriteSheet playerUp = new SpriteSheet(playerSheet, 1, 0, 1, 2, 16);

	public static SpriteSheet playerLeft = new SpriteSheet(playerSheet, 2, 0, 1, 2, 16);

	public static SpriteSheet playerRight = new SpriteSheet(playerSheet, 3, 0, 1, 2, 16);
	
	public static SpriteSheet playerStanding = new SpriteSheet(playerSheet, 4, 0, 1, 1, 16);


	public static SpriteSheet playerShipSheet = new SpriteSheet("/graphics/textures/sheets/playerShipSheet.png", 80, 32);
	
	public static SpriteSheet playerShipUp = new SpriteSheet(playerShipSheet, 0, 0, 1, 2, 16);
	
	public static SpriteSheet playerShipDown = new SpriteSheet(playerShipSheet, 1, 0, 1, 2, 16);

	public static SpriteSheet playerShipLeft = new SpriteSheet(playerShipSheet, 2, 0, 1, 2, 16);

	public static SpriteSheet playerShipRight = new SpriteSheet(playerShipSheet, 3, 0, 1, 2, 16);
	
	//public static SpriteSheet playerShipStanding = new SpriteSheet(playerSheet, 4, 0, 1, 1, 16);
	
	
	public static SpriteSheet wizardSheet = new SpriteSheet("/graphics/textures/sheets/wizardSheet.png", 50, 70);
	
	public static SpriteSheet blueWizardSheet = new SpriteSheet(wizardSheet, 0, 0, 1, 4, 16);
	public static SpriteSheet greenWizardSheet = new SpriteSheet(wizardSheet, 1, 0, 1, 4, 16);
	public static SpriteSheet redWizardSheet = new SpriteSheet(wizardSheet, 2, 0, 1, 4, 16);
	
	public static SpriteSheet oldManSheet = new SpriteSheet("/graphics/textures/sheets/oldManSheet.png", 64, 32);
	
	public static SpriteSheet oldManDown = new SpriteSheet(oldManSheet, 0, 0, 1, 2, 16);
	public static SpriteSheet oldManUp = new SpriteSheet(oldManSheet, 1, 0, 1, 2, 16);
	public static SpriteSheet oldManLeft = new SpriteSheet(oldManSheet, 2, 0, 1, 2, 16);
	public static SpriteSheet oldManRight = new SpriteSheet(oldManSheet, 3, 0, 1, 2, 16);





	
	
	
	
	private String path;
	
	protected int width, height;
	
	public int size;
	
	public int[] pixels;

	public Sprite[] sprites;

	// For a standard square sprite sheet
	public SpriteSheet(String path, int size) {
		
		this.path = path;
		this.size = size;
		this.width = size;
		this.height = size;
		
		pixels = new int[size * size]; // Sets the size of the sprite sheet
		
		loadSpriteSheet();
	
	}
	
	// For non-square sprite sheets
	public SpriteSheet(String path, int width, int height) {
		
		this.path = path;
		this.size = -1;
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];
		loadSpriteSheet();
	}
	
	// Sub sheet (specify subsection of sheet to crop out)
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		
		int w = width * spriteSize;
		int h = height * spriteSize;
		
		if (width == height) {
			
			this.size = width;
		}
		else {
			this.size = -1;
		}
		
		this.width = w;
		this.height = h;
		
		pixels = new int[w * h];
		
		for (int y0 = 0; y0 < h; y0++) {
			
			int yp = yy + y0;
			
			for (int x0 = 0; x0 < w; x0++) {
				
				int xp = xx + x0;
				
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.width];
			}
		}
		
		int frame = 0;
		sprites = new Sprite[width * height];
		
		// Iterates through the sprites and inserts them into pixel array
		for (int ya = 0; ya < height; ya++) {
			
			for (int xa = 0; xa < width; xa++) {  // In tile precision
				//frame++;
				
				int[] spritePixels = new int[spriteSize * spriteSize]; // Stores one sprite from the pixels[] array
				
				for (int y0 = 0; y0 < spriteSize; y0++) { 	// In pixel precision
					
					for (int x0 = 0; x0 < spriteSize; x0++) {
						
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * this.width];				
					}
				}
				
				//if (frame > width || frame > height) frame = 0;
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
		
	}
	
	
	public Sprite[] getSprites() {
		
		return sprites;
	}
	
	
	private void loadSpriteSheet() {
		
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path)); // Creates a buffered image with the sprite sheet
			
			int width = image.getWidth();
			int height = image.getHeight();
			
			image.getRGB(0, 0, width, height, pixels, 0, width); // Scans the image and stores color values in pixels[]
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
