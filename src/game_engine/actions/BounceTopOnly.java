package game_engine.actions;

import javafx.geometry.Side;
import game_data.Sprite;

/**
 * @author Austin
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
		//System.out.println(mySide == Side.TOP);
		//System.out.println(pastPlatform());
		//System.out.println("poop");
		if(mySide==Side.TOP && pastPlatform()){
			//myPlayerSprite.setMyYVelocity(-(myPlayerSprite.getMyYVelocity()+myBounceSpeed));
			//System.out.println("asdasdasdasdasd");
			//System.out.println("player is at " + myPlayerSprite.getMyLocation().getYLocation());
			//System.out.println("bouncer is at " + myBouncer.getMyLocation().getYLocation());
			myPlayerSprite.setYVelocity(-1*myBounceSpeed);
			//myPlayerSprite.getMyLocation().setLocation(myPlayerSprite.getMyLocation().getXLocation(), 
			//		myBouncer.getMyLocation().getYLocation());
		}	
		//System.out.println(" y vel" + myPlayerSprite.getMyYVelocity());
		//myPlayerSprite.setMyVelocity( getNewVelocity() );		
		//myPlayerSprite.getMyLocation().setMyHeading( getNewHeading() );
		
	}
	
	private boolean pastPlatform(){
		/*return myPlayerSprite.getMyLocation().getYLocation()+myPlayerSprite.getMyHeight()>myBouncer
				.getMyLocation().getYLocation() && myPlayerSprite.getMyYVelocity()>0;*/
		return myPlayerSprite.getLocation().getYLocation()+myPlayerSprite.getHeight()<myBouncer
				.getLocation().getYLocation()+(myBouncer.getHeight()*.5) && myPlayerSprite.getYVelocity()>0;
	}
	
	
}