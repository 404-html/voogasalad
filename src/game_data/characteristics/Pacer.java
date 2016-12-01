/*
 * Author: Alex and Austin
 * 
 * if toAct() is true run the following:
 * getSpeed()
 * 
 */

package game_data.characteristics;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;

@CharacteristicAnnotation(name = "Pacer")
public abstract class Pacer {
	
	private int mySpeed;
	
	@ParameterAnnotation(parameters = {"Speed"})
	public Pacer(int speed) {
		mySpeed = speed;
	}
	
	public boolean toAct() {
		return true;
	}
	
	public int getSpeed() {
		return mySpeed;
	}
	
	public abstract boolean toChangeDirection(Sprite aSprite);

}
