package game_engine;

import java.util.Map;
import java.util.Set;
import game_data.Game;
import game_data.Sprite;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
/**
 * @author Katrina, Lucia
 *
 */
public class UpdateGame {
	
	//public void update(Game aGame, double timeElapsed, Set<KeyCode> myKeys, Set<KeyCode> myKeysReleased, Map<Sprite, ImageView> map, double aHeight, double aWidth, double aXPositionForScreen, double aYPositionForScreen){
		//UpdateStates myLevelState = new UpdateStates(aGame.getCurrentLevel(), timeElapsed, myKeys, myKeysReleased, map, aHeight, aWidth, aXPositionForScreen, aYPositionForScreen);
		// TODO Update level?
	//}
	
	public void update(Game aGame, double timeElapsed, Set<KeyCode> myKeys, Set<KeyCode> myKeysReleased, Map<Sprite, ImageView> mySpriteImages, double aScreenHeight, double aScreenWidth, double aScreenXPosition, double aScreenYPosition){
		UpdateStates myLevelState = new UpdateStates(aGame.getCurrentLevel(), timeElapsed, myKeys, myKeysReleased, mySpriteImages, aScreenHeight, aScreenWidth, aScreenXPosition, aScreenYPosition);
		// TODO Update level?
		}

}