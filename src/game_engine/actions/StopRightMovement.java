package game_engine.actions;

import game_data.Sprite;

public class StopRightMovement extends StopMovement {

	public StopRightMovement(Sprite aSprite, double aVelocity) {
		super(aSprite, aVelocity);
	}

	@Override
	public void act() {
		if(mySprite.getXVelocity()>0){
			mySprite.setXVelocity(mySprite.getXVelocity()-myVelocity);
		}
		//System.out.println("stopping right movement");
		//mySprite.setXVelocity(mySprite.getXVelocity()-myVelocity);
	}

}
