package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Player;
import javafx.geometry.Side;

/**
 * @author austingartside
 *
 */

@CharacteristicAnnotation(name = "Projectile Power Up")
public class ProjectilePowerUpper extends PowerUpper implements Characteristic{
	
	@ParameterAnnotation(parameters = {"Sprite"})
	public ProjectilePowerUpper(Sprite aSprite){
		super(aSprite);
	}
	
	@Override
	public Characteristic copy() {
		return new ProjectilePowerUpper(this.getSprite().clone());
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		//TODO: make and execute action
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			if(collidedSprite instanceof Player){
				//myAction = new ProjectilePowerUp();
				//myAction.act();
			}
		}
	}

}
