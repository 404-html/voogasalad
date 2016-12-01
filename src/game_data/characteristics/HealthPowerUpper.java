package game_data.characteristics;
import game_engine.actions.*;

import java.util.Map;

import game_data.Sprite;
import game_data.sprites.Player;
import game_engine.actions.Damage;
import javafx.geometry.Side;

/**
 * @author austingartside
 *
 */
@CharacteristicAnnotation(name = "Health Power Up")
public class HealthPowerUpper extends PowerUpper implements Characteristic{

	private int myHealthToGain;
	private Action myAction;
	
	@ParameterAnnotation(parameters = {"Health Gained", "Sprite"})
	public HealthPowerUpper(int healthToGain, Sprite aSprite){
		super(aSprite);
		myHealthToGain = healthToGain;
	}
	
	public int getHealthToGain(){
		return myHealthToGain;
	}
	
	@Override
	public Characteristic copy() {
		return new HealthPowerUpper(myHealthToGain, mySprite);
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			if (collidedSprite instanceof Player){
				myAction = new Damage(-getHealthToGain(), collidedSprite);
				myAction.act();
			}		
		}

	}
	
}