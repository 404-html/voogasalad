package game_engine.actions;

import game_data.Sprite;
import game_data.states.Physics;
import game_data.states.State;
import game_engine.SpritePhysics;

public class Stick implements Action{

	private Sprite myCollidedSprite;
	private Sprite mySprite;
	
	public Stick(Sprite collidedSprite, Sprite origSprite){
		myCollidedSprite = collidedSprite;
		mySprite = origSprite;
	}
	
	@Override
	public void act() {
		SpritePhysics spritePhysics = null;
		for(State s: mySprite.getStates()){
			if(s instanceof Physics){
				spritePhysics = ((Physics) s).getPhysics();
			}
		}
		if(spritePhysics!=null){
			myCollidedSprite.setMyYAcceleration(spritePhysics.getVerticalGravity());
			myCollidedSprite.setMyYVelocity(mySprite.getMyYVelocity());
			myCollidedSprite.setMyXAcceleration(spritePhysics.getHorizontalGravity());
			myCollidedSprite.setMyXVelocity(mySprite.getMyXVelocity());
		}
		//myCollidedSprite.setMyYAcceleration();
	}

}
