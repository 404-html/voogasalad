package game_data.characteristics;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;

/**
 * @author austingartside
 *
 */

@CharacteristicAnnotation(name = "Power Up")
public abstract class PowerUpper implements Characteristic{
	
	protected Sprite mySprite;
	
	@ParameterAnnotation(parameters = {"Sprite"})
	public PowerUpper(Sprite aSprite){
		mySprite = aSprite;
	}
	
	public Sprite getSprite(){
		return mySprite;
	}
	
	public void addToPowerUpMap(Sprite collidedSprite, double myTimeInEffect){
		boolean hasChanged = false;
		for(Characteristic characteristic: collidedSprite.getMyPowerUps().keySet()){
			if(characteristic instanceof SpeedPowerUpper){
				collidedSprite.getMyPowerUps().put(characteristic, myTimeInEffect);
				hasChanged = true;
			}
		}
		
		if(!hasChanged) {				
			collidedSprite.getMyPowerUps().put(this, myTimeInEffect);
		}

		collidedSprite.setMyPowerUps(collidedSprite.getMyPowerUps());
	}
	

}
