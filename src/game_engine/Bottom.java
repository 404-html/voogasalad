package game_engine;

import game_data.Sprite;

public class Bottom extends Side{

	public Bottom() {
		
	}
	
	@Override
	public void bounce(Sprite aSprite, double speed){
		aSprite.setYVelocity(speed);
	}
	
	@Override
	public boolean breaksOnSide(boolean isBreakable){
		return isBreakable;
	}
	
	@Override
	public void hitImpassable(Sprite aSprite, SpritePhysics aSpritePhysics){
		if(aSprite.getYVelocity()<0){
			aSprite.setYVelocity(0);
		}
		if(aSpritePhysics.getVerticalGravity()<0){
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
