package game_engine;

import game_data.Sprite;
import game_data.states.Physics;
import game_data.states.State;

public class Top extends Side{

	public Top() {
	}
	
	@Override
	public void bounce(Sprite aSprite, double bounceSpeed){
		aSprite.setYVelocity(-bounceSpeed);
	}
	@Override
	public boolean breaksOnSide(boolean isBreakable){
		return isBreakable;
	}
	
	@Override
	public void hitImpassable(Sprite aSprite, Physics aSpritePhysics){
		if(aSprite.getYVelocity()>0){
			aSprite.setYVelocity(0);
		}
		if(aSpritePhysics.getVerticalGravity()>0){
			aSprite.setYAcceleration(-aSpritePhysics.getVerticalGravity());
		}
	}

	@Override
	public boolean isVertical() {
		return true;
	}

	@Override
	public boolean isHorizontal() {
		return false;
	}

	@Override
	public void Movable(Sprite aSprite, Sprite movableSprite) {
		
		if(aSprite.getYVelocity()>=0 ){
			
			for(State i : aSprite.getStates()){
				if(i instanceof Physics){
					
					aSprite.setYAcceleration(-((Physics)i).getVerticalGravity());
					aSprite.setYVelocity(0);
					movableSprite.setYAcceleration(((Physics)i).getVerticalGravity());
					
				}
			}

		}
	}
}
