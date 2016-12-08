package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_engine.actions.Pace;
import javafx.geometry.Side;

/**
 * @author austingartside
 * alternative version of pacer where user does not to include bounds, but rather how far it can travel
 */
 @CharacteristicAnnotation(name = "Pacer Alternative")
public class PacerAlternative implements Characteristic{

	private static final String VERTICAL = "VERTICAL";
	private static final String HORIZONTAL = "HORIZONTAL";
	private static final double TIME_STEP = 60;
	
	private String myType;
	private double myDistance;
	private double originalXPosition;
	private double originalYPosition;
	private Sprite mySprite;
	private double myDistanceTraveled;
	
	@ParameterAnnotation(parameters = {"Type", "Distance", "Sprite"})
	public PacerAlternative(String type, double distance, Sprite associatedSprite){
		myType = type;
		myDistance = distance;
		mySprite = associatedSprite;
		originalXPosition = associatedSprite.getMyLocation().getXLocation();
		originalYPosition = associatedSprite.getMyLocation().getYLocation();
	}
	
	public PacerAlternative(Sprite associatedSprite){
		myType = "";
		myDistance = 0;
		mySprite = associatedSprite;
		originalXPosition = associatedSprite.getMyLocation().getXLocation();
		originalYPosition = associatedSprite.getMyLocation().getYLocation();
	}
	
	private boolean changeDirection(){
		double speed = Math.sqrt(Math.pow(mySprite.getMyXVelocity(),2) + Math.pow(mySprite.getMyYVelocity(),2));
		myDistanceTraveled += speed/TIME_STEP;
		if(myDistanceTraveled >= myDistance) {
			myDistanceTraveled = 0;
			return true;
		}
		return false;
	}
	
//	private boolean atYBound(){
//		double currentYLocation = mySprite.getMyLocation().getYLocation();
//		return currentYLocation>originalYPosition || currentYLocation<(originalYPosition-myDistance);			
//	}
//	
//	private boolean atXBound(){
//		//System.out.println("x is " + mySprite.getMyLocation().getXLocation());
//		//System.out.println("y is " + mySprite.getMyLocation().getYLocation());
//		//System.out.println();
//		double currentXLocation = mySprite.getMyLocation().getXLocation();
//		return currentXLocation<originalXPosition || currentXLocation>(originalXPosition+myDistance);			
//	}

	@Override
	public Characteristic copy() {
		return new PacerAlternative(myType, myDistance, mySprite.clone());
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		Pace pace=new Pace(mySprite, changeDirection(), myType);
		pace.act();
	}

}
