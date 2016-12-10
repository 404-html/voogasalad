package game_engine;

import java.util.Map;
import java.util.Set;
import game_data.Game;
import game_data.Sprite;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
/**
 * @author Katrina, Lucia
 *
 */
public class UpdateGame {
	private UpdateStates myUpdateLevel;
	private Game myGame;
	public UpdateGame(Game aGame){
		myGame=aGame;
		myUpdateLevel = new UpdateStates(myGame.getCurrentLevel());
	}
	public void update(double aTimeElapsed, Set<KeyCode> aKeysPressed, Set<KeyCode> aKeysReleased, Map<Sprite, ImageView> aSpriteImages){
		myUpdateLevel.update(aTimeElapsed, aKeysPressed, aKeysReleased, aSpriteImages);
		changeLevel();
	}
	public void changeLevel(){
		if(myUpdateLevel.getLevel().wonLevel()){
			int currentLevelIndex = myGame.getLevels().indexOf(myGame.getCurrentLevel());
			if(currentLevelIndex+1<myGame.getLevels().size()){
				myGame.setCurrentLevel(currentLevelIndex+1);
			}
			else{
				myGame.setHasWon(true);
			}
		}
		else if(myUpdateLevel.getLevel().lostLevel()){
			myGame.setHasLost(true);
		}
	}
}