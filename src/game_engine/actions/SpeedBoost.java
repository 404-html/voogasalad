package game_engine.actions;

import game_data.Sprite;

public class SpeedBoost implements Action {
	
	private Sprite myPlayerSprite;

	public SpeedBoost(Sprite playerSprite){
		myPlayerSprite = playerSprite;
	}
	
	@Override
	public void act() {
		//Possible to change the physics for one specific sprite?
		//If so:
		//myPlayerSprite.setSpritePhysics(SpritePhysics speedBoostPhysics); 
		//or
		//myPlayerSprite.getSpritePhysics().setHorizontalGravity(.5);
		//myPlayerSprite.getSpritePhysics().setVerticalGravity(.5);
		
		
	}

}
