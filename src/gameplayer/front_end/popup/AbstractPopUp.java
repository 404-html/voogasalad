package gameplayer.front_end.popup;

import java.io.File;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AbstractPopUp {
	
	private static final String STYLESHEET = "data/gui/style.css";
	private final int SIZE = 500;
	private Stage myStage;
	private VBox myOptions;
	protected static final String RESOURCE_FILE = "gameplayerlabels.";
	protected static final String BUTTONLABEL = "ButtonLabels"; 
	private ResourceBundle myButtonLabels;
	
	public AbstractPopUp() {
		myStage = new Stage();
		myOptions = new VBox(40);
		myOptions.setAlignment(Pos.CENTER);
		myButtonLabels = PropertyResourceBundle.getBundle(RESOURCE_FILE + BUTTONLABEL);
		Scene stageScene = new Scene(myOptions, SIZE, SIZE);
		File css = new File(STYLESHEET);
		stageScene.getStylesheets().add(css.toURI().toString());
		myStage.setScene(stageScene);
		myOptions.setId("pane");
	}
	
	public void addOption(Node node) {
		myOptions.getChildren().add(node);
	}
	
	public void show() {
		myStage.show();
	}
	
	public void setOnClosed(EventHandler<WindowEvent> aHandler){
		myStage.setOnCloseRequest(aHandler);
	}
	
	protected String getString(String aProperty) {
		return myButtonLabels.getString(aProperty);
	}
	
	protected RadioButton createRadioButton(String aMessage, ToggleGroup aGroup) {
		RadioButton radioButton = new RadioButton(aMessage);
		radioButton.setToggleGroup(aGroup);
		radioButton.setSelected(true);
		radioButton.setUserData(aMessage);
		return radioButton;
	}
}
