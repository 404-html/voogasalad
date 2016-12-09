

package game_data.characteristics;

import java.util.Map;

import game_data.Level;
import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import game_data.sprites.Player;
import game_engine.IUpdateStatesAndPowerUps;
import game_engine.UpdateStates;
import game_engine.actions.Action;
import game_engine.actions.SpeedBoost;
import javafx.geometry.Side;

/**
 * @author austingartside
 *
 */


@NameAnnotation(name = "Speed Power Up")
public class SpeedPowerUpper extends TemporalPowerUpper implements Characteristic{
	
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
	
	@ViewableMethodOutput(description="Speed Boost", type=double.class)
	public double getSpeedBoost(){
		return mySpeedBoost;
	}

	@ViewableMethodOutput(description="Time In Effect", type=double.class)
	public double getTimeInEffect(){
		return myTimeInEffect;
	}
	
	@Override
	public Characteristic copy() {
		return new SpeedPowerUpper(mySpeedBoost, myTimeInEffect, this.getSprite());
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {		
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			//unless we want non players to be able to speed up upon hitting a powerup
			if(collidedSprite instanceof Player){
				addToPowerUpMap(collidedSprite,myTimeInEffect);
				myAction = new SpeedBoost(collidedSprite, mySpeedBoost);
//				System.out.println("characteristic in");
				myAction.act();
			}
		}
	}

	@Override
	public void reversePowerUp(Sprite playerSprite, IUpdateStatesAndPowerUps myInterface) {
		playerSprite.resetTerminalVelocities();
		myInterface.generateDefaultKeyPressedMap();
		
	}

	@Override
	public void activatePowerUp(Sprite palyerSprite, IUpdateStatesAndPowerUps myInterface, Double timeElapsed) {
//		System.out.println("LUCIA");
		myInterface.setKeyPressedMapWithBoosts();
	}


}