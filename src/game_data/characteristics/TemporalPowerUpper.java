package game_data.characteristics;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_engine.IUpdateStatesAndPowerUps;
import game_engine.UpdateStates;

/**
 *@author Lucia Martos
 */

@NameAnnotation(name = "Temporal Power Up")
public abstract class TemporalPowerUpper extends PowerUpper implements Characteristic{
		
	@ParameterAnnotation(parameters = {"Sprite"})
	public TemporalPowerUpper(Sprite aSprite){
		super(aSprite);
	}
	
	
	public abstract void reversePowerUp(Sprite playerSprite,IUpdateStatesAndPowerUps myInterface);
	
	public abstract void activatePowerUp(Sprite palyerSprite, IUpdateStatesAndPowerUps myInterface, Double timeElapsed);
	
	public void addToPowerUpMap(Sprite collidedSprite, double myTimeInEffect){
		boolean hasChanged = false;
		for(Characteristic characteristic: collidedSprite.getPowerUps().keySet()){
			if(characteristic instanceof SpeedPowerUpper){
				collidedSprite.getPowerUps().put(characteristic, myTimeInEffect);
				hasChanged = true;
			}
		}
		
		if(!hasChanged) {				
			collidedSprite.getPowerUps().put(this, myTimeInEffect);
		}

		collidedSprite.setPowerUps(collidedSprite.getPowerUps());
	}
	

}
