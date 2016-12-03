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
		//if(pastPlatform()){
			if(horizontal) {
				xLoc = myCollidedSprite.getMyLocation().getXLocation() + mySprite.getMyXVelocity()/60;
				yLoc = myCollidedSprite.getMyLocation().getYLocation() - 4.9;
			} else {
				xLoc = myCollidedSprite.getMyLocation().getXLocation();
				yLoc = myCollidedSprite.getMyLocation().getYLocation() + mySprite.getMyYVelocity()/60 - 4.9;
			}
			myCollidedSprite.getMyLocation().setLocation(xLoc, yLoc);
		//}
		
	}
	
	private boolean pastPlatform(){
//		return myPlayerSprite.getMyLocation().getYLocation()+myPlayerSprite.getMyHeight()>myNonPlayerSprite
//				.getMyLocation().getYLocation()+collisionBuffer && myPlayerSprite.getMyYVelocity()>0;
		return myCollidedSprite.getMyLocation().getYLocation()+myCollidedSprite.getMyHeight()<mySprite
				.getMyLocation().getYLocation()+(mySprite.getMyHeight()*.5) && myCollidedSprite.getMyYVelocity()>0;
	}

}
