package game_engine.actions;

import game_data.Level;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.characteristics.SpeedPowerUpper;
import game_engine.GameResources;

public class SpeedBoost implements Action {

	private Sprite myPlayerSprite;
	private double timeInEffect;
	private double speedBoost;
	private double prevSpeed;

	public SpeedBoost(Sprite playerSprite, double speedBoost) {
		myPlayerSprite = playerSprite;
		this.speedBoost = speedBoost;
		prevSpeed = 0;
	}
	
	public double getPrevSpeed(){
		return prevSpeed;
	}

	@Override
	public void act() {
		// Possible to change the physics for one specific sprite?
		// If so:
		// myPlayerSprite.setSpritePhysics(SpritePhysics speedBoostPhysics);
		// or
		// myPlayerSprite.getSpritePhysics().setHorizontalGravity(.5);
		// myPlayerSprite.getSpritePhysics().setVerticalGravity(.5);

		myPlayerSprite.setTerminalXVel(GameResources.TERMINAL_X_VELOCITY.getDoubleResource()+speedBoost);		
		myPlayerSprite.setTerminalYVel(GameResources.TERMINAL_X_VELOCITY.getDoubleResource()+speedBoost);

//		System.out.println("SIZE int" +myLevel.getMyPowerUps().size() );
//		System.out.println("SIZE end" +myLevel.getMyPowerUps().size() );
		prevSpeed = myPlayerSprite.getXVelocity();
		myPlayerSprite.setXVelocity(myPlayerSprite.getXVelocity()*speedBoost);
	}
	
	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new SpeedBoost(aSprite, this.speedBoost);
	}

}
