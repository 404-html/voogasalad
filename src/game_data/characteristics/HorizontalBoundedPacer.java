package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import javafx.geometry.Side;

@CharacteristicAnnotation(name = "Horizontal Bounded Pacer")
public class HorizontalBoundedPacer extends BoundedPacer implements Characteristic{

	@ParameterAnnotation(parameters = {"Speed", "Lower Bound", "Upper Bound"})
	public HorizontalBoundedPacer(int speed, double lowerBound, double upperBound) {
		super(speed, lowerBound, upperBound);
	}

	@Override
	public boolean toChangeDirection(Sprite aSprite) {
		double xLocation = aSprite.getMyLocation().getXLocation();
		return xLocation<=this.getLowerBound() || xLocation>=this.getUpperBound();
	}
	
	@Override
	public Characteristic copy() {
		return new HorizontalBoundedPacer(this.getSpeed(), this.getLowerBound(), this.getUpperBound());
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		// TODO Auto-generated method stub
		
	}

}
