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
	
	private SpriteSheet sheet;
	
	public static Sprite voidSprite = new Sprite(16, 0); // Creates a tile with all black colors
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite brick = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite water = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite water1 = new Sprite(16, 2, 1, SpriteSheet.tiles);
	public static Sprite dirt = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 4, 0, SpriteSheet.tiles);
	
	public static Sprite bomb = new Sprite(16, 5, 0, SpriteSheet.tiles);
	
	public static Sprite playerUp = new Sprite(16, 0, 12, SpriteSheet.tiles);
	public static Sprite playerDown = new Sprite(16, 0, 14, SpriteSheet.tiles);
	public static Sprite playerLeft = new Sprite(16, 0, 15, SpriteSheet.tiles);
	public static Sprite playerRight = new Sprite(16, 0, 13, SpriteSheet.tiles);
	
	public static Sprite playerUp1 = new Sprite(16, 1, 12, SpriteSheet.tiles);			// Player animations
	public static Sprite playerDown1 = new Sprite(16, 1, 14, SpriteSheet.tiles);
	public static Sprite playerLeft1 = new Sprite(16, 1, 15, SpriteSheet.tiles);
	public static Sprite playerRight1 = new Sprite(16, 1, 13, SpriteSheet.tiles);
	
	public static Sprite enemyUp = new Sprite(16, 0, 8, SpriteSheet.tiles);				// Enemy tiles and animations
	public static Sprite enemyDown = new Sprite(16, 0, 10, SpriteSheet.tiles);
	public static Sprite enemyLeft = new Sprite(16, 0, 11, SpriteSheet.tiles);
	public static Sprite enemyRight = new Sprite(16, 0, 9, SpriteSheet.tiles);
	
	public static Sprite enemyUp1 = new Sprite(16, 1, 8, SpriteSheet.tiles);
	public static Sprite enemyDown1 = new Sprite(16, 1, 10, SpriteSheet.tiles);
	public static Sprite enemyLeft1 = new Sprite(16, 1, 11, SpriteSheet.tiles);
	public static Sprite enemyRight1 = new Sprite(16, 1, 9, SpriteSheet.tiles);
	
	public Sprite(int size, int x, int y, SpriteSheet spriteSheet) {
		
		pixels = new int[size * size]; 		// Creates pixel array of sprite size
		
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
