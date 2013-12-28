package com.zachdev.game.entity.mob;

import com.zachdev.game.graphics.AnimatedSprite;
import com.zachdev.game.graphics.Screen;
import com.zachdev.game.graphics.SpriteSheet;

public class OldMan extends Mob {

	private AnimatedSprite oldManUp = new AnimatedSprite(SpriteSheet.oldManUp,
			16, 16, 2);
	private AnimatedSprite oldManDown = new AnimatedSprite(
			SpriteSheet.oldManDown, 16, 16, 2);
	private AnimatedSprite oldManLeft = new AnimatedSprite(
			SpriteSheet.oldManLeft, 16, 16, 2);
	private AnimatedSprite oldManRight = new AnimatedSprite(
			SpriteSheet.oldManRight, 16, 16, 2);

	protected AnimatedSprite animatedSprite;

	public OldMan(int x, int y) {

		this.x = x;
		this.y = y;
		
		animatedSprite = oldManDown;
		
		animatedSprite.setFrameRate(50);
	}

	@Override
	public void tick() {
		animatedSprite.tick();
	}

	@Override
	public void render(Screen screen) {
		screen.renderMob(x, y, animatedSprite.getSprite());	

	}

}
