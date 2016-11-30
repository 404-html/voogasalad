package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import javafx.geometry.Side;

@CharacteristicAnnotation(name = "Losable On Collision")
public class LosableOnCollision extends Losable implements Characteristic{

	private Sprite mySprite;
	
	public LosableOnCollision(Sprite aSprite){
		mySprite = aSprite;
	}
	
	@Override
	public Characteristic copy() {
		return new LosableOnCollision(mySprite.clone());
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		//TODO: make and execute action
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			//myAction = new Losable();
			//myAction.act();
		}
		
	}
	

}
