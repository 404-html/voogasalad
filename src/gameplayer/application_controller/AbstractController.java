package gameplayer.application_controller;

import java.util.ResourceBundle;
import gameplayer.front_end.application_scene.IDisplay;
import gameplayer.front_end.application_scene.SceneFactory;
import gameplayer.front_end.gui_generator.GUIGenerator;
import gameplayer.front_end.gui_generator.IGUIGenerator;
import gameplayer.front_end.gui_generator.combobox_generator.ComboBoxFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class AbstractController {
	
	protected static final String FILE = "gameplayerlabels.";
	protected static final String BUTTONLABEL = "ButtonLabels"; 
	protected static final int SCENE_SIZE = 1000;

	protected SceneFactory mySceneBuilder;
	protected Stage myStage;
	protected ResourceBundle myButtonLabels;
	private IGUIGenerator myGUIGenerator = new GUIGenerator();
	
	public AbstractController() {
		
	}
	
	protected void resetStage(IDisplay aScene) {
		myStage.close();
		myStage.setScene(aScene.init());
		myStage.show();
	}
	
	protected IGUIGenerator getGUIGenerator() {
		return myGUIGenerator;
	}
}
