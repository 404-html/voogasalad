package gameplayer.application_controller;

import java.io.File;
import java.util.ResourceBundle;
import gameplayer.front_end.application_scene.IDisplay;
import gameplayer.front_end.application_scene.SceneFactory;
import gameplayer.front_end.gui_generator.GUIGenerator;
import gameplayer.front_end.gui_generator.IGUIGenerator;
import javafx.stage.Stage;
import util.XMLTranslator;

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
	
	protected void save(Object aObject, String aFilePath) {
		XMLTranslator mySaver = new XMLTranslator();
		mySaver.saveToFile(aObject, "XMLGameFiles/", aFilePath);
	}
	
	protected Object load(File aFilePath) {
		XMLTranslator aLoader = new XMLTranslator();
		return aLoader.loadFromFile(aFilePath);
	}
	
	protected Object load(String aFilePath) {
		XMLTranslator aLoader = new XMLTranslator();
		return aLoader.loadFromFile("XMLGameFiles/", aFilePath);
	}
}
