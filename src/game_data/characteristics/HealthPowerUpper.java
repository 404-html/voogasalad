package game_data.characteristics;
import game_engine.Side;
import game_engine.actions.*;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import game_data.sprites.Player;
import game_engine.actions.Damage;
//import javafx.geometry.Side;

/**
 * @author austingartside
 *
 */
@NameAnnotation(name = "Health Power Up")
public class HealthPowerUpper extends PowerUpper implements Characteristic{

	private int myHealthToGain;
	private Action myAction;
	
	@ParameterAnnotation(parameters = {"Health to Gain", "Sprite"})
	public HealthPowerUpper(int healthToGain, Sprite aSprite){
		super(aSprite);
		myHealthToGain = healthToGain;
	}
	
	@ViewableMethodOutput(type=int.class, description="Health to Gain")
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
				myAction = new AddHealth(-getHealthToGain(), collidedSprite);
				myAction.act();
			}		
		}

	}


	}

