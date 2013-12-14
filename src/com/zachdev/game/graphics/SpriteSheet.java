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
	
	public int size;
	
	public int[] pixels;

	
	public SpriteSheet(String path, int size) {
		
		this.path = path;
		this.size = size;
		
		pixels = new int[size * size]; // Sets the size of the sprite sheet
		
		loadSprite();
	
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
