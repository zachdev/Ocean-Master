package com.zachdev.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Handles all the keyboard events
 * 
 * @author zach
 *
 */
public class Keyboard implements KeyListener{
	
	private boolean[] keys = new boolean[120]; // Create array of keys for each key on keyboard
	
	public boolean up, down, left, right;
	
	public void tick() {
		
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		
		for (int i = 0; i < keys.length; i++) {
			
			//if (keys[i]){
				
				//System.out.println("KEY: " + i);
			//}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		keys[e.getKeyCode()] = true; // Grab the ID of the key we're pressing and set it to true in the keys array
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		keys[e.getKeyCode()] = false;
		
	}

}
