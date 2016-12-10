package game_engine.actions;

import game_data.Sprite;

public class StopUpMovement extends StopMovement {

	public StopUpMovement(Sprite aSprite, double aVelocity) {
		super(aSprite, aVelocity);
	}

	@Override
	public void act() {
		mySprite.setYVelocity(0);
		//mySprite.setMyYVelocity(mySprite.getMyYVelocity()+myVelocity);
	}

}
