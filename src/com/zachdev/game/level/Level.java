package com.zachdev.game.level;

import java.util.ArrayList;
import java.util.List;

import com.zachdev.game.entity.Entity;
import com.zachdev.game.graphics.Screen;
import com.zachdev.game.level.tile.Tile;

public class Level {

	private static final int TILE_SIZE = Tile.TILE_SIZE;

	private int animate = 0; // Used for the background animation

	private List<Entity> entities = new ArrayList<Entity>(); // A list of all
																// entities on
																// the level

	protected int width, height;

	protected int[] tiles;

	/**
	 * Constructor that instantiates a new Level with a given width and height
	 * (in tile precision)
	 * 
	 * @param width
	 * @param height
	 */
	public Level(int width, int height) {

		this.width = width;
		this.height = height;

		tiles = new int[width * height];

		generateLevel();
	}

	public Level(String filePath) {

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

		if (animate < 10000) { // Here we have a limit on animate, so it doesn't
								// keep incrementing until a crash

			animate++; // Increase animation count
		} else {

			animate = 0; // Else reset the animation count to 0 after 10000
							// ticks so game doesn't crash
		}

		for (int i = 0; i < entities.size(); i++) { // Update all entities

			entities.get(i).tick();
		}

		remove();

	}

	private void remove() {

		for (int i = 0; i < entities.size(); i++) { // Update all entities

			if (entities.get(i).isRemoved()) {

				entities.remove(i);
			}
		}
	}

	public void render(int xScroll, int yScroll, Screen screen) {

		screen.setOffset(xScroll, yScroll); // Sets offset from location of
											// player

		// Corner pins - x,y coordinates of top left and bottom right corners of
		// the rendering area

		int x0 = xScroll / TILE_SIZE; // leftmost X coordinate of the screen
										// area we are rendering
		int x1 = (xScroll + screen.width + TILE_SIZE) / TILE_SIZE; // rightmost
																	// X
																	// coordinate
																	// of the
																	// screen
																	// area we
																	// are
																	// rendering

		int y0 = yScroll / TILE_SIZE; // leftmost y coordinate
		int y1 = (yScroll + screen.height + TILE_SIZE) / TILE_SIZE; // rightmost
																	// x
																	// coordinate

		for (int y = y0; y < y1; y++) { // Loops through the rendered window
										// space and renders the tiles

			for (int x = x0; x < x1; x++) {

				getTile(x, y).render(x, y, screen); // Renders the tile onto the
													// screen

			}
		}

		for (int i = 0; i < entities.size(); i++) { // Render all our entities

			entities.get(i).render(screen);
		}
	}

	public void add(Entity e) {

		entities.add(e); // Add entity to our list of Entities
	}

	/**
	 * Returns the tile at the provided x and y coordinates (in the grid
	 * location)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Tile getTile(int x, int y) {

		int pixelLocation = x + y * width;

		if (x < 0 || y < 0 || x >= width || y >= height) { // If we exceed the
															// tile index just
															// return a void
															// tile

			return Tile.voidTile;
		}

		if (tiles[pixelLocation] == 0xFF666699) {

			return Tile.rock;
		}

		// Water Edge Tiles
		if (tiles[pixelLocation] == 0xFFcccc00) {

			return Tile.waterEdgeTopLeft;
		}
		
		if (tiles[pixelLocation] == 0xFFcc9900) {

			return Tile.waterEdgeTopMiddle;
		}
		if (tiles[pixelLocation] == 0xFFffcc00) {

			return Tile.waterEdgeTopRight;
		}
		if (tiles[pixelLocation] == 0xFF99cc00) {

			return Tile.waterEdgeMiddleLeft;
		}
		if (tiles[pixelLocation] == 0xFF99ff00) {

			return Tile.waterEdgeMiddleRight;
		}
		if (tiles[pixelLocation] == 0xFFff6600) {

			return Tile.waterEdgeBottomLeft;
		}
		if (tiles[pixelLocation] == 0xFF996600) {

			return Tile.waterEdgeBottomMiddle;
		}if (tiles[pixelLocation] == 0xFF669900) {

			return Tile.waterEdgeBottomRight;
		}
		
		if (tiles[pixelLocation] == 0xFF666600) {

			return Tile.sand;
		}
		
		if (tiles[pixelLocation] == 0xFF006600) {

			return Tile.islandTree;
		}
		
	
		
		

		if (tiles[pixelLocation] == 0xFF0000cc) {

			// System.out.println(animate % 90 == 2);

			if (animate % 90 >= 6) { // animate incrases by 60 each second
				// Whenever the remainder of anim and 20 is > 10 (50% of the
				// time)
				return Tile.water;
			} else {

				return Tile.water1;
			}
		}

		return Tile.voidTile; // Return a void tile if nothing else

	}

	private void time() {

	}

}