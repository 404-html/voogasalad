package game_engine.actions;

import javafx.geometry.Side;
import game_data.Sprite;

/**
 * @author Alex
 *
 */

public class Hit implements Action {
	
	private Sprite myPlayerSprite;
	private Side mySide;
	

	public Hit(Sprite player, Side aSide) {
		myPlayerSprite = player;
		mySide = aSide;
	}

	@Override
	public void act() {
		
		double oldHeading = myPlayerSprite.getMyLocation().getMyHeading();
		double oldVelocity = myPlayerSprite.getMyVelocity();
		
		//get new Velocity –– gets horizontal or vertical components to zero
		double newVelocity = getNewVelocity(oldHeading, oldVelocity);
		
		//based on the side its hitting and oldHeading
		double newHeading = getNewHeading(oldHeading, oldVelocity);
		
		myPlayerSprite.getMyLocation().setMyHeading(newHeading);
		myPlayerSprite.setMyVelocity(newVelocity);

	}
	
	private double getNewVelocity(double oldHeading, double oldVelocity) {
		
		double oldHeadingRadians = oldHeading*Math.PI/180; //change to radians for trig –– so happy I caught this
		
		if(mySide == Side.LEFT || mySide == Side.RIGHT) {
			return Math.abs( Math.sin(oldHeadingRadians) * oldVelocity );
		}
		return Math.abs( Math.cos(oldHeadingRadians) * oldVelocity );
		
	}
	
	private double getNewHeading(double oldHeading, double oldVelocity) {
		
		if(mySide == Side.LEFT || mySide == Side.RIGHT) {
			
			if(oldHeading > 0 && oldHeading < 180) {
				return 90;
			}
			return 270;
			
		}
		
		if(oldHeading > 270 || oldHeading < 90) {
			return  0;
		}
		return 180;

	}
		
}