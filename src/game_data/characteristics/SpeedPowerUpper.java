

package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Player;
import game_engine.actions.Action;
import game_engine.actions.SpeedBoost;
import javafx.geometry.Side;

/**
 * @author austingartside
 *
 */

@CharacteristicAnnotation(name = "Speed Power Up")
public class SpeedPowerUpper extends PowerUpper implements Characteristic{
	
	private double mySpeedBoost;
	private double myTimeInEffect;
	private double myCurrentTime;
	private Action myAction;
	
	@ParameterAnnotation(parameters = {"Speed Boost", "Time In Effect", "Sprite"})
	public SpeedPowerUpper(double speedBoost, double timeInEffect, Sprite aSprite){
		super(aSprite);
		mySpeedBoost = speedBoost;
		myTimeInEffect = timeInEffect;
		myCurrentTime = 0;
	}
	
	public double getSpeedBoost(){
		return mySpeedBoost;
	}

	@Override
	public Characteristic copy() {
		return new SpeedPowerUpper(mySpeedBoost, myTimeInEffect, this.getSprite());
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		//TODO: make and execute speed Up action
		
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			//unless we want non players to be able to speed up upon hitting a powerup
			if(collidedSprite instanceof Player){
				myAction = new SpeedBoost(collidedSprite);
				myAction.act();
			}
		}
	}

}