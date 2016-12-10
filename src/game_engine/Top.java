package game_engine;

import game_data.Sprite;

public class Top extends Side{

	public Top() {
	}
	
	@Override
	public void bounce(Sprite aSprite, double bounceSpeed){
		aSprite.setMyYVelocity(-bounceSpeed);
	}
	@Override
	public boolean breaksOnSide(boolean isBreakable){
		return isBreakable;
	}
	
	@Override
	public void hitImpassable(Sprite aSprite, SpritePhysics aSpritePhysics){
		if(aSprite.getMyYVelocity()>0){
			aSprite.setMyYVelocity(0);
		}
		if(aSpritePhysics.getVerticalGravity()>0){
			aSprite.setMyYAcceleration(-aSpritePhysics.getVerticalGravity());
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
