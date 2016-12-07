package game_engine;

import java.util.Map;

import game_engine.actions.Action;
import javafx.scene.input.KeyCode;

public interface IUpdateStatesAndPowerUps {
	void generateDefaultKeyPressedMap();
	void setKeyPressedMapWithBoosts();
}
