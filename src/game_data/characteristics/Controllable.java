package game_data.characteristics;

import java.util.Map;
import game_engine.Side;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
//import javafx.geometry.Side;

@NameAnnotation(name = "Controllable")
public class Controllable implements Characteristic {

	Sprite mySprite;
	@ParameterAnnotation(parameters="Sprite")
	public Controllable(Sprite aSprite) {
		mySprite = aSprite;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public Characteristic copy() {
		return new Controllable(mySprite);
	}

}
