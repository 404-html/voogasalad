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
		System.out.println("init y vel" + myPlayerSprite.getMyYVelocity());
		if(mySide==Side.BOTTOM || mySide==Side.TOP){
			myPlayerSprite.setMyYVelocity(-(myPlayerSprite.getMyYVelocity()+myBounceSpeed));
		}			
		if(mySide==Side.LEFT || mySide==Side.RIGHT){
			myPlayerSprite.setMyXVelocity(-(myPlayerSprite.getMyXVelocity()+myBounceSpeed));
		}
		System.out.println(" y vel" + myPlayerSprite.getMyYVelocity());
		//myPlayerSprite.setMyVelocity( getNewVelocity() );		
		//myPlayerSprite.getMyLocation().setMyHeading( getNewHeading() );
		
	}
	
	
}

