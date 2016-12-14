package gameplayer.application_controller;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileChoiceController {

	FileChooser myFileChooser;
	
	public FileChoiceController(){
		myFileChooser = new FileChooser();
		myFileChooser.setInitialDirectory(new File("./XMLGameFiles"));
		myFileChooser.setTitle("Choose New Game");
        myFileChooser.getExtensionFilters().add(new ExtensionFilter("Game Files", "*.xml"));
	}
	
	public File show(Stage stage){
		return myFileChooser.showOpenDialog(stage);
	}
}
