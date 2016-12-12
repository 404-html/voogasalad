package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Player;
import game_engine.Side;
import game_engine.actions.Action;
import game_engine.actions.Teleport;

/**
 * @author ALEXXXXXXXXX
 *
 */


@NameAnnotation(name = "Teleporter")
public class Teleporter implements Characteristic {
	
	private Sprite mySprite;
	private Action myAction;
	private double myXLocation, myYLocation;
	
	@ParameterAnnotation(parameters = { "X Location", "Y Location", "Sprite" })
	public Teleporter(double xLoc, double yLoc, Sprite aSprite) {
		myXLocation = xLoc;
		myYLocation = yLoc;
		mySprite = aSprite;
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		for (Sprite collidedSprite : myCollisionMap.keySet()) {
			if (collidedSprite instanceof Player) {
				myAction = new Teleport(collidedSprite, myXLocation, myYLocation);
				myAction.act();
			}			
		}
	}

	@Override
	public Characteristic copy(Sprite aSprite) {
		return new Teleporter(myXLocation, myYLocation, aSprite);
	}

}
