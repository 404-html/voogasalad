package game_engine.actions;

import game_data.Sprite;
/**
 * @author Katrina
 *
 */
public class Pace implements Action {
	//	private int direction;
	private Sprite mySprite;
	private String myType;
	private boolean myShouldChangeDirection;
	public Pace(Sprite aSprite, boolean aShouldChangeDirection) {
		mySprite=aSprite;
		myShouldChangeDirection=aShouldChangeDirection;
	}
	//this method/class needs to get updated based on moveWithHeading, which is also wrong because it needs to compile
	@Override
	public void act() {
		if(myShouldChangeDirection){
			mySprite.setMyXVelocity(-1*(mySprite.getMyXVelocity()));
			mySprite.setMyYVelocity(-1*(mySprite.getMyYVelocity()));
		}
		//MoveWithHeading moveWithHeading = new MoveWithHeading(mySprite, mySprite.getMyXVelocity());
		//moveWithHeading.act();
	}

}
