package com.zachdev.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.zachdev.game.entity.mob.BlueWizard;
import com.zachdev.game.entity.mob.Enemy;
import com.zachdev.game.entity.mob.GreenWizard;
import com.zachdev.game.entity.mob.OldMan;
import com.zachdev.game.entity.mob.Player;
import com.zachdev.game.entity.mob.RedWizard;
import com.zachdev.game.entity.mob.Wizard;
import com.zachdev.game.entity.vehicle.PlayerShip;
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
	
	private static final String GAME_TITLE = "Ocean Master";

	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HEIGHT = WINDOW_WIDTH / 16 * 9;
	
	private final double frameRate = 1000000000.0 / 60.0; // 60 frames per second, update every 17 milliseconds
	
	private static final int SCALE = 3;
	
	private static final int TILE_SIZE = Tile.TILE_SIZE;
	
	
	private Thread thread;
	
	private JFrame frame;
	
	private Screen screen;
	
	private Keyboard keyboard;
	
	private Level level;
	
	private Player player;
	
	private PlayerShip playerShip;
	
	private Enemy enemy;
	
	
	private Wizard blueWizard, greenWizard, redWizard;
	
	private OldMan oldMan;
	

	
	private BufferedImage image;
	
	private int[] pixels;
	
	private boolean running = false;
	
	
	private int frames = 0; // Tracks how many times render() gets called per second
	
	private int ticks = 0;	// Tracks how many times tick() is called per sec. Should be 60 at all times (60 fps)

	public Game() {
		
		screen = new Screen(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		frame = new JFrame();
		
		keyboard = new Keyboard();
		
		image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB); // Image with buffer
		
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // Converts image to RGB pizel values
		

		
		level = new LoadedLevel("/graphics/level2.png");
		
		//level = new RandomLevel(64, 64);
		
		TileCoordinate playerSpawnLocation = new TileCoordinate(10,49);	
		TileCoordinate playerShipSpawnLocation = new TileCoordinate(10,47);	
		
		TileCoordinate blueWizardSpawnLocation = new TileCoordinate(8,49);
		TileCoordinate greenWizardSpawnLocation = new TileCoordinate(12,53);	

		TileCoordinate redWizardSpawnLocation = new TileCoordinate(7, 55);	
		
		TileCoordinate oldManSpawnLocation = new TileCoordinate(10, 54);	


	

		
		player = new Player(playerSpawnLocation.getX(), playerSpawnLocation.getY(), keyboard);	// Spawn player at tile 6,5 
		playerShip = new PlayerShip(playerShipSpawnLocation.getX(), playerShipSpawnLocation.getY(), keyboard);
		
		level.add(playerShip);
		playerShip.initialize(level);
		
		player.initialize(level);
		level.add(player);
<<<<<<< HEAD
		
		blueWizard = new BlueWizard(blueWizardSpawnLocation.getX(), blueWizardSpawnLocation.getY());
		level.add(blueWizard);
		blueWizard.initialize(level);
		
		greenWizard = new GreenWizard(greenWizardSpawnLocation.getX(), greenWizardSpawnLocation.getY());
		level.add(greenWizard);
		greenWizard.initialize(level);
		
		redWizard = new RedWizard(redWizardSpawnLocation.getX(), redWizardSpawnLocation.getY());
		level.add(redWizard);
		redWizard.initialize(level);
		
		oldMan = new OldMan(oldManSpawnLocation.getX(), oldManSpawnLocation.getY());
		level.add(oldMan);
		oldMan.initialize(level);
=======
>>>>>>> ad915c85f7f824be629cc8c431642fd3dc38b240
		
		
		enemy = new Enemy(8, 6, 1);
		level.add(enemy);
		enemy.initialize(level);
		
		Enemy enemy2 = new Enemy(4, 10, 0);
		level.add(enemy2);
		enemy2.initialize(level);
	
		Enemy enemy3 = new Enemy(11, 9, 1);
		level.add(enemy3);
		enemy3.initialize(level);
		
		Enemy enemy4 = new Enemy(10, 15, 0);
		level.add(enemy4);
		enemy4.initialize(level);
		
		this.addKeyListener(keyboard); // Adds the keyboard listener to the canvas
		this.setPreferredSize(new Dimension(WINDOW_WIDTH * SCALE, WINDOW_HEIGHT * SCALE));	// Set the canvas size

		
		frame.setResizable(false);
		frame.setTitle(Game.GAME_TITLE);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		this.requestFocus(); // request focus for the game window (so we can move without clicking first)
	}
	
	/**
	 * Initializes and starts the game thread
	 */
	public synchronized void start() {
		
		running = true;
		thread = new Thread(this, "DisplayThread");
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
			
		double delta = 0; // Delta time
		
		frames = 0; // Tracks how many times render() gets called per second
		
		ticks = 0;	// Tracks how many times tick() is called per sec. Should be 60 at all times
		
		// The game loop
		while (running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / frameRate;
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
				frame.setTitle(String.format("%s | Resolution: %dx%d | %d ticks/sec, %d fps", Game.GAME_TITLE, WINDOW_WIDTH * SCALE, WINDOW_HEIGHT * SCALE, ticks, frames));
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
<<<<<<< HEAD
=======
		//player.tick();				// Update player logic
>>>>>>> ad915c85f7f824be629cc8c431642fd3dc38b240
		level.tick();				// Update level entities (including player and mobs)
	}
	/**
	 * Renders everything on the screen (gets called 200+ (limitless) times per sec)
	 */
	public void render() {
		
		BufferStrategy buffer = getBufferStrategy();		// Grab the buffer strategy from the Canvas superclass
		
		if (buffer == null) {								// If the buffer strategy hasn't been created 
			
			createBufferStrategy(2);					// Create double buffering strategy
			return;
		}
		
		screen.clear();									// Clear the screen right before each render
		
		
		
		int xScroll = player.x - screen.width / 2;		// Sets the offset of the map to the player
		int yScroll = player.y - screen.height / 2;		// So we can scroll around the map
		
		
		
		
		level.render(xScroll, yScroll, screen); 		// Renders the level
		player.render(screen);							// Renders the player
		
		
		for (int i = 0; i < pixels.length; i++) {		// Move screen data into buffer	
			
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = buffer.getDrawGraphics();				// Fetch from buffer
		
		// All graphics work between here and g.dispose()
		g.setColor(Color.BLACK);						// Set screen to black initially
		g.fillRect(0, 0, getWidth(), getHeight());					// Fill the screen
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);	// Draw the screen image
		g.setColor(Color.WHITE);
		
		// For debugging
		g.setFont(new Font("Verdana", 0, 36));				
		g.drawString("X: " + player.x + " Y: " + player.y, 1, 36);
		g.drawString(String.format("Tile (%d, %d)", player.x / TILE_SIZE, player.y / TILE_SIZE), 1, 73);
		
		g.dispose(); // Releases all graphics/resources in the frame
		buffer.show();
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		game.start();
	}
}
