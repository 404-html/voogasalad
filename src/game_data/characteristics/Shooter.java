package game_data.characteristics;

import java.util.Map;
import game_engine.Side;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Player;
import game_engine.actions.Action;
import game_engine.actions.Bounce;
import game_engine.actions.Shoot;
//import javafx.geometry.Side;

@NameAnnotation(name = "Shooter")
public class Shooter implements Characteristic {
	private Sprite mySprite;
	private Action myAction;

	@ParameterAnnotation(parameters = { "Sprite" })
	public Shooter(Sprite mySprite) {
		this.mySprite = mySprite;
	}
	
	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
			if (mySprite instanceof Player) {
				myAction = new Shoot(mySprite);
				myAction.act();
			}
	
	}

	@Override
	public Characteristic copy() {
		return new Shooter(mySprite);
	}

}
