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
		if(pastPlatform()){
			if(horizontal) {
				xLoc = myCollidedSprite.getMyLocation().getXLocation() + mySprite.getMyXVelocity()/60;
				yLoc = myCollidedSprite.getMyLocation().getYLocation();
			} else {
				xLoc = myCollidedSprite.getMyLocation().getXLocation();
				yLoc = myCollidedSprite.getMyLocation().getYLocation() + mySprite.getMyYVelocity()/60;
			}
			myCollidedSprite.getMyLocation().setLocation(xLoc, yLoc);
		}
		
	}
	
	private boolean pastPlatform(){
		return myCollidedSprite.getMyLocation().getYLocation()+myCollidedSprite.getMyHeight()<mySprite
				.getMyLocation().getYLocation()+(mySprite.getMyHeight()*.5) && myCollidedSprite.getMyYVelocity()>=0;
	}

}
