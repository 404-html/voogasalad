package game_engine.actions;

import javafx.geometry.Side;
import game_data.states.Physics;
import game_data.states.State;
import game_data.Sprite;
import game_engine.SpritePhysics;

/**
 * @author Alex
 *
 */

public class Hit implements Action {
	
	private Sprite myPlayerSprite;
	private Side mySide;
	

	public Hit(Sprite player, Side aSide) {
		myPlayerSprite = player;
		mySide = aSide;
	}

	@Override
	public void act() {
		
		
		//get new Velocity –– gets horizontal or vertical components to zero

		setNewVelocity();
		setNewAcceleration();
	}
	
	private void setNewVelocity() {
				
		if(mySide == Side.LEFT || mySide == Side.RIGHT) {
			myPlayerSprite.setMyXVelocity(0);
		}
		if(mySide==Side.TOP || mySide==Side.BOTTOM){
			System.out.println("top");
			myPlayerSprite.setMyYVelocity(0);
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
		if(mySide == Side.LEFT || mySide==Side.RIGHT){
			myPlayerSprite.setMyXAcceleration(-mySpritePhysics.getHorizontalGravity());
		}
		if(mySide==Side.TOP || mySide==Side.BOTTOM){
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