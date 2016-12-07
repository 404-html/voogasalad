package game_engine.properties;

import game_data.Sprite;

//this class will be apart of Sprite to handle movement
public class RandomMoveHandler {

	private boolean verticalMove, onScreenOnceSinceJump;
	private Sprite mySprite;
	private double myScreenWidth, myScreenHeight, myScreenXPosition, myScreenYPosition;
	
	public RandomMoveHandler(boolean aVerticalMove) {
		verticalMove = aVerticalMove;
		onScreenOnceSinceJump = false;
	}
	
	public RandomMoveHandler(RandomMoveHandler aRandomMoveHandler) {
		verticalMove = aRandomMoveHandler.getVerticalMove();
		onScreenOnceSinceJump = false;
	}
	
	public void move(Sprite aMySprite, double aMyScreenWidth, double aMyScreenHeight, 
			double aMyScreenXPosition, double aMyScreenYPosition) {
		
		mySprite = aMySprite;
		myScreenWidth = aMyScreenWidth;
		myScreenHeight = aMyScreenHeight;
		myScreenXPosition = aMyScreenXPosition + myScreenWidth/2; //middle to left
		myScreenYPosition = aMyScreenYPosition + myScreenHeight/2; //middle to top
//		System.out.println("asdfasdf a     " + myScreenXPosition);
//		System.out.println("asdfasdf a     " + myScreenYPosition);
//		System.out.println("asdfasdf a     " + mySprite.getMyLocation().getXLocation());
//		System.out.println("asdfasdf a     " + mySprite.getMyLocation().getYLocation());
		
		if(onScreen()) {
			onScreenOnceSinceJump = true;
			return;
		}
		if(!onScreenOnceSinceJump) {
			return;
		}
		
		if(verticalMove) {
			mySprite.getMyLocation().setLocation(Math.random()*500, mySprite.getMyLocation().getYLocation()+myScreenHeight);
		} else {
			mySprite.getMyLocation().setLocation(mySprite.getMyLocation().getXLocation()+myScreenWidth, Math.random()*500);
		}
		
		onScreenOnceSinceJump = false;
		
	}
	
	private boolean onScreen() {
		return withinBounds(mySprite.getMyLocation().getXLocation(),mySprite.getMyWidth(),myScreenXPosition,myScreenWidth) 
				&& withinBounds(mySprite.getMyLocation().getYLocation(),mySprite.getMyHeight(),myScreenYPosition,myScreenHeight);
	}
	
	private boolean withinBounds(double spriteLoc, double spriteLength, double screenLoc, double screenLength) {
		
		if(spriteLoc < screenLoc) { //left part of sprite outside
			return (spriteLoc+spriteLength) > screenLoc; //right part of sprite inside to outside to the right
		} else {
			return spriteLoc < (screenLoc+screenLength); //left part inside
		}
		
	}
	
	public boolean getVerticalMove() {
		return verticalMove;
	}
	
}
