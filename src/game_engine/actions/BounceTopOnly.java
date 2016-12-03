package game_engine.actions;

import javafx.geometry.Side;
import game_data.Sprite;

/**
 * @author Alex & James
 *
 */

public class BounceTopOnly implements Action {
	
	private double myBounceSpeed;
	private Sprite myPlayerSprite;
	private Sprite myBouncer;
	private Side mySide;
	

	public BounceTopOnly(double bouncerSpeed, Sprite player, Side aSide, Sprite bouncer) {
		myBounceSpeed = bouncerSpeed;
		myPlayerSprite = player;
		mySide = aSide;
		myBouncer = bouncer;
	}

	@Override
	public void act() {
		//System.out.println("init y vel" + myPlayerSprite.getMyYVelocity());
		if(mySide==Side.TOP && pastPlatform()){
			myPlayerSprite.setMyYVelocity(-(myPlayerSprite.getMyYVelocity()+myBounceSpeed));
		}			
		//System.out.println(" y vel" + myPlayerSprite.getMyYVelocity());
		//myPlayerSprite.setMyVelocity( getNewVelocity() );		
		//myPlayerSprite.getMyLocation().setMyHeading( getNewHeading() );
		
	}
	
	private boolean pastPlatform(){
		return myPlayerSprite.getMyLocation().getYLocation()+myPlayerSprite.getMyHeight()>myBouncer
				.getMyLocation().getYLocation()+(myBouncer.getMyHeight()*.2) && myPlayerSprite.getMyYVelocity()>0;		
	}
	
	
}