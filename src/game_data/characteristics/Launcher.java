/*
 * Author: Alex
 * 
 * if toAct() is true run the following:
 * getLaunched()
 * 
 */

package game_data.characteristics;

import java.util.Map;

import game_data.*;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import javafx.geometry.Side;
//are we going to need a separate launcher class that reacts to key commands?
//big question is how are we going to handle launching projectiles when it's not on a uniform time
@CharacteristicAnnotation(name = "Launcher")
public class Launcher implements Characteristic{
	
	private Sprite mySprite;
	private int myTimeInterval;
	private int myCurrentTime;
	
	@ParameterAnnotation(parameters = {"Sprite", "Time Interval"})
	public Launcher(Sprite sprite, int timeInterval) {
		mySprite = sprite;
		myTimeInterval = timeInterval;
		myCurrentTime = 0;
	}
	
	public Sprite getLaunched() {
		return mySprite;	
	}


	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		myCurrentTime++;
		myCurrentTime %= myTimeInterval;
		if(myCurrentTime == 0){
			//TODO need to make action
			//Action myAction = new Launch(mySprite, collidedSprite);
			//myAction.act();
		}
	}

	@Override
	public Characteristic copy() {
		return new Launcher(mySprite, myTimeInterval);
	}
	

}
