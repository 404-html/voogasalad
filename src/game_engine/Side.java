package game_engine;

import game_data.Sprite;
import game_data.states.Physics;

public abstract class Side {
	
	public abstract void bounce(Sprite aSprite, double bounceSpeed);
	public abstract boolean breaksOnSide(boolean isBreakable);
	public abstract void hitImpassable(Sprite aSprite, Physics aSpritePhysics);
	public abstract boolean isVertical();
	public abstract boolean isHorizontal();
	public abstract void Movable (Sprite aSprite, Sprite movableSprite);
	
	
}

