package com.zachdev.game.entity.mob;

import com.zachdev.game.graphics.AnimatedSprite;
import com.zachdev.game.graphics.SpriteSheet;

public class BlueWizard extends Wizard {
	
	public BlueWizard(int x, int y) {
		
		super(x, y);
		
		wizardAnim = new AnimatedSprite(SpriteSheet.blueWizardSheet, 16, 16, 4);
		
		wizardAnim.setFrameRate(200);
		
		
	}

}
