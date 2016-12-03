package gameplayer.front_end.application_scene;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import gameplayer.back_end.keycode_handler.MovementHandler;
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
	private MovementHandler myKeyCodeHandler;
	//private String myBackgroundFilePath;
	
	public AnimationPane(MovementHandler aKeyHandler, double aWidth, double aHeight) {
		myGamePlayCanvas = new Pane();
		myKeyCodeHandler = aKeyHandler;
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
	
	public void moveScreen() {
		myGamePlayCanvas.setTranslateX(myGamePlayCanvas.getTranslateX() + myKeyCodeHandler.getXMovement());
	}
	public void addImageToView(ImageView aImage) {
		myGamePlayCanvas.getChildren().add(aImage);
	}
	
}

