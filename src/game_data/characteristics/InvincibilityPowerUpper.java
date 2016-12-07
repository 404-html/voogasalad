

package game_data.characteristics;

import java.util.Map;

import game_data.Level;
import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Player;
import game_engine.actions.Action;
import game_engine.actions.Invincibility;
import game_engine.actions.SpeedBoost;
import javafx.geometry.Side;

/**
 * @author austingartside
 *
 */

@CharacteristicAnnotation(name = "Invincibility Power Up")
public class InvincibilityPowerUpper extends PowerUpper implements Characteristic{
	
	private double myTimeInEffect;
	private Action myAction;
	
	@ParameterAnnotation(parameters = {"Time In Effect", "Sprite"})
	public InvincibilityPowerUpper(double timeInEffect, Sprite aSprite){
		super(aSprite);
		myTimeInEffect = timeInEffect;
	}

	@Override
	public Characteristic copy() {
		return new InvincibilityPowerUpper( myTimeInEffect, this.getSprite());
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		//TODO: make and execute speed Up action
		
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			//unless we want non players to be able to speed up upon hitting a powerup
			if(collidedSprite instanceof Player){
				addToPowerUpMap(collidedSprite,myTimeInEffect);
				myAction = new Invincibility(collidedSprite);
//				System.out.println("characteristic in");
				myAction.act();
			}
		}
	}

}