package game_engine;

import java.util.Map;
import java.util.Set;

import game_data.Game;
import game_data.Level;
import game_data.Sprite;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class UpdateGame {
	
	public void update(Game aGame, double timeElapsed, Set<KeyCode> myKeys, Map<Sprite, ImageView> mySpriteImages){
		UpdateStates myLevelState = new UpdateStates(aGame.getCurrentLevel(), timeElapsed, myKeys, mySpriteImages);
		// TODO Update level?
	}

}