package gameplayer.front_end.application_scene;

import gameplayer.back_end.keycode_handler.MovementHandler;
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
	
	public void moveScreen(MovementHandler aHandler) {
		myGamePlayCanvas.setTranslateX(aHandler.getXMovement());
		myGamePlayCanvas.setTranslateY(aHandler.getYMovement());
	}
	
	public void setOpacity(double aOpacity) {
		myGamePlayCanvas.setOpacity(aOpacity);
	}
	
	public double getAnimationScreenXPosition() {
		return myGamePlayCanvas.getTranslateX();
	}
	
	public double getAnimationScreenYPosition() {
		return myGamePlayCanvas.getTranslateY();
	}
	
	public void addImageToView(ImageView aImage) {
		myGamePlayCanvas.getChildren().add(aImage);
	}
	
}

