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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 464381ae3a3a37a6a7890fd7dd4b4c2cde66bc83
	
		myPlayerSprite.setMyVelocity( getNewVelocity() );		
		myPlayerSprite.getMyLocation().setMyHeading( getNewHeading() );

	}
	
	private double getNewVelocity() {
		return myPlayerSprite.getMyVelocity() + myBounceSpeed;
	}
	
	private double getNewHeading() {
		
		double oldHeading = myPlayerSprite.getMyLocation().getMyHeading();
		
		if(mySide == Side.LEFT || mySide == Side.RIGHT) {
			return 180 - oldHeading;
<<<<<<< HEAD
=======
		//System.out.println("poop");
		//myPlayerSprite.setMyVelocity((myPlayerSprite.getMyVelocity()) + myBounceSpeed);
		//myPlayerSprite.getMyLocation().setMyHeading(myPlayerSprite.getMyLocation().getMyHeading()+Math.PI);
		myPlayerSprite.setMyYVelocity(0);
>>>>>>> a21ba578045e2d967a8654259a322cef7a204506
=======
>>>>>>> 464381ae3a3a37a6a7890fd7dd4b4c2cde66bc83
		}
		return 360 - oldHeading;
		
	}
	
	
}

