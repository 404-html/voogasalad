package game_engine.actions;

import game_data.Sprite;
import game_data.states.Physics;
import game_data.states.State;
import game_engine.SpritePhysics;

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
				xLoc = myCollidedSprite.getLocation().getXLocation() + mySprite.getXVelocity()/60;
				yLoc = myCollidedSprite.getLocation().getYLocation();
			} else {
				xLoc = myCollidedSprite.getLocation().getXLocation();
				yLoc = myCollidedSprite.getLocation().getYLocation() + mySprite.getYVelocity()/60;
			}
			myCollidedSprite.getLocation().setLocation(xLoc, yLoc);
		}
		
	}
	
	private boolean pastPlatform(){
		return myCollidedSprite.getLocation().getYLocation()+myCollidedSprite.getHeight()<mySprite
				.getLocation().getYLocation()+(mySprite.getHeight()*.5) && myCollidedSprite.getYVelocity()>=0;
	}

}
