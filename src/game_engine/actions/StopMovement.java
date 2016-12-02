package game_engine.actions;

import game_data.Sprite;

public abstract class StopMovement implements Action {
	public Sprite mySprite;
	public double myVelocity;
	public StopMovement(Sprite aSprite, double aVelocity) {
		mySprite=aSprite;
		myVelocity=aVelocity;
	}

	@Override
	public abstract void act();

}
