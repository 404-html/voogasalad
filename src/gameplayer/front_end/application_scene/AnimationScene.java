package gameplayer.front_end.application_scene;
import java.util.Set;
import game_data.Sprite;
import gameplayer.back_end.keycode_handler.KeyCodeHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

/**
 * Concrete representation of the scene where games are played
 * 
 * @author tedmarchildon, hannah
 *
 */

public class AnimationScene {
	
	private Pane myGamePlayCanvas;
	private KeyCodeHandler myKeyCodeHandler;
	
	public AnimationScene(Scene aScene, double aWidth, double aHeight) {
		myGamePlayCanvas = new Pane();
		myKeyCodeHandler = new KeyCodeHandler();
	}
	/**
	 * Add the correct nodes to the scene
	 */
	
	public Pane init() {
		return myGamePlayCanvas;
	}
	
	public void addButton(EventHandler<? super MouseEvent> handler){
		Button b = new Button("Save");
		b.setOnMouseClicked(handler);
		myGamePlayCanvas.getChildren().add(b);
	}
	
	public void makeRed() {
		myGamePlayCanvas.setStyle("-fx-background-color: red;");
	}
	
	public ImageView addSpriteToScene(Sprite aSprite){
		ImageView image = new ImageView(aSprite.getMyImagePath());
		image.setFitWidth(aSprite.getMyWidth());
		image.setFitHeight(aSprite.getMyHeight());
		image.setX(aSprite.getMyLocation().getXLocation());
		image.setY(aSprite.getMyLocation().getYLocation());
		myGamePlayCanvas.getChildren().add(image);
		return image;
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
