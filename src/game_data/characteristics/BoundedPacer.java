package game_data.characteristics;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableVariable;

@NameAnnotation(name = "Bounded Pacer")
public abstract class BoundedPacer extends Pacer{

	private double myLowerBound;
	private double myUpperBound;
	
	@ParameterAnnotation(parameters = {"Speed", "Lower Bound", "Upper Bound", "Sprite"})
	public BoundedPacer(int speed, double lowerBound, double upperBound, Sprite aSprite) {
		super(speed, aSprite);
		myLowerBound = lowerBound;
		myUpperBound = upperBound;
	}
	
	@ViewableVariable(description="Lower Bound", type=double.class)
	public double getLowerBound(){
		return myLowerBound;
	}
	@ViewableVariable(description="Upper Bound", type=double.class)
	public double getUpperBound(){
		return myUpperBound;
	}

}
