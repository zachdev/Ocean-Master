package com.zachdev.game;

import javax.swing.*;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.zachdev.game.entity.mob.Enemy;
import com.zachdev.game.entity.mob.Player;
import com.zachdev.game.graphics.Screen;
import com.zachdev.game.input.Keyboard;
import com.zachdev.game.level.Level;
import com.zachdev.game.level.LoadedLevel;
import com.zachdev.game.level.TileCoordinate;
import com.zachdev.game.level.tile.Tile;

/**
 * Main game class
 * 
 * @author zach
 *
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static String title = "Ocean Master";

	private static int WINDOW_WIDTH = 300;
	
	private static int WINDOW_HEIGHT = WINDOW_WIDTH / 16 * 9;
	
	private static int SCALE = 3;
	
	public static final int TILE_SIZE = Tile.TILE_SIZE;
	
	
	private Thread thread;
	
	private JFrame frame;
	
	private Screen screen;
	
	private Keyboard keyboard;
	
	private Level level;
	
	private Player player;
	
	private Enemy enemy;
	

	
	private BufferedImage image;
	
	private int[] pixels;
	
	private boolean running = false;
	
	
	int frames = 0; // Tracks how many times render() gets called per second
	
	int ticks = 0;	// Tracks how many times tick() is called per sec. Should be 60 at all times (60 fps)

	
	int x = 0;
	int y = 0;
	
	long fpsUpdateTime = System.currentTimeMillis();

	public Game() {
		
		screen = new Screen(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		frame = new JFrame();
		
		keyboard = new Keyboard();
		
		image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB); // Image with buffer
		
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // Converts image to RGB pizel values
		

		
		level = new LoadedLevel("/graphics/level.png");
		
		//level = new RandomLevel(64, 64);
		
		TileCoordinate playerSpawnLocation = new TileCoordinate(6,5);		
		
		player = new Player(playerSpawnLocation.getX(), playerSpawnLocation.getY(), keyboard);	// Spawn player at tile 6,5 
		
		enemy = new Enemy(8, 6);
		
		player.initialize(level);
		
		level.add(enemy);
	
		
		this.addKeyListener(keyboard); // Adds the keyboard listener to the canvas
		this.setPreferredSize(new Dimension(WINDOW_WIDTH * SCALE, WINDOW_HEIGHT * SCALE));	// Set the canvas size
		
		frame.setResizable(false);
		frame.setTitle(Game.title);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}
	
	/**
	 * Initializes and starts the game thread
	 */
	public synchronized void start() {
		
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	
	/**
	 * Stops the game when called
	 */
	public synchronized void stop() {
		
		running = false;
		
		try {
			
			thread.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * Main game loop
	 */
	public void run() {
		
		long lastTime = System.nanoTime(); // Get the system time in nanoseconds
		
		long timer = System.currentTimeMillis();
		
		final double ns = 1000000000.0 / 60.0; // 60 frames per second
		
		double delta = 0; // Delta time
		
		frames = 0; // Tracks how many times render() gets called per second
		
		ticks = 0;	// Tracks how many times tick() is called per sec. Should be 60 at all times (60 fps)
		
		
		requestFocus(); // request focus for the canvas window (so we can move without clicking first)
		// The game loop
		while (running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) {		// This will only update 60 times per second

				tick();		// Updates 60 times/sec, logic
				ticks++;
				delta--;
				
			}
			
			render();	// Updates as many times as possible per sec, graphics
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) { // This will execute once per second
				
				timer += 1000;
				//System.out.println(ticks + " fps, " + frames + " ticks/sec");
				frame.setTitle(Game.title + "" + ticks + " fps, " + frames + " ticks/sec");
				ticks = 0;
				frames = 0;
			}
			
		}
		
		stop();
		
	}
	
	/**
	 * With every tick, the inputs, player, mob, level, etc. get updated (gets called a fixed 60 times per sec)
	 */
	public void tick() {
		
		keyboard.tick(); 			// Update the keyboard input
		player.tick();				// Update player logic
		level.tick();				// Update level logic

		
		if (player.x < 0) player.x = 0;					// Temporary Player constraints
		if (player.y < 0) player.y = 0;
		if (player.x > 1008) player.x = 1008;
		if (player.y > 1008) player.y = 1008;
		
	}
	/**
	 * Renders everything on the screen (gets called 200+ (limitless) times per sec)
	 */
	public void render() {
		
		BufferStrategy bs = getBufferStrategy();		// Grab the buffer strategy from the Canvas superclass
		
		if (bs == null) {								// If the buffer strategy hasn't been created 
			
			createBufferStrategy(3);					// Create Triple buffering
			return;
		}
		
		screen.clear();									// Clear the screen right before each render
		
		int xScroll = player.x - screen.width / 2;		// Sets the offset of the map to the player
		int yScroll = player.y - screen.height / 2;		
		
		level.render(xScroll, yScroll, screen); 		// Renders the level
		player.render(screen);							// Renders the player
		
		
		for (int i = 0; i < pixels.length; i++) {		// Move screen data into buffer	
			
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();				// Fetch from buffer
		
		// All graphics work between here and g.dispose()
		g.setColor(Color.BLACK);						// Set screen to black initially
		g.fillRect(0, 0, getWidth(), getHeight());					// Fill the screen
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);	// Draw the screen image
		g.setColor(Color.WHITE);
		
		// For debugging
		g.setFont(new Font("Verdana", 0, 36));				
		g.drawString("X: " + player.x + " Y: " + player.y, 1, 36);
		g.drawString(String.format("Tile (%d, %d)", player.x/16, player.y/16), 1, 73);
		
		g.dispose(); // Releases all graphics/resources in the frame
		bs.show();
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		game.start();
	}
}
