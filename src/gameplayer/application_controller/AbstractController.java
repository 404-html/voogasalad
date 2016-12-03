package gameplayer.application_controller;

import java.util.ResourceBundle;

import gameplayer.front_end.application_scene.IDisplay;
import gameplayer.front_end.gui_generator.GUIGenerator;
import javafx.stage.Stage;

public abstract class AbstractController {

	protected static final String FILE = "gameplayerlabels.";
	protected static final String BUTTONLABEL = "ButtonLabels"; 
	protected static final int SCENE_SIZE = 1000;
	protected Stage myStage;
	protected GUIGenerator myGUIGenerator = new GUIGenerator();
	protected ResourceBundle myButtonLabels;
	
	public AbstractController() {
		
	}
	
	protected void resetStage(IDisplay aScene){
		myStage.close();
		myStage.setScene(aScene.init());
		myStage.show();
	}
}
