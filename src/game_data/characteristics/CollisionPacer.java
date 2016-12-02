package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import javafx.geometry.Side;

@CharacteristicAnnotation(name = "Collision Pacer")
public class CollisionPacer extends Pacer implements Characteristic{

	@ParameterAnnotation(parameters = {"Speed", "Sprite"})
	public CollisionPacer(int speed, Sprite aSprite) {
		super(speed, aSprite);
	}

	@Override
	public boolean toChangeDirection(Sprite aSprite) {
		//return isColliding();??? how are we gonna know if there's a collision
		return false;
	}
	
	@Override
	public Characteristic copy() {
		return new CollisionPacer(this.getSpeed(), this.getSprite());
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		// TODO Auto-generated method stub
		
	}

}
