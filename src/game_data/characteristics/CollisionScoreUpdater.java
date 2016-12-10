package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
//import javafx.geometry.Side;
import game_engine.Side;

/**
 * @author austingartside
 *
 * might have two different types for this, one type that just disappears when you hit it
 * other could be a thing that changes appearance and shoots something out to indicate score change (like blocks with coin
 * in Mario)
 */

@NameAnnotation(name="Collision Score Updater")
public class CollisionScoreUpdater implements Characteristic{

	private Sprite mySprite;
	
	@ParameterAnnotation(parameters="Sprite")
	public CollisionScoreUpdater(Sprite aSprite){
		mySprite = aSprite;
	}
	
	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		//TODO: create and execute action for updating score on collision
	}

	@Override
	public Characteristic copy() {
		return new CollisionScoreUpdater(mySprite.clone());
	}
	

}
