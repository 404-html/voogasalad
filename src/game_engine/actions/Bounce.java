package game_engine.actions;

import javafx.geometry.Side;
import game_data.Sprite;

/**
 * @author Alex & James
 *
 */

public class Bounce implements Action {
	
	private double myBounceSpeed;
	private Sprite myPlayerSprite;
	private Side mySide;
	

	public Bounce(double bouncerSpeed, Sprite player, Side aSide) {
		myBounceSpeed = bouncerSpeed;
		myPlayerSprite = player;
		mySide = aSide;
	}

	@Override
	public void act() {
	
		//myPlayerSprite.setMyVelocity( getNewVelocity() );		
		myPlayerSprite.getMyLocation().setMyHeading( getNewHeading() );

	}
	
/*	private double getNewVelocity() {
		return myPlayerSprite.getMyVelocity() + myBounceSpeed;
	}*/
	
	private double getNewHeading() {
		
		double oldHeading = myPlayerSprite.getMyLocation().getMyHeading();
		
		if(mySide == Side.LEFT || mySide == Side.RIGHT) {
			return 180 - oldHeading;
		}
		return 360 - oldHeading;
		
	}
	
	
}

