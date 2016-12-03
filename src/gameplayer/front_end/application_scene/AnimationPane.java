package gameplayer.front_end.application_scene;

import gameplayer.back_end.keycode_handler.KeyCodeHandler;
import javafx.scene.image.ImageView;
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
	
	public AnimationPane() {
		myGamePlayCanvas = new Pane();
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
	
	public void clear() {
		myGamePlayCanvas.getChildren().clear();
	}
	
	public void setBackground(Background aBackground) {
		myGamePlayCanvas.setBackground(aBackground);
	}
	
	public void moveScreen(KeyCodeHandler aHandler) {
		myGamePlayCanvas.setTranslateX(myGamePlayCanvas.getTranslateX() + aHandler.getMovement());
	}
	
	public void addImageToView(ImageView aImage) {
		myGamePlayCanvas.getChildren().add(aImage);
	}
	
}

