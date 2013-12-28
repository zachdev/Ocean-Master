package com.zachdev.game.graphics;

/**
 * Represents a graphical sprite on the canvas
 * @author zach
 *
 */
public class Sprite {
	
	public int size;
	
	private int x, y;
	
	public int[] pixels;
	
	protected SpriteSheet sheet;
	
	protected int width, height;
	
	protected int width, height;
	
	public static Sprite voidSprite = new Sprite(16, 0); // Creates a tile with all black colors
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite brick = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite water = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite water1 = new Sprite(16, 2, 1, SpriteSheet.tiles);
	public static Sprite dirt = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 4, 0, SpriteSheet.tiles);
	
	public static Sprite cannonBall = new Sprite(16, 5, 0, SpriteSheet.tiles);
	
	public static Sprite islandTree = new Sprite(16, 0, 1, SpriteSheet.tiles);
	
	public static Sprite waterEdgeTopLeft = new Sprite(16, 0, 3, SpriteSheet.tiles);
	public static Sprite waterEdgeTopMiddle = new Sprite(16, 1, 3, SpriteSheet.tiles);
	public static Sprite waterEdgeTopRight = new Sprite(16, 3, 3, SpriteSheet.tiles);
	public static Sprite waterEdgeMiddleLeft = new Sprite(16, 0, 4, SpriteSheet.tiles);
	public static Sprite sand = new Sprite(16, 1, 4, SpriteSheet.tiles);
	public static Sprite waterEdgeMiddleRight = new Sprite(16, 3, 4, SpriteSheet.tiles);
	public static Sprite waterEdgeBottomLeft = new Sprite(16, 0, 6, SpriteSheet.tiles);
	public static Sprite waterEdgeBottomMiddle = new Sprite(16, 1, 6, SpriteSheet.tiles);
	public static Sprite waterEdgeBottomRight = new Sprite(16, 3, 6, SpriteSheet.tiles);
<<<<<<< HEAD
=======
	
	
	public static Sprite explosion1 = new Sprite(16, 0, 2, SpriteSheet.tiles);
	public static Sprite explosion2 = new Sprite(16, 1, 2, SpriteSheet.tiles);

	public static Sprite explosion3 = new Sprite(16, 2, 2, SpriteSheet.tiles);
	public static Sprite explosion4 = new Sprite(16, 3, 2, SpriteSheet.tiles);
	public static Sprite explosion5 = new Sprite(16, 4, 2, SpriteSheet.tiles);


	
	
	
	
	
>>>>>>> ad915c85f7f824be629cc8c431642fd3dc38b240
	
	
	public static Sprite explosion1 = new Sprite(16, 0, 2, SpriteSheet.tiles);
	public static Sprite explosion2 = new Sprite(16, 1, 2, SpriteSheet.tiles);

	public static Sprite explosion3 = new Sprite(16, 2, 2, SpriteSheet.tiles);
	public static Sprite explosion4 = new Sprite(16, 3, 2, SpriteSheet.tiles);
	public static Sprite explosion5 = new Sprite(16, 4, 2, SpriteSheet.tiles);
	
	public static Sprite enemyUp = new Sprite(16, 0, 8, SpriteSheet.tiles);				// Enemy tiles and animations
	public static Sprite enemyDown = new Sprite(16, 0, 10, SpriteSheet.tiles);
	public static Sprite enemyLeft = new Sprite(16, 0, 11, SpriteSheet.tiles);
	public static Sprite enemyRight = new Sprite(16, 0, 9, SpriteSheet.tiles);
	
	public static Sprite enemyUp1 = new Sprite(16, 1, 8, SpriteSheet.tiles);
	public static Sprite enemyDown1 = new Sprite(16, 1, 10, SpriteSheet.tiles);
	public static Sprite enemyLeft1 = new Sprite(16, 1, 11, SpriteSheet.tiles);
	public static Sprite enemyRight1 = new Sprite(16, 1, 9, SpriteSheet.tiles);
	
<<<<<<< HEAD
	
	// For loading an individual sprite from a pixels array
	public Sprite(int[] pixels, int width, int height) {
		
		if (width == height) this.size = width;
		else this.size = -1;
		
		this.height = height;
		this.width = width;
		
		this.pixels = pixels;
	}
	
	public Sprite(int size, SpriteSheet sheet) {
		
		if (this.width == this.height) this.size = width;
		else this.size = -1;
		
		this.width = size;
		this.height = size;
		this.sheet = sheet;
		
		
	}
	
=======
>>>>>>> ad915c85f7f824be629cc8c431642fd3dc38b240
	public Sprite (SpriteSheet sheet, int width, int height) {
		
		if (width == height) this.size = width;
		else this.size = -1;
		
		this.sheet = sheet;
		this.width = width;
		this.height = height;
	}
	
	public Sprite(int size, int x, int y, SpriteSheet spriteSheet) {
		
		pixels = new int[size * size]; 		// Creates pixel array of sprite size
		
		this.width = size;
		this.height = size;
		
		this.size = size;
		this.x = x * size; 					// Gets sprite grid location from x, y pixels coordinates
		this.y = y * size;					
		this.sheet = spriteSheet;
		
		loadSprite();
		
	}
	
	/**
	 * Constructor that creates a new Sprite with the given size and color value
	 * 
	 * @param size
	 * @param color
	 */
	public Sprite (int size, int color) {
		
		this.size = size;
		pixels = new int[size * size];
		setColor(color);
	}
	
	private void setColor(int color) {
		
		for (int i = 0; i < size * size; i++) {
			
			pixels[i] = color;
		}
	}
	/**
	 * Loads specific sprite from sprite sheet
	 */
	private void loadSprite() {
		
		for (int y = 0; y < this.size; y++) {
			
			for (int x = 0; x < this.size; x++) {
				
				pixels[x + y * this.size] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.size]; 
			}
		}
		
		
	}

}
