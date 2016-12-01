package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_engine.actions.Action;
import game_engine.actions.Bounce;
import javafx.geometry.Side;

/**
 * @author Alex & James
 *
 */

@CharacteristicAnnotation(name = "Bouncer")
public class Bouncer implements Characteristic{
	private double myBounceSpeed;
	private Sprite mySprite;
	private Action myAction;
	
	@ParameterAnnotation(parameters = {"Bounce Speed", "Sprite"})
	public Bouncer(double bounceSpeed, Sprite mySprite){
		myBounceSpeed = bounceSpeed;
	}
	
	public double getBounceSpeed(){
		return myBounceSpeed;
	}
	
	
	@Override
	public void execute(Map<Sprite, Side> myCollisionMap){
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			//is going to need to have arguments after implemented
			myAction = new Bounce(myBounceSpeed, collidedSprite, myCollisionMap.get(collidedSprite));
			myAction.act();
		}
	}

	@Override
	public Characteristic copy() {
		return new Bouncer(myBounceSpeed, mySprite);
	}

}
