package game_engine.actions;

import game_data.Sprite;

public class StopRightMovement extends StopMovement {

	public StopRightMovement(Sprite aSprite, double aVelocity) {
		super(aSprite, aVelocity);
	}

	@Override
	public void act() {
		mySprite.setMyXVelocity(0);
		//mySprite.setMyXVelocity(mySprite.getMyXVelocity()-myVelocity);
	}

}
