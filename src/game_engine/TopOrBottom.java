package game_engine;

import game_data.Sprite;
import game_data.states.Physics;

public class TopOrBottom extends Side{
	
	public TopOrBottom(){
		
	}

	@Override
	public void bounce(Sprite aSprite, double bounceSpeed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean breaksOnSide(boolean isBreakable) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void hitImpassable(Sprite aSprite, Physics aSpritePhysics) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isVertical() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHorizontal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Movable(Sprite aSprite, Sprite movableSprite) {
		// TODO Auto-generated method stub
		
	}
	
	

}
