package game_data.characteristics;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;

@CharacteristicAnnotation(name = "Bounded Pacer")
public abstract class BoundedPacer extends Pacer{

	private double myLowerBound;
	private double myUpperBound;
	
	@ParameterAnnotation(parameters = {"Speed", "Lower Bound", "Upper Bound", "Sprite"})
	public BoundedPacer(int speed, double lowerBound, double upperBound, Sprite aSprite) {
		super(speed, aSprite);
		myLowerBound = lowerBound;
		myUpperBound = upperBound;
	}
	
	public double getLowerBound(){
		return myLowerBound;
	}
	
	public double getUpperBound(){
		return myUpperBound;
	}

}
