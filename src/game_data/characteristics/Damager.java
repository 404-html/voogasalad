package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import game_engine.actions.Action;
import game_engine.actions.Break;
import game_engine.actions.Damage;
import javafx.geometry.Side;

/**
 * @author austingartside
 *
 */

@NameAnnotation(name = "Damager")
public class Damager implements Characteristic{
	
	private int myDamageToGive;
	private Sprite mySprite;
	
	@ParameterAnnotation(parameters = {"Amount of Damage", "Sprite"})
	public Damager(int damageToGive, Sprite aSprite){
		myDamageToGive = damageToGive;
		mySprite = aSprite;
	}
	
	@ViewableMethodOutput(description="Damage", type=int.class)
	public int getDamage(){
		return myDamageToGive;
	}

	@Override
	public Characteristic copy() {
		return new Damager(myDamageToGive, mySprite);
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			Action myAction = new Damage(myDamageToGive, collidedSprite);
			myAction.act();
		}
		
	}

}
