package game_engine;

import game_data.Sprite;
import game_data.states.Physics;

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

}
