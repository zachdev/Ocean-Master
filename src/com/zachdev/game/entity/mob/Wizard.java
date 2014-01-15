package com.zachdev.game.entity.mob;

import java.util.Random;

import com.zachdev.game.graphics.AnimatedSprite;
import com.zachdev.game.graphics.Screen;

public class Wizard extends Mob {
	
	protected AnimatedSprite wizardAnim;
	
	protected Random random = new Random();
	
	protected int[] randomFrameRate = {100, 150, 200, 250, 300, 400};
	
	public Wizard(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}

	@Override
	public void tick() {
		wizardAnim.setFrameRate(randomFrameRate[random.nextInt(5)]);
		wizardAnim.tick();

	}

	@Override
	public void render(Screen screen) {
		screen.renderMob(x, y, wizardAnim.getSprite());	

	}

}
