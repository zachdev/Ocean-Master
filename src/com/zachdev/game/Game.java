package com.zachdev.game;

import javax.swing.*;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.zachdev.game.graphics.Screen;
import com.zachdev.game.input.Keyboard;

/**
 * Main game class
 * 
 * @author zach
 *
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static int width = 300;
	
	//public static int height = width / 16 * 9;
	public static int height = 168;
	
	public static int scale = 3;
	
	private Thread thread;
	
	private boolean running = false;
	
	private JFrame frame;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // Image with buffer
	
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // Converts image to RGB pizel values
	
	private Screen screen;
	
	private Keyboard keyboard;
	
	int x = 0, y = 0;
	
	public static String title = "Game";
	
	public Game() {
		
		Dimension size = new Dimension(width * scale, height * scale); // The dimensions of the canvas
		
		this.setPreferredSize(size);	// Set the canvas size
		
		screen = new Screen(width, height);
		
		frame = new JFrame();
		
		keyboard = new Keyboard();
		
		this.addKeyListener(keyboard); // Adds the keyboard listener to the canvas
		
		
	}
	
	public synchronized void start() {
		
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop() {
		
		running = false;
		
		try {
			
			thread.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

	public void run() {
		
		long lastTime = System.nanoTime(); // Get the system time in nanoseconds
		
		long timer = System.currentTimeMillis();
		
		final double ns = 1000000000.0 / 60.0; // 60 frames per second
		
		double delta = 0; // Delta time
		
		int frames = 0; // Tracks how many times render() gets called per second
		
		int ticks = 0;	// Tracks how many times tick() is called per sec. Should be 60 at all times (60 fps)
		
		
		requestFocus(); // request focus for the canvas window (so we can move without clicking first)
		// The game loop
		while (running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) {		// This will only update 60 times per second

				tick();		// Updates 60 times/sec, graphics
				ticks++;
				delta--;
				
			}
			
			render();	// Updates as many times as possible per sec, logic
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) { // This will execute once per second
				
				timer += 1000;
				System.out.println(ticks + " fps, " + frames + " ticks/sec");
				frame.setTitle(Game.title + " | " + ticks + " fps, " + frames + " ticks/sec");
				ticks = 0;
				frames = 0;
			}
			
		}
		
		stop();
		
	}
	
	public void tick() {
		
		keyboard.tick(); // Update the keyboard input
		
		if (keyboard.up) y--;			// If we press up, move the map down
		if (keyboard.down) y++;			// If we press down, move the map up
		if (keyboard.left) x--;			// If we press left, move the map right
		if (keyboard.right) x++;		// If we press right, move the map left
		
		
	}
	
	public void render() {
		
		BufferStrategy bs = getBufferStrategy();	// Grab the buffer strategy from the Canvas superclass
		
		if (bs == null) {	// If the buffer strategy hasn't been created 
			
			createBufferStrategy(3);	// Create Triple buffering
			return;
		}
		
		screen.clear();			// Clear the screen right before each render
		screen.render(x, y);	// Render the screen
		
		
		for (int i = 0; i < pixels.length; i++) {
			
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();		// Link between graphics and buffer
		// All graphics work between here and g.dispose()
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose(); // Releases all graphics/resources in the frame
		bs.show();
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
}
