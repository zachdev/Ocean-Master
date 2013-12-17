package com.zachdev.game.level;

import com.zachdev.game.graphics.Screen;
import com.zachdev.game.level.tile.Tile;

public class Level {
	
	private final int TILE_SIZE = 16;
	
	protected int width, height;
	
	protected int[] tiles;
	
	public Level(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		tiles = new int[width * height];
		
		generateLevel();
	}
	
	public Level (String filePath) {
		
		loadLevel(filePath);
	}
	
	protected void generateLevel() {
		
		
	}
	
	private void loadLevel(String filePath) {
		
		
	}
	
	/**
	 * Updates events in the level (mobs moving around, etc)
	 */
	public void tick() {
		
		
	}
	
	
	public void render(int xScroll, int yScroll, Screen screen) {
		
		screen.setOffset(xScroll, yScroll);	// Sets offset from location of player
		
		// Corner pins - x,y coordinates of top left and bottom right corners of the rendering area
		
		int x0	= xScroll / TILE_SIZE;					// leftmost X coordinate of the screen area we are rendering
		int x1 = (xScroll + screen.width) / TILE_SIZE; 	// rightmost X coordinate of the screen area we are rendering
		
		int y0 = yScroll / TILE_SIZE;					// leftmost y coordinate
		int y1 = (yScroll + screen.height) / TILE_SIZE;	// rightmost x coordinate
		
		
		for (int y = y0; y < y1; y++) { 			// Loops through the rendered window space and renders the tiles
			
			for (int x = x0; x < x1; x++) {
				
				getTile(x, y).render(x, y, screen);		// Renders the tile onto the screen
				
			}
		}
	}
	
	
	/**
	 * Returns the tile at the provided x and y coordinates (in the grid location)
	 * @param x
	 * @param y
	 * @return
	 */
	public Tile getTile(int x, int y) {
		
		if (tiles[x + y * width]== 0) {
			
			return Tile.grass;
		}

		if (tiles[x + y * width]== 1) {
			
			return Tile.brick;
		}

		if (tiles[x + y * width]== 2 || tiles[x + y * width]== 3) {
	
			return Tile.water;
		}
		
		return Tile.voidTile;		// Return a void tile if nothing else
		
		
	}
	
	private void time() {
		
		
	}

}
