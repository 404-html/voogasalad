

package game_data.characteristics;

import java.util.HashMap;
import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import game_data.sprites.Player;
import game_engine.IUpdateStatesAndPowerUps;
import game_engine.Side;
import game_engine.actions.Action;
import game_engine.actions.Move;
import game_engine.actions.SpeedBoost;
import javafx.scene.input.KeyCode;
//import javafx.geometry.Side;

/**
 * @author austingartside, Luth, alex!!
 *
 */


@NameAnnotation(name = "Speed Power Up")
public class SpeedPowerUpper extends TemporalPowerUpper implements Characteristic{
	
	private double mySpeedBoost;
	private double myTimeInEffect;
	private Action myAction;
	private double myCurrentTime;
	private Map<KeyCode, Double> defaultKeyPressedMap;
	
	@ParameterAnnotation(parameters = {"Speed Boost", "Time In Effect", "Sprite"})
	public SpeedPowerUpper(double speedBoost, double timeInEffect, Sprite aSprite){
		super(aSprite);
		mySpeedBoost = speedBoost;
		myTimeInEffect = timeInEffect;
		defaultKeyPressedMap = null;
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
			if(collidedSprite instanceof Player){
				addToPowerUpMap(collidedSprite, myTimeInEffect);
				if(defaultKeyPressedMap == null) {
					defaultKeyPressedMap = new HashMap<KeyCode, Double>();
					for(KeyCode key:collidedSprite.getControllable().getMyKeyPressedMap().keySet()){
						defaultKeyPressedMap.put(key, ((Move) collidedSprite.getControllable().getMyKeyPressedMap().get(key)).getVelocity());
					}
				}
				myAction = new SpeedBoost(collidedSprite, mySpeedBoost);
				myAction.act();
			}
			
		}
	}

	@Override
	public void reversePowerUp(Sprite playerSprite, IUpdateStatesAndPowerUps myInterface) {
		playerSprite.resetTerminalVelocities();
		for(KeyCode key:  playerSprite.getControllable().getMyKeyPressedMap().keySet()){
			( (Move) playerSprite.getControllable().getMyKeyPressedMap().get(key)).setMyVelocity(defaultKeyPressedMap.get(key));
		}
//		playerSprite.getControllable().setMyKeyPressedMap(defaultKeyPressedMap);

//		myInterface.generateDefaultKeyPressedMap();
		
	}

	
	@Override
	public void activatePowerUp(Sprite playerSprite, IUpdateStatesAndPowerUps myInterface, Double timeElapsed) {
//		System.out.println("LUCIA");
//		System.out.println("power up " );
//		playerSprite.getControllable().resetMyKeyPressedMap();
		
//		playerSprite.getControllable().setMyKeyPressedMap(defaultKeyPressedMap);
		for(KeyCode key:  playerSprite.getControllable().getMyKeyPressedMap().keySet()){
			( (Move) playerSprite.getControllable().getMyKeyPressedMap().get(key)).setMyVelocity(defaultKeyPressedMap.get(key));
		}
		
		for(KeyCode key: playerSprite.getControllable().getMyKeyPressedMap().keySet()){
			if(playerSprite.getControllable().getMyKeyPressedMap().get(key) instanceof Move){
				((Move) playerSprite.getControllable().getMyKeyPressedMap().get(key)).setMyVelocity(mySpeedBoost +((Move) playerSprite.getControllable().getMyKeyPressedMap().get(key)).getVelocity());
				//((MoveRight) newMap.get(key)).setVelocity();
			}
		}
		
//		playerSprite.getControllable().setMyKeyPressedMap(newMap);
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