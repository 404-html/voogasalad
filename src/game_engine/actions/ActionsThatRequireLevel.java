package game_engine.actions;

import game_data.LevelSetter;

public interface ActionsThatRequireLevel extends LevelSetter, Action {
	public void setLevel();
}
