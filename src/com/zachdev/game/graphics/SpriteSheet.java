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
	
	private String path;
	
	protected int width, height;
	
	public int size;
	
	public int[] pixels;

	public Sprite[] sprites;

	
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
		
		for (int y0 = 0; y0 < this.height; y0++) {
			
			for (int x0 = 0; x0 < this.width; x0++) {
				
				//Sprite sprite = new Sprite()
				
				
			}
		}
	}
	
	public SpriteSheet(String path, int size) {
		
		this.path = path;
		this.size = size;
		
		pixels = new int[size * size]; // Sets the size of the sprite sheet
		
		loadSprite();
	
	}
	
	public Sprite[] getSprite() {
		
		return null;
	}
	
	private void loadSprite() {
		
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
