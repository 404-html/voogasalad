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
		//System.out.println(mySide == Side.TOP);
		//System.out.println(pastPlatform());
		//System.out.println("poop");
		if(mySide==Side.TOP && pastPlatform()){
			//myPlayerSprite.setMyYVelocity(-(myPlayerSprite.getMyYVelocity()+myBounceSpeed));
			//System.out.println("asdasdasdasdasd");
			//System.out.println("player is at " + myPlayerSprite.getMyLocation().getYLocation());
			//System.out.println("bouncer is at " + myBouncer.getMyLocation().getYLocation());
			myPlayerSprite.setMyYVelocity(-1*myBounceSpeed);
			System.out.println("Bounce Speed is " + myBounceSpeed);
			System.out.println("velocity is " + myPlayerSprite.getMyYVelocity());
			System.out.println("fuck this fucking piece of shitting fuck");
			System.out.println();
			System.out.println();
			//myPlayerSprite.getMyLocation().setLocation(myPlayerSprite.getMyLocation().getXLocation(), 
			//		myBouncer.getMyLocation().getYLocation());
		}	
		System.out.println("this should match " + myPlayerSprite.getMyYVelocity());
		//System.out.println(" y vel" + myPlayerSprite.getMyYVelocity());
		//myPlayerSprite.setMyVelocity( getNewVelocity() );		
		//myPlayerSprite.getMyLocation().setMyHeading( getNewHeading() );
		
	}
	
	private boolean pastPlatform(){
		/*return myPlayerSprite.getMyLocation().getYLocation()+myPlayerSprite.getMyHeight()>myBouncer
				.getMyLocation().getYLocation() && myPlayerSprite.getMyYVelocity()>0;*/
		return myPlayerSprite.getMyLocation().getYLocation()+myPlayerSprite.getMyHeight()<myBouncer
				.getMyLocation().getYLocation()+(myBouncer.getMyHeight()*.5) && myPlayerSprite.getMyYVelocity()>0;
	}
	
	
}