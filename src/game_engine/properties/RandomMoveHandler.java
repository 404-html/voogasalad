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
		myScreenXPosition = aMyScreenXPosition*-1; //switch to different coordinates – left side
		myScreenYPosition = aMyScreenYPosition*-1; //switching to different coordinates – top side
		
		if(onScreen()) {
			onScreenOnceSinceJump = true;
			return;
		}
		if(!onScreenOnceSinceJump) {
			return;
		}
		
		setSpritesNewLocation();
		onScreenOnceSinceJump = false;
		
	}
	
	private void setSpritesNewLocation() {
		
		double newXLoc, newYLoc;
		if(verticalMove) {
			newXLoc = Math.random()*myScreenWidth;
			newYLoc = mySprite.getMyLocation().getYLocation()+myScreenHeight+mySprite.getMyHeight();
		} else {
			newXLoc = mySprite.getMyLocation().getXLocation()+myScreenWidth+mySprite.getMyWidth();
			newYLoc = Math.random()*myScreenHeight;
		}
		mySprite.getMyLocation().setLocation(newXLoc, newYLoc);
		
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
