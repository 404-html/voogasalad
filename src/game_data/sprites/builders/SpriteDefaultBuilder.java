package game_data.sprites.builders;

import game_data.Sprite;

@FunctionalInterface
public interface SpriteDefaultBuilder{

	static final int WIDTH = 100;
	static final int HEIGHT = 100;
	
	public Sprite build();
}
