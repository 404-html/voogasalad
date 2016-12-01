package gameplayer.front_end.application_scene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * Concrete representation of the introduction scene
 * 
 * @author tedmarchildon, hannah
 *
 */
public class MainMenuScene extends AbstractNavigationPlayerScene {

	public MainMenuScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
	}

	@Override
	public Scene init(){
		myRoot.setCenter(addNodes());
		return myScene;
	}
	
	private VBox addNodes(){
		myOptions = new VBox(BOX_INSETS);
		myOptions.getChildren().addAll(myGUIGenerator.createLabel("Main Menu", 0, 0));
		myOptions.setAlignment(Pos.CENTER);
		return myOptions;
	}
}
