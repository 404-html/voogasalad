package game_data.characteristics;

import game_data.Sprite;

public interface ICharactericticAction {
	public void addToPowerUpMap(Sprite collidedSprite, double myTimeInEffect);
}
