package game_engine.actions;

import game_data.Sprite;

/*
 * Author: alex
 */

public class Stick implements Action{

	private Sprite myCollidedSprite;
	private Sprite mySprite;
	private boolean horizontal;
	
	public Stick(Sprite collidedSprite, Sprite origSprite, boolean hori){
		myCollidedSprite = collidedSprite;
		mySprite = origSprite;
		horizontal = hori;
	}
	
	@Override
	public void act() {
		
		double xLoc, yLoc;
		
		if(horizontal) {
			xLoc = myCollidedSprite.getMyLocation().getXLocation() + mySprite.getMyXVelocity()/60;
			yLoc = myCollidedSprite.getMyLocation().getYLocation() - 4.9;
		} else {
			xLoc = myCollidedSprite.getMyLocation().getXLocation();
			yLoc = myCollidedSprite.getMyLocation().getYLocation() + mySprite.getMyYVelocity()/60 - 4.9;
		}
		myCollidedSprite.getMyLocation().setLocation(xLoc, yLoc);
		
	}

}
