package com.zachdev.game.level;

import com.zachdev.game.level.tile.Tile;

/**
 * Represents a location on the tile grid
 * 
 * @author zach
 * 
 */
public class TileCoordinate {

	private static final int TILE_SIZE = Tile.TILE_SIZE;

	private int x, y;

	public TileCoordinate(int x, int y) {

		this.x = x * TILE_SIZE; // Converts tile precision to pixel precision
		this.y = y * TILE_SIZE;
	}

	public int getX() {

		return x;
	}

	public int getY() {

		return y;
	}
}
