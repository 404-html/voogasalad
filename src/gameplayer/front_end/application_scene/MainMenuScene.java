package gameplayer.front_end.application_scene;
import gameplayer.front_end.animated_display.AnimatedDisplay;
import gameplayer.front_end.gui_generator.GUIGenerator;
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
	
	private AnimatedDisplay myAnimation;
	private double myWidth;
	private GUIGenerator myGUIGenerator; 

	public MainMenuScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
		myWidth = aWidth;
		myAnimation = new AnimatedDisplay();
		myGUIGenerator = new GUIGenerator();
	}

	@Override
	public Scene init() {
		getRoot().setCenter(addNodes());
		return myScene;
	}
	
	private VBox addNodes() {
		getOptions().getChildren().add(myAnimation.makeFadeTransition(myGUIGenerator.createImage("data/gui/praying-for-the-six.png", myWidth / 10)));
		getOptions().setAlignment(Pos.CENTER);
		return getOptions();
	}
}
