package com.zachdev.game.graphics;

public class AnimatedSprite extends Sprite {
	
	private int frame = 0;
	
	private Sprite sprite;
	
	private int rate = 5;
	
	private int animationLength = -1;
	
	
	
	
	
	public AnimatedSprite (SpriteSheet spriteSheet, int width, int height, int animationLength) {
		
		super(spriteSheet, width, height);
		this.animationLength = animationLength;
		
		
		
		
	}
	
	public void tick() {
		
		if (frame > animationLength) frame = 0;
		else frame++;
		
		sprite = 
		
		
	}
	
	public Sprite getSprite() {
		
		return sprite;
	}

	/**
	 * Sets the rate of the animation
	 * @param frameRate
	 */
	public void setFrameRate(int frameRate) {
		rate = frameRate;
		
	}

}
