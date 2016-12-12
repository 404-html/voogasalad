package game_data.characteristics;

import java.util.Map;
import game_engine.Side;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
//import javafx.geometry.Side;

/**
 * @author austingartside
 *
 */

@NameAnnotation(name = "Rotatable")
public class Rotatable implements Characteristic{

	private double mySpeed;
	private Sprite mySprite;
	
	@ParameterAnnotation(parameters={"Rotate Speed","Sprite"})
	public Rotatable(double speed, Sprite aSprite){
		mySpeed = speed;
		mySprite = aSprite;
	}
	
	@ViewableMethodOutput(description="Rotate Speed", type=double.class)
	public double getSpeed(){
		return mySpeed;
	}

	@Override
	public Characteristic copy(Sprite aSprite) {
		return new Rotatable(mySpeed, aSprite);
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {		
		//TODO: make and execute rotate action
		//myAction = new Bounce();
		//myAction.act();
	}

}
