

package game_data.characteristics;

import java.util.HashMap;
import java.util.Map;
import game_engine.Side;

import game_data.Level;
import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import game_data.sprites.Player;
import game_engine.GameResources;
import game_engine.IUpdateStatesAndPowerUps;
import game_engine.UpdateStates;
import game_engine.actions.Action;
import game_engine.actions.Move;
import game_engine.actions.MoveLeft;
import game_engine.actions.MoveRight;
import game_engine.actions.SpeedBoost;
import javafx.scene.input.KeyCode;
//import javafx.geometry.Side;

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
				addToPowerUpMap(collidedSprite, myTimeInEffect);
				myAction = new SpeedBoost(collidedSprite, mySpeedBoost);
				System.out.println("characteristic in");
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
	public void activatePowerUp(Sprite playerSprite, IUpdateStatesAndPowerUps myInterface, Double timeElapsed) {
//		System.out.println("LUCIA");
//		System.out.println("power up " );
		playerSprite.getControllable().resetMyKeyPressedMap();
		
		Map<KeyCode, Action> newMap = new HashMap<KeyCode, Action>(playerSprite.getControllable().getMyKeyPressedMap());
		
		for(KeyCode key: newMap.keySet()){
			if(newMap.get(key) instanceof MoveRight){
				System.out.println("my vel right " + ((MoveRight) newMap.get(key)).getVelocity());
				((MoveRight) newMap.get(key)).setMyVelocity(mySpeedBoost +((MoveRight) newMap.get(key)).getVelocity());
				//((MoveRight) newMap.get(key)).setVelocity();
			}
		}
		playerSprite.getControllable().setMyKeyPressedMap(newMap);
//		myInterface.setKeyPressedMapWithBoosts();
	}

	@Override
	public boolean checkForSpecificTemporalPowerUpper(Sprite collidedSprite, double myTimeInEffect, boolean hasChanged,
			Characteristic characteristic) {
//		System.out.println("LUCIA");
			if(characteristic instanceof SpeedPowerUpper){
				collidedSprite.getPowerUps().put(characteristic, myTimeInEffect);
				hasChanged = true;
			}
			return hasChanged;
	}


}