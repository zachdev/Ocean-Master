package com.zachdev.game.level.tile;

import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.Sprite;
import com.zachdev.game.level.TileCoordinate;

public class Tile {
	
	public static final int TILE_SIZE = 16;
	
	public TileCoordinate tileCoordinates;
	
	public int x, y; // Tile location on the grid
	
	public Sprite sprite; // Sprite of this tile
	
	//public static Tile grass = new GrassTile(Sprite.grass); // Static object - grass tile grabbed from Sprite class
	
	public static Tile water = new WaterTile(Sprite.water); // Static object - water tile grabbed from Sprite class
	
	public static Tile water1 = new WaterTile(Sprite.water1); // Static object - water tile grabbed from Sprite class
	
	public static Tile rock = new RockTile(Sprite.rock);	// Rock tile, collidable
	
	public static Tile bomb = new WaterTile(Sprite.cannonBall);
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	
	public static Tile waterEdgeTopLeft = new WaterEdgeTile(Sprite.waterEdgeTopLeft); // Static object - water tile grabbed from Sprite class
	public static Tile waterEdgeTopMiddle = new WaterEdgeTile(Sprite.waterEdgeTopMiddle); // Static object - water tile grabbed from Sprite class
	public static Tile waterEdgeTopRight = new WaterEdgeTile(Sprite.waterEdgeTopRight); // Static object - water tile grabbed from Sprite class
	public static Tile waterEdgeMiddleLeft = new WaterEdgeTile(Sprite.waterEdgeMiddleLeft); // Static object - water tile grabbed from Sprite class
	public static Tile waterEdgeMiddleRight = new WaterEdgeTile(Sprite.waterEdgeMiddleRight); // Static object - water tile grabbed from Sprite class
	public static Tile waterEdgeBottomLeft = new WaterEdgeTile(Sprite.waterEdgeBottomLeft); // Static object - water tile grabbed from Sprite class
	public static Tile waterEdgeBottomMiddle = new WaterEdgeTile(Sprite.waterEdgeBottomMiddle); // Static object - water tile grabbed from Sprite class
	public static Tile waterEdgeBottomRight = new WaterEdgeTile(Sprite.waterEdgeBottomRight); // Static object - water tile grabbed from Sprite class

	public static Tile sand = new LandTile(Sprite.sand); // Static object - water tile grabbed from Sprite class
	
	public static Tile islandTree = new RockTile(Sprite.islandTree); // Static object - water tile grabbed from Sprite class


	
	
	
	
	
	
	public Tile(Sprite sprite) {
		
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		
		// Render stuff here
			screen.renderTile(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, this);
			
		}
		
	
	public boolean solid() {
		
		return false;		// False is returned by default in subclasses
	}

}
