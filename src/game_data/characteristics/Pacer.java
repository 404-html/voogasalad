/*
 * Author: Alex and Austin
 * 
 * if toAct() is true run the following:
 * getSpeed()
 * 
 */

package game_data.characteristics;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableVariable;

@NameAnnotation(name = "Pacer")
public abstract class Pacer {
	
	private int mySpeed;
	private Sprite mySprite;
	
	@ParameterAnnotation(parameters = {"Speed", "Sprite"})
	public Pacer(int speed, Sprite aSprite) {
		mySpeed = speed;
		mySprite = aSprite;
	}
	
	public boolean toAct() {
		return true;
	}
	
	@ViewableVariable(description="Speed", type=int.class)
	public int getSpeed() {
		return mySpeed;
	}
	
	protected final Sprite getSprite(){
		return mySprite;
	}
	
	public abstract boolean toChangeDirection(Sprite aSprite);

}
