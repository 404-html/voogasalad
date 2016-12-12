package game_engine.actions;

import game_data.Sprite;

/**
 * @author Katrina
 *
 */
public interface Action {
	public void act();
	
	public Action copyWithNewSprite(Sprite aSprite);
}
