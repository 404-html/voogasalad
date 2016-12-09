package game_data.characteristics;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_engine.IUpdateStatesAndPowerUps;
import game_engine.UpdateStates;

/**
 *@author Lucia Martos
 */

@CharacteristicAnnotation(name = "Temporal Power Up")
public abstract class TemporalPowerUpper extends PowerUpper implements Characteristic, ICharactericticAction {
		
	@ParameterAnnotation(parameters = {"Sprite"})
	public TemporalPowerUpper(Sprite aSprite){
		super(aSprite);
	}
	
	
	public abstract void reversePowerUp(Sprite playerSprite,IUpdateStatesAndPowerUps myInterface);
	
	public abstract void activatePowerUp(Sprite palyerSprite, IUpdateStatesAndPowerUps myInterface, Double timeElapsed);
	
	public void addToPowerUpMap(Sprite collidedSprite, double myTimeInEffect){
		boolean hasChanged = false;
		for(Characteristic characteristic: collidedSprite.getMyPowerUps().keySet()){
			hasChanged = checkForSpecificTemporalPowerUpper(collidedSprite, myTimeInEffect, hasChanged, characteristic);
		}
		
		if(!hasChanged) {				
			collidedSprite.getMyPowerUps().put(this, myTimeInEffect);
		}

		collidedSprite.setMyPowerUps(collidedSprite.getMyPowerUps());
	}


	public abstract boolean checkForSpecificTemporalPowerUpper(Sprite collidedSprite, double myTimeInEffect, boolean hasChanged,
			Characteristic characteristic);
	
	public ICharactericticAction getInterface(){
		return this;
	}
	

}
