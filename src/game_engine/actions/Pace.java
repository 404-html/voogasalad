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
			mySprite.setXVelocity(-1*(mySprite.getXVelocity()));
			mySprite.setYVelocity(-1*(mySprite.getYVelocity()));

		//if(myShouldChangeDirection==true){
			//if(myType.equals("HORIZONTAL")){
				//mySprite.setXVelocity(-1*(mySprite.getXVelocity()));
			//}
			//else{
				//mySprite.setYVelocity(-1*(mySprite.getYVelocity()));
			//}

		}
		//MoveWithHeading moveWithHeading = new MoveWithHeading(mySprite, mySprite.getXVelocity());
		//moveWithHeading.act();
	}

}
