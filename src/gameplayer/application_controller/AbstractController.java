package gameplayer.application_controller;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import gameplayer.back_end.user_information.HighscoreManager;
import gameplayer.back_end.user_information.XMLHandler;
import gameplayer.front_end.application_scene.IDisplay;
import gameplayer.front_end.application_scene.SceneFactory;
import gameplayer.front_end.gui_generator.GUIGenerator;
import gameplayer.front_end.gui_generator.IGUIGenerator;
import gameplayer.front_end.popup.ErrorAlert;
import javafx.stage.Stage;

public abstract class AbstractController {
	
	protected static final String FILE = "gameplayerlabels.";
	protected static final String BUTTONLABEL = "ButtonLabels"; 
	protected static final int SCENE_SIZE = 1000;
	private SceneFactory mySceneBuilder;
	private Stage myStage;
	private ResourceBundle myButtonLabels;
	private IGUIGenerator myGUIGenerator = new GUIGenerator();
	private XMLHandler myXMLHandler;
	private PlayerInformationController myInformationController;
	
	public AbstractController(Stage aStage) {
		myStage = aStage;
		mySceneBuilder = new SceneFactory();
		myButtonLabels = PropertyResourceBundle.getBundle(FILE + BUTTONLABEL);
		myStage.setTitle(myButtonLabels.getString("Title"));
		myXMLHandler = new XMLHandler();
	}
	
	protected void resetStage(IDisplay aScene) {
		//myStage.close();
		myStage.setScene(aScene.init());
		myStage.show();
	}
	
	protected IGUIGenerator getGUIGenerator() {
		return myGUIGenerator;
	}
	
	protected XMLHandler getXMLHandler() {
		return myXMLHandler;
	}
	
	protected Stage getStage() {
		return myStage;
	}
	
	protected SceneFactory getSceneFactory() {
		return mySceneBuilder;
	}
	
	protected PlayerInformationController getPlayerInformationController() {
		return myInformationController;
	}
	
	protected void setPlayerInformationController(PlayerInformationController aController) {
		myInformationController = aController;
	}
	
	protected ResourceBundle getButtonLabels(){
		return myButtonLabels;
	}
	
	protected HighscoreManager loadHighscores() {
		HighscoreManager hm;
		try {
			hm = (HighscoreManager) getXMLHandler().load("highscores");
		} catch (Exception e) {
			hm = new HighscoreManager();
		}
		return hm;
	}

	protected void showError(Exception x) {
		ErrorAlert ea = new ErrorAlert();
		ea.show(x);
	}
}
