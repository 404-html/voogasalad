package game_data.characteristics;
import game_engine.actions.*;

import java.util.Map;

import game_data.Sprite;
<<<<<<< HEAD
import game_data.sprites.Player;
import game_engine.actions.Damage;
=======
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
>>>>>>> a21ba578045e2d967a8654259a322cef7a204506
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
<<<<<<< HEAD
			if (collidedSprite instanceof Player){
				myAction = new Damage(-getHealthToGain(), collidedSprite);
				myAction.act();
			}		
		}

	}
	
=======
			//TODO need to make action
			//Action myAction = new Damage(mySprite, collidedSprite);
			//myAction.act();
		}		
	}

>>>>>>> a21ba578045e2d967a8654259a322cef7a204506
}
