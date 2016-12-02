package gameplayer.front_end.application_scene;
import gameplayer.front_end.animated_display.AnimatedDisplay;
import gameplayer.front_end.gui_generator.GUIGenerator;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Concrete representation of the introduction scene
 * 
 * @author tedmarchildon, hannah
 *
 */
public class MainMenuScene extends AbstractNavigationPlayerScene {
	
	//private GUIGenerator myCreator;
	private AnimatedDisplay myAnimation;
	private Pane myPane; 
	private double myWidth;
	private double myHeight;
	private GUIGenerator myGUIGenerator; 

	public MainMenuScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
		//myCreator = new GUIGenerator();
		myPane = new Pane();
		myAnimation = new AnimatedDisplay();
		myWidth = aWidth;
		myHeight = aHeight;
		myGUIGenerator = new GUIGenerator();
	}

	@Override
	public Scene init() {
		myPane.getChildren().add(addNodes());
		myRoot.setCenter(myPane);
		return myScene;
	}
	
	private VBox addNodes() {
		myOptions = new VBox(BOX_INSETS);
		myOptions.prefHeightProperty().bind(myPane.heightProperty());
		myOptions.prefWidthProperty().bind(myPane.widthProperty());
		myOptions.setLayoutX(0);
		myOptions.setLayoutY(0);
		myOptions.getChildren().add(myAnimation.makeFadeTransition(myGUIGenerator.createImage("data/gui/praying-for-the-six.png", myWidth / 10)));
		//myOptions.getChildren().add(myGUIGenerator.createImage("data/gui/it_works.png", myWidth / 5));
		myOptions.setAlignment(Pos.CENTER);
		return myOptions;
	}
}
