package game_engine.actions;

import game_data.Sprite;
/**
 * @author Katrina, Alex!!1!
 *
 */
public class Pace implements Action {
	
	private Sprite mySprite;
	private boolean myShouldChangeDirection;
	
	public Pace(Sprite aSprite, boolean aShouldChangeDirection) {
		mySprite=aSprite;
		myShouldChangeDirection=aShouldChangeDirection;
	}
	
	@Override
	public void act() {
		
		if(myShouldChangeDirection){
			mySprite.setXVelocity(-1*(mySprite.getXVelocity()));
			mySprite.setYVelocity(-1*(mySprite.getYVelocity()));
		}
		
	}

}
