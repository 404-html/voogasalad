package game_engine.actions;

//import javafx.geometry.Side;
import game_data.Sprite;
import game_engine.Side;
import game_engine.Top;

/**
 * @author Austin
 *
 */

public class BounceTopOnly implements Action {
	
	private double myBounceSpeed;
	private Sprite myPlayerSprite;
	private Sprite myBouncer;
	private Side mySide;
	
	public BounceTopOnly(Sprite aSprite, Sprite aPlayer) {
		myPlayerSprite = aPlayer;
		myBouncer = aSprite;
	}
	
	public BounceTopOnly(double bouncerSpeed, Sprite player, Side aSide, Sprite bouncer) {
		myBounceSpeed = bouncerSpeed;
		myPlayerSprite = player;
		mySide = aSide;
		myBouncer = bouncer;
	}

	@Override
	public void act() {
		//System.out.println("init y vel" + myPlayerSprite.getYVelocity());
		//System.out.println(mySide == Side.TOP);
		//System.out.println(pastPlatform());
		//System.out.println("poop");

		//if(mySide==Side.TOP && pastPlatform()){
		if(mySide instanceof Top && pastPlatform()){
			mySide.bounce(myPlayerSprite, myBounceSpeed);
			/*myPlayerSprite.setYVelocity(-1*myBounceSpeed);*/

		//if(mySide==Side.TOP && pastPlatform()){
			//myPlayerSprite.setYVelocity(-(myPlayerSprite.getYVelocity()+myBounceSpeed));
			//System.out.println("asdasdasdasdasd");
			//System.out.println("player is at " + myPlayerSprite.getLocation().getYLocation());
			//System.out.println("bouncer is at " + myBouncer.getLocation().getYLocation());
			//myPlayerSprite.setYVelocity(-1*myBounceSpeed);
			//myPlayerSprite.getLocation().setLocation(myPlayerSprite.getLocation().getXLocation(), 
			//		myBouncer.getLocation().getYLocation());
		}	
		//System.out.println(" y vel" + myPlayerSprite.getYVelocity());
		//myPlayerSprite.setMyVelocity( getNewVelocity() );		
		//myPlayerSprite.getLocation().setMyHeading( getNewHeading() );
		//
	}
	
	public boolean pastPlatform(){

		return myPlayerSprite.getLocation().getYLocation()+myPlayerSprite.getHeight()<myBouncer
				.getLocation().getYLocation()+(myBouncer.getHeight()*.5) && myPlayerSprite.getYVelocity()>=0;
	}
	
	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new BounceTopOnly(myBounceSpeed, aSprite, mySide, myBouncer);
	}
	
}