package game_engine;

import game_data.Sprite;

public class Bottom extends Side{

	public Bottom() {
		
	}
	
	@Override
	public void bounce(Sprite aSprite, double speed){
		aSprite.setMyYVelocity(speed);
	}
	
	@Override
	public boolean breaksOnSide(boolean isBreakable){
		return isBreakable;
	}
	
	@Override
	public void hitImpassable(Sprite aSprite, SpritePhysics aSpritePhysics){
		if(aSprite.getMyYVelocity()<0){
			aSprite.setMyYVelocity(0);
		}
		if(aSpritePhysics.getVerticalGravity()<0){
			aSprite.setMyYAcceleration(-aSpritePhysics.getVerticalGravity());
		}
	}

}
