package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Player;
import game_engine.actions.Action;
import game_engine.actions.Bounce;
import game_engine.actions.BounceTopOnly;
import javafx.geometry.Side;

/**
 * @author Alex & James
 *
 */

@NameAnnotation(name = "BouncerTop")
public class BouncerTop implements Characteristic {
	private double myBounceSpeed;
	private Sprite mySprite;
	private Action myAction;

	@ParameterAnnotation(parameters = { "Bounce Speed", "Sprite" })
	public BouncerTop(double bounceSpeed, Sprite aSprite) {
		myBounceSpeed = bounceSpeed;
		mySprite = aSprite;
	}

	public double getBounceSpeed() {
		return myBounceSpeed;
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		for (Sprite collidedSprite : myCollisionMap.keySet()) {
			// is going to need to have arguments after implemented
			if (collidedSprite instanceof Player) {
				myAction = new BounceTopOnly(myBounceSpeed, collidedSprite, myCollisionMap.get(collidedSprite), mySprite);
				myAction.act();
			}
		}
	}

	@Override
	public Characteristic copy() {
		return new Bouncer(myBounceSpeed, myBounceSpeed, mySprite);
	}

}