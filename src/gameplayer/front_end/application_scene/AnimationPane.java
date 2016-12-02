package gameplayer.front_end.application_scene;
import java.util.Set;
import gameplayer.back_end.keycode_handler.KeyCodeHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

/**
 * Concrete representation of the scene where games are played
 * 
 * @author tedmarchildon, hannah
 *
 */

public class AnimationPane {
	
	private Pane myGamePlayCanvas;
	private KeyCodeHandler myKeyCodeHandler;
	
	public AnimationPane(Scene aScene, double aWidth, double aHeight) {
		myGamePlayCanvas = new Pane();
		myKeyCodeHandler = new KeyCodeHandler();
	}
	
	/**
	 * Add the correct nodes to the scene
	 */
	
	public Pane init() {
		return myGamePlayCanvas;
	}
	
	public void makeRed() {
		myGamePlayCanvas.setStyle("-fx-background-color: red;");
	}
	
	public void addSpriteToScene(ImageView aSprite){
		myGamePlayCanvas.getChildren().add(aSprite);
	}
	
	public void clear() {
		myGamePlayCanvas.getChildren().clear();
	}
	
	public void setBackground(Background aBackground) {
		myGamePlayCanvas.setBackground(aBackground);
	}
	
	public void moveScreen(Set<KeyCode> myKeySet) {
		for (KeyCode key : myKeySet) {
			myGamePlayCanvas.setTranslateX(myGamePlayCanvas.getTranslateX() + myKeyCodeHandler.getMovement(key));
		}
	}
}
