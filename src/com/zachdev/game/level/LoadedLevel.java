package com.zachdev.game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.KeyStore.LoadStoreParameter;

import javax.imageio.ImageIO;

import com.zachdev.game.entity.mob.Enemy;
import com.zachdev.game.level.tile.Tile;

public class LoadedLevel extends Level {


	public LoadedLevel(String filePath) {
		
		super(filePath);
		loadLevel(filePath);
		
	}
	
	/**
	 * Performs the loading of a level file from an image, converts each pixel into a RGB color value
	 * and then loads these pixel values into the tiles[] array
	 * 
	 * @param filePath
	 */
	protected void loadLevel(String filePath) {
		
		
		try {
			
			BufferedImage image = ImageIO.read(LoadedLevel.class.getResource(filePath));
			
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			
			tiles = new int[w * h];
			
			image.getRGB(0, 0, w, h, tiles, 0 , w);
		}
		catch (IOException e) {
			
			e.printStackTrace();
			System.out.println("Level file could not be loaded");
		}
		
		
		
	}
	
	/**
	 * Converts pixels into tiles
	 * 
	 * Rock = 0x666699
	 * Water = 0x0000cc
	 */
	protected void generateLevel() {
		
		//System.out.println("Tiles: " + tiles[0]);
		
		
	}
	
	

}
