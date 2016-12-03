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
=======
		//System.out.println("init y vel" + myPlayerSprite.getMyYVelocity());
		if(mySide==Side.BOTTOM || mySide==Side.TOP){
			myPlayerSprite.setMyYVelocity(-(myPlayerSprite.getMyYVelocity()+myBounceSpeed));
		}			
		if(mySide==Side.LEFT || mySide==Side.RIGHT){
			myPlayerSprite.setMyXVelocity(-(myPlayerSprite.getMyXVelocity()+myBounceSpeed));
>>>>>>> 51b30df9f8e041f06f7ccb31dca3c6c7205450ae
		}
		//System.out.println(" y vel" + myPlayerSprite.getMyYVelocity());
		//myPlayerSprite.setMyVelocity( getNewVelocity() );		
		//myPlayerSprite.getMyLocation().setMyHeading( getNewHeading() );
		
	}
	
	
}

