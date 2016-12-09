package game_engine.actions;

import javafx.geometry.Side;
import game_data.states.Physics;
import game_data.states.State;
import game_data.Sprite;
import game_engine.SpritePhysics;

/**
 * @author Austn
 *
 */

public class HitTop implements Action {
	
	private double collisionBuffer;
	private Sprite myPlayerSprite;
	private Sprite myNonPlayerSprite;
	private Side mySide;

	public HitTop(Sprite player, Side aSide, Sprite nonPlayerSprite) {
		myPlayerSprite = player;
		myNonPlayerSprite = nonPlayerSprite;
		mySide = aSide;
		collisionBuffer = nonPlayerSprite.getMyHeight()*.2;
	}

	@Override
	public void act() {
		
		
		//get new Velocity –– gets horizontal or vertical components to zero
		setNewVelocity();
		setNewAcceleration();
	}
	
	private void setNewVelocity() {

		if(mySide==Side.TOP){
			if(myPlayerSprite.getMyYVelocity()>0){
				if(pastPlatform()){
					myPlayerSprite.setMyYVelocity(0);
				}
			}
		}
	}
	
	private boolean pastPlatform(){
		return myPlayerSprite.getMyLocation().getYLocation()+myPlayerSprite.getMyHeight()<myNonPlayerSprite
				.getMyLocation().getYLocation()+(myNonPlayerSprite.getMyHeight()*.5) && myPlayerSprite.getMyYVelocity()>=0;
	}

	private void setNewAcceleration(){
		SpritePhysics mySpritePhysics = null;
		for(State s: myPlayerSprite.getStates()){
			if(s instanceof Physics){
				mySpritePhysics = ((Physics) s).getPhysics();
			}
		}
		if((mySide==Side.TOP && mySpritePhysics.getVerticalGravity()>0)&&pastPlatform()){
			myPlayerSprite.setMyYAcceleration(-mySpritePhysics.getVerticalGravity());
		}
		
	}
	
	
		
}
