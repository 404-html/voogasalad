package game_engine;

import game_data.Sprite;

public abstract class Side {
	
	public abstract void bounce(Sprite aSprite, double bounceSpeed);
	public abstract boolean breaksOnSide(boolean isBreakable);
	public abstract void hitImpassable(Sprite aSprite, SpritePhysics aSpritePhysics);
	
}

