package game_engine.actions;

import javafx.geometry.Side;
import game_data.states.Physics;
import game_data.states.State;
import game_data.Sprite;
import game_engine.SpritePhysics;

/**
 * @author Katrina
 *
 */

public class Hit implements Action {
	
	private Sprite myPlayerSprite;
	private Sprite myNonPlayerSprite;
	private Side mySide;
	

	public Hit(Sprite player,  Side aSide, Sprite nonPlayerSprite) {
		myPlayerSprite = player;
		myNonPlayerSprite = nonPlayerSprite;
		mySide = aSide;
	}

	@Override
	public void act() {
		
		
		//get new Velocity –– gets horizontal or vertical components to zero
		setNewVelocity();
		setNewAcceleration();
	}
	
	private boolean pastPlatform(){
		return myPlayerSprite.getMyLocation().getYLocation()+myPlayerSprite.getMyHeight()>myNonPlayerSprite
				.getMyLocation().getYLocation() && myPlayerSprite.getMyYVelocity()>0;		
	}
	
	private void setNewVelocity() {
				
		if(mySide == Side.LEFT) {
			//System.out.println("hitting this");
			if(myPlayerSprite.getMyXVelocity()>0){
				
				myPlayerSprite.setMyXVelocity(0);
				//System.out.println("hittin left side of block");
			}
		}
		if(mySide==Side.RIGHT) {
			if(myPlayerSprite.getMyXVelocity()<0){
				//System.out.println("hittin right side of block");
				myPlayerSprite.setMyXVelocity(0);
			}
		}
		if(mySide==Side.TOP){
			//System.out.println("top");
			if(myPlayerSprite.getMyYVelocity()>0){
				//System.out.println("this should be a thing");
				myPlayerSprite.setMyYVelocity(0);
			}
		}
		if(mySide==Side.BOTTOM){
			if(myPlayerSprite.getMyYVelocity()<0){
				myPlayerSprite.setMyYVelocity(-.5*myPlayerSprite.getMyYVelocity());
			}
		}
/*		if(mySide==Side.BOTTOM){
			myPlayerSprite.setMyYVelocity(-myPlayerSprite.getMyYVelocity());
		}*/
	}
	private void setNewAcceleration(){
		SpritePhysics mySpritePhysics = null;
		for(State s: myPlayerSprite.getStates()){
			if(s instanceof Physics){
				mySpritePhysics = ((Physics) s).getPhysics();
			}
		}
		if((mySide == Side.LEFT && mySpritePhysics.getHorizontalGravity()>0)||(mySide == Side.RIGHT && mySpritePhysics.getHorizontalGravity()<0)){
			myPlayerSprite.setMyXAcceleration(-mySpritePhysics.getHorizontalGravity());
		}
		else if((mySide==Side.TOP && mySpritePhysics.getVerticalGravity()>0)||(mySide==Side.BOTTOM && mySpritePhysics.getVerticalGravity()<0)){
			//System.out.println("this should also be a thing");
			myPlayerSprite.setMyYAcceleration(-mySpritePhysics.getVerticalGravity());
		}
		
	}
/*	private double getNewHeading(double oldHeading, double oldVelocity) {
		
		if(mySide == Side.LEFT || mySide == Side.RIGHT) {
			
			if(oldHeading > 0 && oldHeading < 180) {
				return 90;
			}
			return 270;
			
		}
		
		if(oldHeading > 270 || oldHeading < 90) {
			return  0;
		}
		return 180;

	}*/
	
	
		
}