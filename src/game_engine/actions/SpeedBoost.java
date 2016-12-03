package game_engine.actions;

import game_data.Sprite;
import game_engine.GameResources;

public class SpeedBoost implements Action {
	
	private Sprite myPlayerSprite;
	private double timeInEffect;
	private double speedBoost;

	public SpeedBoost(Sprite playerSprite, double speedBoost, double timeInEffect){
		myPlayerSprite = playerSprite;
		this.timeInEffect = timeInEffect;
		this.speedBoost = speedBoost;
	}
	
	@Override
	public void act() {
		//Possible to change the physics for one specific sprite?
		//If so:
		//myPlayerSprite.setSpritePhysics(SpritePhysics speedBoostPhysics); 
		//or
		//myPlayerSprite.getSpritePhysics().setHorizontalGravity(.5);
		//myPlayerSprite.getSpritePhysics().setVerticalGravity(.5);
		double lastTimeHit= 0;
		while(true){
		if(System.currentTimeMillis() > lastTimeHit+timeInEffect){
		    lastTimeHit= System.currentTimeMillis();
			myPlayerSprite.setMyXVelocity(myPlayerSprite.getMyXVelocity() + speedBoost);
		}	
		
		
		}
	}

}
