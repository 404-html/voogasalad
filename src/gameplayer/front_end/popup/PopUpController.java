package gameplayer.front_end.popup;

import java.io.File;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PopUpController implements IPopUpDisplay {
	
	private static final String STYLESHEET = "GUI/style.css";
	private static final String FILE = "file:";
	private final int SIZE = 500;
	private Stage myStage;
	private VBox myOptions;
	
	public PopUpController() {
		myStage = new Stage();
		myOptions = new VBox(40);
		myOptions.setAlignment(Pos.CENTER);
		Scene stageScene = new Scene(myOptions, SIZE, SIZE);
		File css = new File(STYLESHEET);
		stageScene.getStylesheets().add(FILE + css.getAbsolutePath());
		myStage.setScene(stageScene);
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
}
