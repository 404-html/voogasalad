package game_engine.actions;

import game_data.Sprite;

public class StopUpMovement extends StopMovement {

	public StopUpMovement(Sprite aSprite, double aVelocity) {
		super(aSprite, aVelocity);
	}

	@Override
	public void act() {
		mySprite.setYVelocity(0);
		//mySprite.setYVelocity(mySprite.getYVelocity()+myVelocity);
	}

	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return copyWithNewSprite(aSprite);
	}

}
