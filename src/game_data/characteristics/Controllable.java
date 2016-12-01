package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import javafx.geometry.Side;

@CharacteristicAnnotation(name = "Controllable")
public class Controllable implements Characteristic {

	@ParameterAnnotation(parameters="Sprite")
	public Controllable(Sprite aSprite) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public Characteristic copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
